package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class RegistrationCommand implements ActionCommand {

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page ;
        String password = request.getParameter(ParamName.PARAM_NAME_PASSWORD);
        String confirmedPassword = request.getParameter(ParamName.PARAM_NAME_CONFIRMED_PASSWORD2);
        String email = request.getParameter(ParamName.PARAM_NAME_EMAIL);
        String redirect = request.getParameter(ParamName.REDIRECT);
//        EmailAcceptor emailAcceptor = new EmailAcceptor();
//        emailAcceptor.sendMessage(email);
        String username = request.getParameter(ParamName.PARAM_NAME_USERNAME);
        String sex = request.getParameter(ParamName.PARAM_NAME_SEX);

        Map<Object,Boolean> map = new HashMap<>();

        UserServiceImpl userService = new UserServiceImpl();
        boolean wasCreated ;
        try {
            wasCreated = userService.register(email,password,confirmedPassword,username,sex,map);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty(PathName.PATH_REGISTRATION_PAGE);
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
        }else{
            if(map.isEmpty()){
                request.setAttribute("wrong_fields", MessageManager.getProperty(MessageName.MESSAGE_WRONG_REGISTRATION));
            }else{
                request.setAttribute("wrong_fields", MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            }
            request.setAttribute(ParamName.STUDENTS, map);
        }

        return page;
    }
}
