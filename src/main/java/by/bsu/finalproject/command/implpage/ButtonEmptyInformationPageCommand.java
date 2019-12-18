package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for creating users information command
 * @author A. Kuzmik
 */

public class ButtonEmptyInformationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);
    }
}
