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
            int currentPage = Integer.valueOf(request.getParameter("currentPage"));
            int recordsPerPage = Integer.valueOf(request.getParameter("recordsPerPage"));
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
            request.setAttribute("noOfPages", noOfPages);
            request.setAttribute("currentPage", currentPage);
            request.setAttribute("recordsPerPage",recordsPerPage);
            request.setAttribute("userId",userId);
            request.setAttribute(ParamName.TRAINING, trainingMap);

            String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_USERS_TRAINING);
            return page;
    }
}
