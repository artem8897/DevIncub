package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainingServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Delete students training command
 * @author A. Kuzmik
 */

public class DeleteTrainingCommand implements ActionCommand {

    private TrainingServiceImpl trainingService = new TrainingServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);

        int trainingId = Integer.parseInt(request.getParameter(ParamName.PARAM_TRAINING_ID));
        String redirect = request.getParameter(ParamName.REDIRECT);

        boolean wasCreated;

        try {
            wasCreated = trainingService.deleteTraining(trainingId);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if (wasCreated) {
            request.setAttribute(ParamName.REDIRECT, redirect);
        } else {
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
        }
        return page;
    }
}
