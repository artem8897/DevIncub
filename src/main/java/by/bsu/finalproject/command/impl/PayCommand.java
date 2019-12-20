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
 * Pay for training command
 * @author A. Kuzmik
 */

public class PayCommand implements ActionCommand {

    private PaymentServiceImpl paymentService = new PaymentServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        if(user != null) {

            double sum = Double.parseDouble(request.getParameter(ParamName.PRICE));
            String cardNumber = request.getParameter(ParamName.CARD);
            String redirect = request.getParameter(ParamName.REDIRECT);
            String amountOfTraining = request.getParameter(ParamName.TRAINING_AMOUNT);
            int trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_NAME_TRAINER_ID));


            try {
                boolean wasPaid = paymentService.payTraining(sum, cardNumber, user.getId(), amountOfTraining, trainerId);
                if (wasPaid) {
                    request.setAttribute(ParamName.REDIRECT, redirect);
                } else {
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.PROBLEMS_WITH_PAID));
                }
            } catch (LogicException e) {
                throw new CommandException(e);
            }
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;

    }
}
