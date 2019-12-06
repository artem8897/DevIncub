package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UpdateTrainingCommand implements ActionCommand {

     private static final String MESSAGE_WRONG_REGISTRATION = "message.wrongregistration";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page ;
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        int trainingId = Integer.parseInt(request.getParameter("training_id"));

        String periodicity = request.getParameter(ParamName.PARAM_NAME_DATE);
        String personality = request.getParameter(ParamName.PARAM_NAME_PERSONALITY);
        String trainingType = request.getParameter(ParamName.PARAM_NAME_TRAINING_TYPE);
        String redirect = request.getParameter(ParamName.REDIRECT);

        Map<String,String> map = new HashMap<>();

        TrainingServiceImpl trainingService = new TrainingServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = trainingService.updateTraining(trainingId,personality,periodicity,trainingType,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty(PathName.PATH_TRAINING_PAGE);
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute("this email or username is already exist",
                    MessageManager.getProperty(MESSAGE_WRONG_REGISTRATION));
            request.setAttribute(ParamName.TRAINING,map);
        }
        return page;
    }
    }
