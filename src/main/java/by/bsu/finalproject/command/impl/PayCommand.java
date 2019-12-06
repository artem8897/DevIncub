package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.service.impl.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class PayCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        HttpSession session = request.getSession(true);
        User user = ((User)(session.getAttribute(ParamName.USER_SESSION)));
        double sum = Double.parseDouble(request.getParameter(ParamName.PRICE));
        int cardNumber = Integer.parseInt(request.getParameter(ParamName.CARD));
        String redirect = request.getParameter(ParamName.REDIRECT);
        int amountOfTraining = Integer.parseInt(request.getParameter(ParamName.TRAINING_AMOUNT));
        int trainerId = Integer.parseInt(request.getParameter(ParamName.PARAM_TRAINING_ID));
        PaymentService paymentService = new PaymentService();
        try {
            boolean wasPaid = paymentService.patTraining(sum,cardNumber,user.getId(),amountOfTraining,trainerId);
            if(wasPaid){
                request.setAttribute(ParamName.REDIRECT, redirect);
            }{
                request.setAttribute("info","message.some_problem_with_paid");
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);

    }
}
