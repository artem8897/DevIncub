package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Show all trainers and their information command
 * @author A. Kuzmik
 */

public class ShowAllTrainersCommand implements ActionCommand {

    private TrainerServiceImpl trainerService = new TrainerServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        List<Trainer> trainerList ;
        int noOfRecords;
        String currentPageString = request.getParameter(ParamName.CURRENT_PAGE);
        String recordPageString = request.getParameter(ParamName.RECORDS_PER_PAGE);
        try {
            trainerList = trainerService.findLimitTrainerMap(currentPageString,recordPageString);
            noOfRecords = trainerService.findNumberOfRows();
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        if(!trainerList.isEmpty()) {
            int currentPage = Integer.parseInt(currentPageString);
            int recordsPerPage  = Integer.parseInt(recordPageString);
            int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
            if(noOfPages % recordsPerPage > 0){
                noOfPages++;
            }
            request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
            request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
            request.setAttribute(ParamName.RECORDS_PER_PAGE,recordsPerPage);
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,trainerList);
            return ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_TRAINERS);
        } else{
            request.setAttribute(ParamName.WRONG_ACTION, MessageManager.getProperty(MessageName.WRONG_ACTION));
            return ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
    }
}
