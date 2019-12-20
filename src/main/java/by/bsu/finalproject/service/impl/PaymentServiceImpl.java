package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.PaymentService;
import by.bsu.finalproject.validator.PaymentValidator;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for payment
 * @author A. Kuzmik
 */

public class PaymentServiceImpl implements PaymentService {

    private PaymentDaoImpl paymentDao = DaoFactory.INSTANCE.getPaymentDao();

    private static final String REGULAR_NUMBER = "\\d{1,9}";

    public boolean addDiscount(String date, String discount) throws LogicException {

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
                throw new LogicException(e);
            }
        }
        return false;
    }

    public double calcPriceForTraining(int userId, String trainingAmount) throws LogicException {

        double price = 0;

        if(isValidNumber(trainingAmount)){

            int trainingAmountNumber = Integer.parseInt(trainingAmount);

            try {
                price = paymentDao.calcPrice(userId,trainingAmountNumber);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }
        return price;
    }
    public boolean payTraining(double sum, String cardNumber, int userId, String trainingAmount, int trainerId) throws LogicException {

        boolean isValidCard = PaymentValidator.INSTANCE.isValidCard(cardNumber);
        boolean isValidTrainingAmount = PaymentValidator.INSTANCE.isValidDiscount(trainingAmount);

        if (isValidCard && isValidTrainingAmount) {
            int validCardNumber = Integer.parseInt(cardNumber);
            int validTrainingAmount = Integer.parseInt(trainingAmount);
            try {
                return paymentDao.payTraining(validCardNumber, userId, sum, validTrainingAmount, trainerId);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }
        return false;
    }

    public Map<Integer, String> selectStatuses() throws LogicException {

        try {
            return paymentDao.findAllPayStatuses();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    private boolean isValidNumber(String number){
        Pattern pat = Pattern.compile(REGULAR_NUMBER);
        Matcher matcher = pat.matcher(number);
        return matcher.matches();
    }
}
