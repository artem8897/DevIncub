package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Add students training command
 * @author A. Kuzmik
 */

public class AddPersonalTrainingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
        String periodicity = request.getParameter(ParamName.PARAM_NAME_DATE);
        String redirect = request.getParameter(ParamName.REDIRECT);
        String trainingType = request.getParameter(ParamName.PARAM_NAME_TRAINING_TYPE);
        String personality = request.getParameter(ParamName.PARAM_NAME_PERSONALITY);
        int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));

        Map<String,String> map = new HashMap<>();

        TrainingServiceImpl trainingService = new TrainingServiceImpl();
        boolean wasCreated;
        try {
            wasCreated = trainingService.createPersonalTrainingForUser(userId,periodicity,trainingType,personality,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
            request.setAttribute(ParamName.TRAINING, map);
            request.setAttribute(ParamName.INFO,
                    MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
        }
        return page;
    }
}