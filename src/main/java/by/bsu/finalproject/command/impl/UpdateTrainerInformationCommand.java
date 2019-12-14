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
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

public class UpdateTrainerInformationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page;
        String trainingType = request.getParameter(ParamName.PARAM_TRAINING_TYPE);
        String name = request.getParameter(ParamName.PARAM_NAME_NAME);
        String redirect = request.getParameter(ParamName.REDIRECT);
        int workExperience = Integer.parseInt(request.getParameter(ParamName.PARAM_WORK_EXPERIENCE));
        int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER_INFORMATION);
        Map<String,String> map = new HashMap<>();
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = trainerService.updateTrainerInformation(userId,name,workExperience,trainingType,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT,redirect);
        }else{
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            request.setAttribute(ParamName.TRAINER_ATTRIBUTE,map);
            request.setAttribute(ParamName.USER_ID, userId);
        }
        return page;
    }
}
