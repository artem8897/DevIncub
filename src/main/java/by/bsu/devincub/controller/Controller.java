package by.bsu.devincub.controller;

import by.bsu.devincub.command.ActionCommand;
import by.bsu.devincub.command.ActionFactory;
import by.bsu.devincub.command.PathName;
import by.bsu.devincub.exception.CommandException;
import by.bsu.devincub.manager.ConfigurationManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        try {
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
        }
    }

    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException, CommandException {

        logger.debug("Controller starts");
        ActionCommand command = ActionFactory.defineCommand(request);
        String page = command.execute(request);

        if (page != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
            dispatcher.forward(request, response);
        } else {
            page = ConfigurationManager.getProperty(PathName.PATH_INDEX_PAGE);
            response.sendRedirect(request.getContextPath() + page);
        }
        logger.debug("Controller finished");
    }
}
