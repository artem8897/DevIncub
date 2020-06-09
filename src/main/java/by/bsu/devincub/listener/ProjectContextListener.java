package by.bsu.devincub.listener;

import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.connectionpool.ConnectionPool;
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
    }

}
