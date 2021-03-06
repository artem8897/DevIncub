package by.bsu.devincub.connectionpool;

import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.manager.DatabaseResourceManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;

public enum ConnectionPool {

    INSTANCE;
    private final Logger logger = LogManager.getLogger(ConnectionPool.class);
    private Queue<Connection> connectionQueue;
    private static final int DEFAULT_POOL_SIZE = 32;
    private final String DATABASE_DRIVER = DatabaseResourceManager.getProperty("database.driver");
    private final String DATABASE_URL = DatabaseResourceManager.getProperty("database.url");
    private final String DATABASE_USER = DatabaseResourceManager.getProperty("database.user");
    private final String DATABASE_PASSWORD = DatabaseResourceManager.getProperty("database.password");

    ConnectionPool() {
        try {
            Class.forName(DATABASE_DRIVER);
            connectionQueue = new ArrayDeque<>(DEFAULT_POOL_SIZE);
            for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
                Connection connection = new ProxyConnection(DriverManager.getConnection(DATABASE_URL, DATABASE_USER, DATABASE_PASSWORD));
                connectionQueue.add(connection);
            }
        } catch (ClassNotFoundException | SQLException e) {
            logger.fatal(e);
            throw new RuntimeException("Initializing connection pool", e);
        }
    }

    public Connection getConnection() {
        Connection connection = connectionQueue.remove();
        logger.trace("Connection is successful");
        return connection;
    }

    public void releaseConnection(Connection connection) throws ConnectionPoolException {
        if (connection.getClass() == ProxyConnection.class) {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                throw new ConnectionPoolException(e);
            }
            connectionQueue.offer(connection);
        } else {
            logger.error("wrong connection tries to get into ConnectionPool");
            throw new ConnectionPoolException("Unknown connection");
        }
    }


    public void destroyPool() throws ConnectionPoolException {
        for (int i = 0; i < DEFAULT_POOL_SIZE; i++) {
            try {
                ((ProxyConnection) connectionQueue.remove()).reallyClose();
            } catch (SQLException e) {
                throw new ConnectionPoolException("SQLException in destroyPool", e);
            }
        }
        deregisterDriver();
    }

    private void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.error("Cannot deregister driver", e);
            }
        });
    }
}
