package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.service.impl.InformationServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Add students information command
 * @author A. Kuzmik
 */

public class AddStudentInformationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);
        HttpSession session = request.getSession(true);
        String name = request.getParameter(ParamName.PARAM_NAME_NAME);
        String redirect = request.getParameter(ParamName.REDIRECT);
        String secondName = request.getParameter(ParamName.PARAM_NAME_SECOND_NAME);
        int weight = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_WEIGHT));
        int height = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_HEIGHT));
        String sex = request.getParameter(ParamName.PARAM_NAME_SEX);
        Map<String,String> map = new HashMap<>();

        User user = ((User)(session.getAttribute(ParamName.USER_ATTRIBUTE)));
        int userId = user.getId();
        InformationServiceImpl logic = new InformationServiceImpl();
        boolean wasCreated;
        try {
            wasCreated = logic.addInformation(userId,name,secondName,sex,weight,height,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
            session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
        }else{
            request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
            request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            request.setAttribute(ParamName.STUDENTS, map);
        }
        return page;
    }
}