package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.manager.ConfigurationManager;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Change locale command
 * @author A. Kuzmik
 */

public class ChangeLocaleCommand implements ActionCommand {

    private static final String RU = "ru_RU";
    private static final String EN = "en_US";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        HttpSession session = request.getSession(true);

        String locale = request.getParameter(ParamName.LOCALE);
        if(EN.equals(locale) || RU.equals(locale)){
            session.setAttribute(ParamName.LOCALE,locale);
        }
        return page;
    }
}
