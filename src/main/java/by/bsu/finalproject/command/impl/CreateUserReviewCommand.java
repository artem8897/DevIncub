package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Create students review command
 * @author A. Kuzmik
 */

public class CreateUserReviewCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page = null;
        HttpSession session = request.getSession(true);
        String message = request.getParameter(ParamName.PARAM_NAME_MESSAGE);
        int trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_TRAINER_ID));
        int userId = ((User)(session.getAttribute(ParamName.USER_ATTRIBUTE))).getId();
        int rate = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_RATE));
        String redirect = request.getParameter(ParamName.REDIRECT);
        ReviewServiceImpl reviewService = new ReviewServiceImpl();
        boolean wasCreated ;
        Map<String,String> map = new HashMap<>();
        try {
            wasCreated = reviewService.createReview(userId,message,rate,trainerId,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_USER_REVIEW);
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            try {
                TrainerDaoImpl trainerDao = new TrainerDaoImpl();
                request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerDao.findAll());
                request.setAttribute("map",map);
                request.setAttribute("field_with_wrong",
                        MessageManager.getProperty("message.wrongregistration"));
            } catch (DaoException e) {
                throw new CommandException(e);
            }
        }
        return page;
    }
}
