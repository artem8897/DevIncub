package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.service.impl.ReviewServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Create students review command
 * @author A. Kuzmik
 */

public class CreateUserReviewCommand implements ActionCommand {

    private TrainerServiceImpl trainerService = new TrainerServiceImpl();
    private ReviewServiceImpl reviewService = new ReviewServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_USER_REVIEW);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        if(user != null) {

            String message = request.getParameter(ParamName.PARAM_NAME_MESSAGE);
            int trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_TRAINER_ID));
            int userId = user.getId();
            String rate = request.getParameter(ParamName.PARAM_NAME_RATE);
            String redirect = request.getParameter(ParamName.REDIRECT);
            boolean wasCreated;
            Map<String, String> map = new HashMap<>();
            try {
                wasCreated = reviewService.createReview(userId, message, rate, trainerId, map);
                if (wasCreated) {
                    request.setAttribute(ParamName.REDIRECT, redirect);
                } else {
                    request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerService.findAllTrainerMap());
                    request.setAttribute(ParamName.MAP, map);
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
                }
            } catch (LogicException e) {
                throw new CommandException(e);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
