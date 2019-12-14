package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.UserServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class ButtonAdminEditionAllCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        UserServiceImpl userService = new UserServiceImpl();
        Map<Integer, User> userMap ;
        int noOfRecords;
        int currentPage = Integer.parseInt(request.getParameter(ParamName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParamName.RECORDS_PER_PAGE));
        try {
            userMap = userService.findAllUserMap(currentPage,recordsPerPage);
            noOfRecords = userService.findNumberOfRows();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if(noOfPages % recordsPerPage > 0){
            noOfPages++;
        }
        request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
        request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
        request.setAttribute(ParamName.RECORDS_PER_PAGE,recordsPerPage);
        request.setAttribute(ParamName.PERSONAL_INFORMATION,userMap);
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_ADMIN_EDITION_USER);
    }
}

