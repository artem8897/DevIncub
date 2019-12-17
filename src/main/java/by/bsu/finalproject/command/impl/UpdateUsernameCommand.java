package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Update username command
 * @author A. Kuzmik
 */

public class UpdateUsernameCommand implements ActionCommand {
        @Override
        public String execute(HttpServletRequest request) throws CommandException {
            HttpSession session = request.getSession(true);
            int userId = ((User)(session.getAttribute(ParamName.USER_SESSION))).getId();
            String page ;
            String username = request.getParameter(ParamName.PARAM_NAME_USERNAME);
            String redirect = request.getParameter(ParamName.REDIRECT);
            UserServiceImpl userService = new UserServiceImpl();
            page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);
            boolean wasCreated ;
            try {
                wasCreated = userService.changeUsername(userId, username);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
            if(wasCreated){
                request.setAttribute(ParamName.REDIRECT, redirect);
            }else{

            }
            return page;
        }
    }
