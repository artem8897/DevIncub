package by.bsu.devincub.command.impl;

import by.bsu.devincub.command.ActionCommand;
import by.bsu.devincub.command.PathName;
import by.bsu.devincub.exception.CommandException;
import by.bsu.devincub.exception.ServiceException;
import by.bsu.devincub.service.impl.AccountServiceImpl;
import by.bsu.devincub.manager.ConfigurationManager;
import by.bsu.devincub.service.AccountService;

import javax.servlet.http.HttpServletRequest;

public class CountCommand implements ActionCommand {
    private AccountService accountService = new AccountServiceImpl();
    private static final String COUNT = "count";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int count;
        try {
            count = accountService.findNumberOfUsers();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        request.setAttribute(COUNT, count);
        return ConfigurationManager.getProperty(PathName.PATH_COUNT_PAGE);
    }
}

