package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.ReviewServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Find trainers review command
 * @author A. Kuzmik
 */

public class FindTrainersReview implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        int trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_TRAINER_ID));
        ReviewServiceImpl reviewService = new ReviewServiceImpl();
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
