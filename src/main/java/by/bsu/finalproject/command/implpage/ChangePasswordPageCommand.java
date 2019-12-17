package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for updating users password command
 * @author A. Kuzmik
 */


public class ChangePasswordPageCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_PASSWORD);
    }
}
