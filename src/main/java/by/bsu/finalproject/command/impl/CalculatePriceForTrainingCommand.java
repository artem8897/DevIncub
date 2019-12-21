package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * Calculate price for students command
 * @author A. Kuzmik
 */

public class CalculatePriceForTrainingCommand implements ActionCommand {

    private PaymentServiceImpl paymentService = new PaymentServiceImpl();
    private TrainerServiceImpl trainerService = new TrainerServiceImpl();

    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);

        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);

        if (user != null) {

            String amountOfTrainings = request.getParameter(ParamName.TRAINING_AMOUNT);
            Map<Integer, Trainer> trainerMap;

            try {
                trainerMap = trainerService.findAllTrainerMap();

                if (!trainerMap.isEmpty()) {
                    request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerMap);
                    double price = paymentService.calcPriceForTraining(user.getId(), amountOfTrainings);

                    if (price > 0) {
                        page = ConfigurationManager.getProperty(PathName.PATH_PAGE_PRICE_PAY);
                        request.setAttribute(ParamName.TRAINING_AMOUNT, amountOfTrainings);
                        request.setAttribute(ParamName.PRICE, price);
                    } else {
                        request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.PROBLEMS_WITH_PAID));
                    }
                } else {
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINERS_EXIST));
                }
            } catch (ServiceException e) {
                throw new CommandException(e);
            }
        } else {
            page = ConfigurationManager.getProperty(PathName.PATH_LOGIN_PAGE);
        }
        return page;
    }
}
