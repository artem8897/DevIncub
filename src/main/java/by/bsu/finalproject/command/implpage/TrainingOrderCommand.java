package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;

import javax.servlet.http.HttpServletRequest;

/**
 * Go to page for choose trainer command
 * @author A. Kuzmik
 */

public class TrainingOrderCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        return ConfigurationManager.getProperty(PathName.PATH_PAGE_CHOOSE_TRAINER);
    }
}
