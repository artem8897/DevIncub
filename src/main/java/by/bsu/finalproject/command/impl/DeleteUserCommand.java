package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Delete user command
 * @author A. Kuzmik
 */

public class DeleteUserCommand implements ActionCommand {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        //todo
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));
        String page ;
        boolean wasDeleted ;
        try {
            wasDeleted = userService.deleteUser(userId);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if(wasDeleted){
            page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
        }else{
            page = ConfigurationManager.getProperty(PathName.ADMIN_EDITION_TRAINER);
        }
        return page;
    }

}

