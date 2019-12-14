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

public class UpdateDietCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_DIET_PAGE);
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
        int userId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_USER_ID));
        String dietType = request.getParameter(ParamName.PARAM_NAME_DIET_TYPE);
        int proteins = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_PROTEINS));
        String redirect = request.getParameter(ParamName.REDIRECT);
        int fats = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_FATS));
        int carbohydrates = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_CARBOHYDRATES));

        Map<String,String> map = new HashMap<>();

        DietServiceImpl dietService = new DietServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = dietService.updateDiet(userId,dietType,carbohydrates,fats,proteins,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.UPDATE);
            request.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            request.setAttribute(ParamName.DIET,map);
            request.setAttribute(ParamName.USER_ID, userId);
        }
        return page;
    }
}
