package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.command.PathName;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.CommandException;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.ConfigurationManager;
import by.bsu.finalproject.manager.MessageManager;
import by.bsu.finalproject.service.impl.PaymentService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CalcPriceForTrainingCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {
        String page;
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);
        int amountOfTrainings = Integer.parseInt(request.getParameter("amount of trainings"));
        PaymentService paymentService = new PaymentService();
        TrainerDaoImpl trainerDao = new TrainerDaoImpl();
        Map<Integer, Trainer> trainerMap;
        try {
            trainerMap = trainerDao.findAll();
        } catch (DaoException e) {
            throw new CommandException(e);
        }
        request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerMap);

        double price;
        try {
            price = paymentService.calcPriceForTraining(user.getId(),amountOfTrainings);
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        request.setAttribute(ParamName.TRAINING_AMOUNT, amountOfTrainings);
        request.setAttribute(ParamName.PRICE,price);
        if(trainerMap.isEmpty()){
            page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);
            request.setAttribute("message",
                    MessageManager.getProperty("message.no_trainers"));
        }else{
            page = ConfigurationManager.getProperty(PathName.PATH_PAGE_PRICE_PAY);
        }
        return page;
    }
}
