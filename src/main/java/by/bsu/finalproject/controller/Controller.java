package by.bsu.finalproject.controller;
import by.bsu.finalproject.command.ActionFactory;
import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
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
@WebServlet("/controller")
public class Controller extends HttpServlet {

    private static final Logger logger = LogManager.getLogger(Controller.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
    }

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        try{
            processRequest(request, response);
        } catch (CommandException e) {
            logger.error(e);
            response.sendError(500);
//            response.sendRedirect(ConfigurationManager.getProperty(PathName.PATH_PAGE_ERROR));
        }

    }
    private void processRequest(HttpServletRequest request,
                                HttpServletResponse response) throws IOException, ServletException, CommandException {

        String page ;
        ActionCommand command = ActionFactory.defineCommand(request);
        page = command.execute(request);
        String redirect = (String) request.getAttribute(ParamName.REDIRECT);
        logger.info(redirect);

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
            request.getSession().setAttribute("nullPage",
                    MessageManager.getProperty("message.nullpage"));
            response.sendRedirect(request.getContextPath() + page);
        }
    }

    @Override
    public void destroy() {
        super.destroy();
        try {
            ConnectionPool.INSTANCE.destroyPool();
        } catch (ConnectionPoolException e) {
            logger.error(e);
        }
    }
}
