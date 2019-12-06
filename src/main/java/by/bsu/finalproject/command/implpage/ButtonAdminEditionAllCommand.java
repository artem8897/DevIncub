package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
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
        UserServiceImpl informationService = new UserServiceImpl();
        Map<Integer, User> userMap ;
        int noOfRecords;
        int currentPage = Integer.valueOf(request.getParameter("currentPage"));
        int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
        try {
            userMap = informationService.findAllUserMap(currentPage,recordsPerPage);
            noOfRecords = informationService.findNumberOfRows();
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
        if(noOfPages % recordsPerPage > 0){
            noOfPages++;
        }
        request.setAttribute("noOfPages", noOfPages);
        request.setAttribute("currentPage", currentPage);
        request.setAttribute("recordsPerPage",recordsPerPage);
        request.setAttribute("personal_information",userMap);
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_ADMIN_EDITION_USER);
    }
}

