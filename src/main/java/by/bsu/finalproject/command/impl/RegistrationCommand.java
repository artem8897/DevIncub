package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.service.impl.UserServiceImpl;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Registration in center command
 * @author A. Kuzmik
 */

public class RegistrationCommand implements ActionCommand {

    private UserServiceImpl userService = new UserServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page ;
        String password = request.getParameter(ParamName.PARAM_NAME_PASSWORD);
        String confirmedPassword = request.getParameter(ParamName.PARAM_NAME_CONFIRMED_PASSWORD2);
        String email = request.getParameter(ParamName.PARAM_NAME_EMAIL);
        String redirect = request.getParameter(ParamName.REDIRECT);
        String username = request.getParameter(ParamName.PARAM_NAME_USERNAME);
        String sex = request.getParameter(ParamName.PARAM_NAME_SEX);

        Map<String,String> map = new HashMap<>();

        boolean wasCreated ;
        try {
            wasCreated = userService.register(email,password,confirmedPassword,username,sex,map);
        } catch (ServiceException e) {
            throw new CommandException(e);
        }
        page = ConfigurationManager.getProperty(PathName.PATH_REGISTRATION_PAGE);
        if(wasCreated){
            request.setAttribute(ParamName.REDIRECT, redirect);
            //        EmailAcceptor emailAcceptor = new EmailAcceptor();
//       emailAcceptor.sendMessage(email);
        }else{
            if(map.isEmpty()){
                request.setAttribute(ParamName.WRONG_FIELDS, MessageManager.getProperty(MessageName.MESSAGE_WRONG_REGISTRATION));
            }else{
                request.setAttribute(ParamName.WRONG_FIELDS, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            }
            request.setAttribute(ParamName.STUDENTS, map);
        }

        return page;
    }
}
