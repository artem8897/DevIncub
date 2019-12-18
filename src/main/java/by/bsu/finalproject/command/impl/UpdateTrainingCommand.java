package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Update students training command
 * @author A. Kuzmik
 */

public class UpdateTrainingCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_TRAINING_PAGE);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {

            int trainingId = Integer.parseInt(request.getParameter(ParamName.PARAM_TRAINING_ID));

            String periodicity = request.getParameter(ParamName.PARAM_NAME_DATE);
            String personality = request.getParameter(ParamName.PARAM_NAME_PERSONALITY);
            String trainingType = request.getParameter(ParamName.PARAM_NAME_TRAINING_TYPE);
            String redirect = request.getParameter(ParamName.REDIRECT);

            Map<String, String> map = new HashMap<>();

            TrainingServiceImpl trainingService = new TrainingServiceImpl();

            boolean wasCreated;

            try {
                wasCreated = trainingService.updateTraining(trainingId, personality, periodicity, trainingType, map);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
            if (wasCreated) {
                request.setAttribute(ParamName.REDIRECT, redirect);
            } else {
                request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
                request.setAttribute(ParamName.TRAINING, map);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
    }
