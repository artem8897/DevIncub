package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.ReviewServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Find trainers review command
 * @author A. Kuzmik
 */

public class FindTrainersReview implements ActionCommand {

    private ReviewServiceImpl reviewService = new ReviewServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        int trainerId;

        if(user != null && user.getUserType() == UserType.TRAINER){
            trainerId = user.getId();
        }else{
            trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_TRAINER_ID));
        }
        Map<Integer, Review> reviewMap;

        try {
            reviewMap = reviewService.findReview(trainerId, UserType.TRAINER);
        } catch (LogicException e) {
            throw new CommandException(e);
        }

        request.setAttribute(ParamName.REVIEW,reviewMap);

        return ConfigurationManager.getProperty(PathName.PATH_PAGE_ALL_REVIEWS);
    }
}
