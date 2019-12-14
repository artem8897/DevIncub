package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
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

public class ChangeUserStatusCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        int adminId = ((User)(session.getAttribute(ParamName.USER_ATTRIBUTE))).getId();

        String status = request.getParameter(ParamName.STATUS).toUpperCase();
        String userType = request.getParameter(ParamName.PARAM_NAME_USER_TYPE).toUpperCase();
        String redirect = request.getParameter(ParamName.REDIRECT);
        String page =  ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
        UserServiceImpl userService = new UserServiceImpl();
        boolean wasChanged ;
        int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));
        try {
            wasChanged = userService.changeUserStatus(userId, adminId, status, userType);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasChanged){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute(ParamName.INFO, MessageManager.getProperty("message.wrongaction"));
        }
        return page;

    }

}

