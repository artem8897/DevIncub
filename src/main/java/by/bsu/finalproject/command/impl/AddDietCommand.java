package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.service.impl.DietServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class AddDietCommand implements ActionCommand {

    private static final String MESSAGE_WRONG_REGISTRATION = "message.wrongregistration";

    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page ;
        String dietType = request.getParameter(ParamName.PARAM_NAME_DIET_TYPE);
        int proteins = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_PROTEINS));
        String redirect = request.getParameter(ParamName.REDIRECT);
        int fats = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_FATS));
        int carbohydrates = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_CARBOHYDRATES));
        int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));
        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);
        Map<String,String> map = new HashMap<>();

        DietServiceImpl logic = new DietServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = logic.addInformation(userId, dietType,carbohydrates,fats,proteins,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            request.setAttribute("this email or username is already exist",
                    MessageManager.getProperty(MESSAGE_WRONG_REGISTRATION));
            request.setAttribute(ParamName.DIET,map);
        }
        return page;
    }
}

