package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Update trainers information command
 * @author A. Kuzmik
 */

public class UpdateTrainerInformationCommand implements ActionCommand {

    private TrainerServiceImpl trainerService = new TrainerServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {

            String trainingType = request.getParameter(ParamName.PARAM_TRAINING_TYPE);
            String name = request.getParameter(ParamName.PARAM_NAME_NAME);
            String redirect = request.getParameter(ParamName.REDIRECT);
            String workExperience = request.getParameter(ParamName.PARAM_WORK_EXPERIENCE);
            int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));

            Map<String, String> map = new HashMap<>();

            boolean wasCreated;

            try {
                wasCreated = trainerService.updateTrainerInformation(userId, name, workExperience, trainingType, map);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }

            if (wasCreated) {
                request.setAttribute(ParamName.REDIRECT, redirect);
            } else {
                request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
                request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
                request.setAttribute(ParamName.TRAINER_ATTRIBUTE, map);
                request.setAttribute(ParamName.USER_ID, userId);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
