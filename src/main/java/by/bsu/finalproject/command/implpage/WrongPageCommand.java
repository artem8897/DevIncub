package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to login page if case of wrong command
 * @author A. Kuzmik
 */

public class WrongPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        request.setAttribute(ParamName.WRONG_ACTION, MessageManager.getProperty(MessageName.WRONG_ACTION));
        return ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);

    }
}
