package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;

public class PaymentService {

    public double calcPriceForTraining(int userId, int trainingAmount) throws LogicException {
        //toDo valll
        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        try {
            return paymentDao.calcPrice(userId,trainingAmount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public boolean patTraining(double sum,int cardNumber,int userId,int trainingAmount,int trainerId) throws LogicException {

        PaymentDaoImpl paymentDao = new PaymentDaoImpl();
        try {
            return paymentDao.payTraining(cardNumber,userId,sum,trainingAmount, trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
