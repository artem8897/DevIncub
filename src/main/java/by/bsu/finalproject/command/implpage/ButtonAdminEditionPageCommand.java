package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for admin update command
 * @author A. Kuzmik
 */

public class ButtonAdminEditionPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        request.setAttribute(ParamName.USER_ID,userId);
        return ConfigurationManager.getProperty(PathName.ADMIN_EDITION_PAGE);

    }
}
