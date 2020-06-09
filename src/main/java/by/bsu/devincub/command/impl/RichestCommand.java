package by.bsu.devincub.command.impl;

import by.bsu.devincub.command.ActionCommand;
import by.bsu.devincub.command.PathName;
import by.bsu.devincub.entity.User;
import by.bsu.devincub.manager.ConfigurationManager;
import by.bsu.devincub.service.UserService;
import by.bsu.devincub.exception.CommandException;
import by.bsu.devincub.exception.ServiceException;
import by.bsu.devincub.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;

public class RichestCommand implements ActionCommand {
    private UserService userService = new UserServiceImpl();
    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        User user;
        try {
            user = userService.findRichestUser();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(NAME, user.getUsername());
        request.setAttribute(SURNAME, user.getSecondName());
        return ConfigurationManager.getProperty(PathName.PATH_RICHEST_PAGE);
    }
}
