package by.bsu.finalproject.listener;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ProjectContextListener implements ServletContextListener {

    private static final Logger logger = LogManager.getLogger(ProjectContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("ConnectionPool", ConnectionPool.INSTANCE);
        logger.info("Connection pool initialized");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
        logger.info("Connection pool destroyed");
    }

}
