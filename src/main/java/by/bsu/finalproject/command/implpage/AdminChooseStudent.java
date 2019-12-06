package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.InformationServiceImpl;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public class AdminChooseStudent implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        InformationServiceImpl informationService = new InformationServiceImpl();
        Map<Integer, PersonInformation> userMap ;
        int noOfRecords;
        int currentPage = Integer.valueOf(request.getParameter(ParamName.CURRENT_PAGE));
        int recordsPerPage = Integer.valueOf(request.getParameter(ParamName.RECORDS_PER_PAGE));
        try {
            userMap = informationService.findLimitUserMap(currentPage,recordsPerPage);
            noOfRecords = informationService.findNumberOfRows();
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
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_ADMIN_CHOOSE_PERSON);

    }
    }
