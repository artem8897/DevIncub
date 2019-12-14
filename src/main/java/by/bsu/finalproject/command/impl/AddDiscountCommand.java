package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;

import javax.servlet.http.HttpServletRequest;


public class AddDiscountCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page ;
        String date = request.getParameter(ParamName.PARAM_NAME_DATE);
        int discount = Integer.parseInt(request.getParameter(ParamName.DISCOUNT));
        String redirect = request.getParameter(ParamName.REDIRECT);
        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_DISCOUNT);

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
            request.setAttribute("this email or username is already exist",
                    MessageManager.getProperty(MessageName.MESSAGE_WRONG_FIELDS));
        }
        return page;
    }
}

