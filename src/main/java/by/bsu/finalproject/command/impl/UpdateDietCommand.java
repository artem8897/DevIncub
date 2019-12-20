package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.DietServiceImpl;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Update diet for students command
 * @author A. Kuzmik
 */

public class UpdateDietCommand implements ActionCommand {

    private DietServiceImpl dietService = new DietServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_DIET_PAGE);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));

        if(user != null) {

            String dietType = request.getParameter(ParamName.PARAM_NAME_DIET_TYPE);
            String proteins = request.getParameter(ParamName.PARAM_NAME_PROTEINS);
            String redirect = request.getParameter(ParamName.REDIRECT);
            String fats = request.getParameter(ParamName.PARAM_NAME_FATS);
            String carbohydrates = request.getParameter(ParamName.PARAM_NAME_CARBOHYDRATES);
            int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));

            Map<String, String> map = new HashMap<>();

            boolean wasCreated;

            try {
                wasCreated = dietService.updateDiet(userId, dietType, carbohydrates, fats, proteins, map);
            } catch (LogicException e) {
                throw new CommandException(e);
            }

            if (wasCreated) {
                request.setAttribute(ParamName.REDIRECT, redirect);
            } else {
                request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
                request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
                request.setAttribute(ParamName.DIET, map);
                request.setAttribute(ParamName.USER_ID, userId);
            }
        }else{
           page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
