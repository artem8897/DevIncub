package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Create trainer information command
 * @author A. Kuzmik
 */

public class CreateTrainerInformationCommand implements ActionCommand {

     private static final String MESSAGE_WRONG_REGISTRATION = "message.wrongregistration";

     @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page ;
        HttpSession session = request.getSession(true);

        String name = request.getParameter(ParamName.PARAM_NAME_NAME);
        String trainingType = request.getParameter(ParamName.PARAM_TRAINING_TYPE);
        String redirect = request.getParameter(ParamName.REDIRECT);
        int workExperience = Integer.parseInt(request.getParameter(ParamName.PARAM_WORK_EXPERIENCE));
        User user = ((User)(session.getAttribute(ParamName.USER_ATTRIBUTE)));
        int userId = user.getId();
        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);

        Map<String,String> map = new HashMap<>();

        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = trainerService.createTrainer(userId,name,workExperience,trainingType,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,map);
            request.setAttribute("this email or username is already exist",
                    MessageManager.getProperty(MESSAGE_WRONG_REGISTRATION));
        }
        return page;
    }
}
