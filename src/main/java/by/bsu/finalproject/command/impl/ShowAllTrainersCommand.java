package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public class ShowAllTrainersCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        List<Trainer> trainerList ;
        int noOfRecords;
        int currentPage = Integer.parseInt(request.getParameter(ParamName.CURRENT_PAGE));
        int recordsPerPage = Integer.parseInt(request.getParameter(ParamName.RECORDS_PER_PAGE));
        try {
            trainerList = trainerService.findLimitTrainerMap(currentPage,recordsPerPage);
            noOfRecords = trainerService.findNumberOfRows();
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
        request.setAttribute(ParamName.TRAINER_ATTRIBUTE,trainerList);
        return ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_TRAINERS);
    }
}
