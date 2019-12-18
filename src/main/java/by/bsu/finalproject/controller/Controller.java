package by.bsu.finalproject.controller;
import by.bsu.finalproject.command.*;
import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ConnectionPoolException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Main servlet controller.
 *
 * @author A. Kuzmik
 *
 */

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try{
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
        }
    }
    /**
     * Processes the request by obtaining a command from the {@link javax.servlet.http.HttpServletRequest} object,
     * execute this command and redirects or forwards on destination page depending on the result of the command execution.
     *
     * @throws IOException
     * @throws ServletException
     */

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException, CommandException {

        logger.debug("Controller starts");

        String page ;
        ActionCommand command = ActionFactory.defineCommand(request);
        page = command.execute(request);
        String redirect = (String) request.getAttribute(ParamName.REDIRECT);

        logger.trace(redirect);

        if (page != null) {

            if(redirect == null){
                RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
                dispatcher.forward(request, response);
            }
            else{
                response.sendRedirect(redirect);
            }
        } else {
            page = ConfigurationManager.getProperty(PathName.PATH_INDEX_PAGE);
            request.getSession().setAttribute(ParamName.NULL_PAGE, MessageManager.getProperty(MessageName.NULL_PAGE));
            response.sendRedirect(request.getContextPath() + page);
        }

        logger.debug("Controller finished");

    }

    /**
     * destroying method of this controller.
     */

    @Override
    public void destroy() {
        super.destroy();
        // destroying connection pool
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
    }
}
