package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for log in command
 * @author A. Kuzmik
 */

public class LoginPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        return ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
    }
}
