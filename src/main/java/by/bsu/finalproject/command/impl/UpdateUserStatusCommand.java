package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Change users status command
 * @author A. Kuzmik
 */

public class UpdateUserStatusCommand implements ActionCommand {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page =  ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        if(user != null) {

            int adminId = ((User) (session.getAttribute(ParamName.USER_ATTRIBUTE))).getId();

            String status = request.getParameter(ParamName.STATUS).toUpperCase();
            String userType = request.getParameter(ParamName.PARAM_NAME_USER_TYPE).toUpperCase();
            String redirect = request.getParameter(ParamName.REDIRECT);
            boolean wasChanged;
            int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));
            try {
                wasChanged = userService.changeUserStatus(userId, adminId, status, userType);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
            if (wasChanged) {
                request.setAttribute(ParamName.REDIRECT, redirect);
            } else {
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.WRONG_ACTION));
            }

        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;

    }

}

