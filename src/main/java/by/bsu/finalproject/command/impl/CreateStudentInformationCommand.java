package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.service.impl.StudentServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * Add students information command
 * @author A. Kuzmik
 */

public class CreateStudentInformationCommand implements ActionCommand {

    private StudentServiceImpl logic = new StudentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_PAGE_INFORMATION);

        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_ATTRIBUTE)));

        if(user != null) {

            String name = request.getParameter(ParamName.PARAM_NAME_NAME);
            String redirect = request.getParameter(ParamName.REDIRECT);
            String secondName = request.getParameter(ParamName.PARAM_NAME_SECOND_NAME);
            String weight = request.getParameter(ParamName.PARAM_NAME_WEIGHT);
            String height = request.getParameter(ParamName.PARAM_NAME_HEIGHT);
            String sex = request.getParameter(ParamName.PARAM_NAME_SEX);
            Map<String, String> map = new HashMap<>();

            int userId = user.getId();
            boolean wasCreated;
            try {
                wasCreated = logic.addInformation(userId, name, secondName, sex, weight, height, map);
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
            if (wasCreated) {
                request.setAttribute(ParamName.REDIRECT, redirect);
                session.setAttribute(ParamName.PARAM_NAME_USER_TYPE, user.getUserType().toString());
            } else {
                request.setAttribute(ParamName.MOV_ATTRIBUTE, ParamName.ADD);
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
                request.setAttribute(ParamName.STUDENTS, map);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}