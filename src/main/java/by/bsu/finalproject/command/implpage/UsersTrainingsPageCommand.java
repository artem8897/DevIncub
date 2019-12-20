package by.bsu.finalproject.command.implpage;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Go to review all trainers review command
 * @author A. Kuzmik
 */

public class UsersTrainingsPageCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_USERS_TRAINING);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {
            int userId;

            if (user.getUserType() == UserType.USER) {
                userId = user.getId();
            } else {
                userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
            }

            TrainingServiceImpl trainingService = new TrainingServiceImpl();
            Map<Integer, Training> trainingMap;
            int noOfRecords;
            String currentPageString = (request.getParameter(ParamName.CURRENT_PAGE));
            String recordPageString = (request.getParameter(ParamName.RECORDS_PER_PAGE));

            try {
                trainingMap = trainingService.findUsersTrainings(currentPageString, recordPageString, userId);
                noOfRecords = trainingService.findNumberOfRows(userId);
            } catch (LogicException e) {
                throw new CommandException(e);
            }

            if (!trainingMap.isEmpty()) {
                int currentPage = Integer.parseInt(currentPageString);
                int recordsPerPage = Integer.parseInt(recordPageString);
                int noOfPages = (int) Math.ceil(noOfRecords * 1.0 / recordsPerPage);
                if (noOfPages % recordsPerPage > 0) {
                    noOfPages++;
                }
                request.setAttribute(ParamName.NUMBER_OF_PAGES, noOfPages);
                request.setAttribute(ParamName.CURRENT_PAGE, currentPage);
                request.setAttribute(ParamName.RECORDS_PER_PAGE, recordsPerPage);
                request.setAttribute(ParamName.USER_ID, userId);
                request.setAttribute(ParamName.TRAINING, trainingMap);

            } else {
                if(user.getUserType() == UserType.ADMIN){
                    page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);
                }else{
                    page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
                }
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINING_EXIST));
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
    return page;
    }
}
