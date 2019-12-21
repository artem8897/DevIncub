package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.service.PaymentService;
import by.bsu.finalproject.validator.PaymentValidator;

import java.util.Map;

/**
 * Service for payment
 * @author A. Kuzmik
 */

public class PaymentServiceImpl implements PaymentService {

    private PaymentDaoImpl paymentDao = DaoFactory.INSTANCE.getPaymentDao();

    public boolean addDiscount(String date, String discount) throws ServiceException {

        boolean isValidDate = PaymentValidator.INSTANCE.isValidDate(date);
        boolean isValidValue = PaymentValidator.INSTANCE.isValidDiscount(discount);

        if(isValidDate && isValidValue) {

            int discountValue = Integer.parseInt(discount);

            try {
                boolean isExist = paymentDao.isDiscountDateExist(date);
                if (!isExist) {
                    return paymentDao.insertDiscountDate(date, discountValue);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }


    public double calcPriceForTraining(int userId, String trainingAmount) throws ServiceException {

        double price = 0;
        boolean isValidAmount = PaymentValidator.INSTANCE.isValidDiscount(trainingAmount);

        if(isValidAmount){

            int trainingAmountNumber = Integer.parseInt(trainingAmount);

            try {
                price = paymentDao.calcPrice(userId,trainingAmountNumber);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return price;
    }
    public boolean deleteDiscount(String date) throws ServiceException {

        boolean isValidDate = PaymentValidator.INSTANCE.isValidDate(date);

        if(isValidDate) {

            try {
                boolean isExist = paymentDao.isDiscountDateExist(date);
                if (isExist) {
                    return paymentDao.deleteDiscount(date);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }
    public boolean payTraining(double sum, String cardNumber, int userId, String trainingAmount, int trainerId) throws ServiceException {

        boolean isValidCard = PaymentValidator.INSTANCE.isValidCard(cardNumber);
        boolean isValidTrainingAmount = PaymentValidator.INSTANCE.isValidDiscount(trainingAmount);

        if (isValidCard && isValidTrainingAmount) {
            int validCardNumber = Integer.parseInt(cardNumber);
            int validTrainingAmount = Integer.parseInt(trainingAmount);
            try {
                return paymentDao.payTraining(validCardNumber, userId, sum, validTrainingAmount, trainerId);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    public Map<String, Integer> findDiscounts() throws ServiceException {

        try {
            return paymentDao.findDiscountDates();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public Map<Integer, String> selectStatuses() throws ServiceException {

        try {
            return paymentDao.findAllPayStatuses();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
