package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for creating trainers information command
 * @author A. Kuzmik
 */

public class CreatingTrainerInformationPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);
    }
}
