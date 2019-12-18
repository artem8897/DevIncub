package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Add discount command
 * @author A. Kuzmik
 */

public class AddDiscountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_ADMIN_PAGE);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        if(user != null){

            String date = request.getParameter(ParamName.PARAM_NAME_DATE);
            int discount = Integer.parseInt(request.getParameter(ParamName.DISCOUNT));
            String redirect = request.getParameter(ParamName.REDIRECT);

            PaymentServiceImpl logic = new PaymentServiceImpl();

            boolean wasCreated ;

            try {
                wasCreated = logic.addDiscount(date, discount);
            } catch (LogicException e) {
                throw new CommandException(e);
            }
            if(wasCreated){
                request.setAttribute(ParamName.REDIRECT, redirect);
            }else{
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
            }
        } else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}

