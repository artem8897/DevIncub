package by.bsu.finalproject.command;

import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to login page if case of wrong command
 * @author A. Kuzmik
 */

public class WrongCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        request.setAttribute(ParamName.WRONG_ACTION, MessageManager.getProperty(MessageName.WRONG_ACTION));
        return ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);

    }
}
