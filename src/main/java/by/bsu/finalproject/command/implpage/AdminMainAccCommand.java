package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;

import javax.servlet.http.HttpServletRequest;

public class AdminMainAccCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.SUCCESS_UPDATE));
        return ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);

    }
}
