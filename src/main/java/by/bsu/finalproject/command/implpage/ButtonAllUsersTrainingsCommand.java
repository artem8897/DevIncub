package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class ButtonAllUsersTrainingsCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
            HttpSession session = request.getSession(true);
            User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
            int userId;
            if (user.getUserType() == UserType.USER) {
                userId = user.getId();
            }else {
                userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            }
            TrainingServiceImpl trainingService = new TrainingServiceImpl();
            Map<Integer, Training> trainingMap ;
            int noOfRecords;
            int currentPage = Integer.parseInt(request.getParameter(ParamName.CURRENT_PAGE));
            int recordsPerPage = Integer.parseInt(request.getParameter(ParamName.RECORDS_PER_PAGE));
            try {
                trainingMap = trainingService.findLimitTrainerMap(currentPage,recordsPerPage,userId);
                noOfRecords = trainingService.findNumberOfRows(userId);
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
            request.setAttribute(ParamName.USER_ID,userId);
            request.setAttribute(ParamName.TRAINING, trainingMap);

            return ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_USERS_TRAINING);
    }
}
