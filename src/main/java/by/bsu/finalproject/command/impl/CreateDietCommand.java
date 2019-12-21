package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.service.impl.DietServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Add students diet command
 * @author A. Kuzmik
 */

public class CreateDietCommand implements ActionCommand {

    private DietServiceImpl logic = new DietServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_TRAINER);

        String dietType = request.getParameter(ParamName.PARAM_NAME_DIET_TYPE);
        String proteins = request.getParameter(ParamName.PARAM_NAME_PROTEINS);
        String redirect = request.getParameter(ParamName.REDIRECT);
        String fats = request.getParameter(ParamName.PARAM_NAME_FATS);
        String carbohydrates = request.getParameter(ParamName.PARAM_NAME_CARBOHYDRATES);
        int userId = Integer.parseInt(request.getParameter(ParamName.USER_ID));

        Map<String, String> map = new HashMap<>();

        boolean wasCreated;

        try {
            wasCreated = logic.addInformation(userId, dietType, carbohydrates, fats, proteins, map);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }

        if (wasCreated) {
            request.setAttribute(ParamName.REDIRECT, redirect);
        } else {
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            request.setAttribute(ParamName.DIET, map);
        }
        return page;
    }
}

