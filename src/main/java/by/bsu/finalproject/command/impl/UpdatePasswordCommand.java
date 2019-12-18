package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.security.Cryptographer;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Update users password command
 * @author A. Kuzmik
 */

public class UpdatePasswordCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_PASSWORD);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {

            int userId = user.getId();
            String password = request.getParameter(ParamName.PARAM_NAME_PASSWORD);
            Cryptographer cryptographer = new Cryptographer();
            String redirect = request.getParameter(ParamName.REDIRECT);
            String encryptedPassword = cryptographer.encrypt(password);
            UserServiceImpl userService = new UserServiceImpl();

            boolean wasCreated;

            try {
                wasCreated = userService.changePassword(userId, encryptedPassword);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
            if (wasCreated) {
                request.setAttribute(ParamName.REDIRECT, redirect);
            } else {
                request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            }
        }else{
           page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }

        return page;
    }
}
