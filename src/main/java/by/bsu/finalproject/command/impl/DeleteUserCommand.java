package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;

public class DeleteUserCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        //todo
        int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));
        String page ;
        UserServiceImpl userService = new UserServiceImpl();
        boolean wasDeleted ;
        try {
            wasDeleted = userService.deleteUser(userId);
        } catch (
                LogicException e) {
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

