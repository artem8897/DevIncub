package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.PaymentService;

import java.util.Map;

/**
 * Service for payment
 * @author A. Kuzmik
 */

public class PaymentServiceImpl implements PaymentService {

    PaymentDaoImpl paymentDao = DaoFactory.INSTANCE.getPaymentDao();

    public boolean addDiscount(String date, int value) throws LogicException {

        if(value>0 && value<1000 && date != null ){
            try {
                boolean isExist = paymentDao.searchDiscountDate(date);
                if(!isExist){
                    return paymentDao.insertDiscountDate(date, value);
                }
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }
        return false;
    }

    public double calcPriceForTraining(int userId, int trainingAmount) throws LogicException {

        try {
            return paymentDao.calcPrice(userId,trainingAmount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public boolean payTraining(double sum, int cardNumber, int userId, int trainingAmount, int trainerId) throws LogicException {

        if(sum < 0 || cardNumber < 0 || trainingAmount < 0){
            return false;
        }
        try {
            return paymentDao.payTraining(cardNumber,userId,sum,trainingAmount, trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Map<Integer, String> selectStatuses() throws LogicException {
        try {
            return paymentDao.findAllPayStatuses();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
}
