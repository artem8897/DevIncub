package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Go to page with all users and their status command
 * @author A. Kuzmik
 */

public class AdminEditUserStatusesPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_ADMIN_EDITION_USER);
        UserServiceImpl userService = new UserServiceImpl();
        Map<Integer, User> userMap ;
        int noOfRecords;
        String currentPageString = (request.getParameter(ParamName.CURRENT_PAGE));
        String recordPageString = (request.getParameter(ParamName.RECORDS_PER_PAGE));

        try {
            userMap = userService.findAllUserMap(currentPageString,recordPageString);
            noOfRecords = userService.findNumberOfUsers();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(!userMap.isEmpty()) {
            int currentPage = Integer.parseInt(currentPageString);
            int recordsPerPage  = Integer.parseInt(recordPageString);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            if (noOfPages % recordsPerPage > 0) {
                noOfPages++;
            }
            request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
            request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
            request.setAttribute(ParamName.RECORDS_PER_PAGE, recordsPerPage);
            request.setAttribute(ParamName.PERSONAL_INFORMATION, userMap);
        }else{
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.WRONG_ACTION));
            page =  ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
        }
        return page;
    }
}

