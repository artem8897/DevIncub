package by.bsu.finalproject.command.impl;

import by.bsu.finalproject.command.ActionCommand;
import by.bsu.finalproject.command.MessageName;
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
import by.bsu.finalproject.service.TrainerService;
import by.bsu.finalproject.service.impl.PaymentServiceImpl;
import by.bsu.finalproject.service.impl.TrainerServiceImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

public class CalcPriceForTrainingCommand implements ActionCommand {
    @Override
    public String execute(HttpServletRequest request) throws CommandException {

        String page = ConfigurationManager.getProperty(PathName.PATH_USER_PAGE);
        HttpSession session = request.getSession(true);
        User user = (User) session.getAttribute(ParamName.USER_ATTRIBUTE);
        int amountOfTrainings = Integer.parseInt(request.getParameter(ParamName.TRAINING_AMOUNT));
        PaymentServiceImpl paymentService = new PaymentServiceImpl();
        TrainerServiceImpl trainerService = new TrainerServiceImpl();
        Map<Integer, Trainer> trainerMap;
        try {
            trainerMap = trainerService.findAllTrainerMap();
            if(!trainerMap.isEmpty()){
                request.setAttribute(ParamName.TRAINER_ATTRIBUTE, trainerMap);
                double price = paymentService.calcPriceForTraining(user.getId(),amountOfTrainings);

                if(price > 0){
                    page = ConfigurationManager.getProperty(PathName.PATH_PAGE_PRICE_PAY);
                    request.setAttribute(ParamName.TRAINING_AMOUNT, amountOfTrainings);
                    request.setAttribute(ParamName.PRICE,price);
                }else{
                    request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.PROBLEMS_WITH_PAID));
                }
            }else{
                request.setAttribute(ParamName.INFO, MessageManager.getProperty(MessageName.NO_TRAINERS_EXIST));
            }
        } catch (LogicException e) {
            throw new CommandException(e);
        }
        return page;
    }
}
