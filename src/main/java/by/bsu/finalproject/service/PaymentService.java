package by.bsu.finalproject.service;

import by.bsu.finalproject.exception.ServiceException;

import java.util.Map;

/**
 * Basic interface for PaymentService.
 *
 * @author A. Kuzmik
 */

public interface PaymentService {

    /**
     * Calculate a price for user training at the specified userId
     * @param trainingAmount
     * @param userId
     * @return price
     * @throws ServiceException
     */

    double calcPriceForTraining(int userId, String trainingAmount) throws ServiceException;

    /**
     * Insert discount date
     * @param value
     * @param date
     * @return return boolean was discount date inserted
     * @throws ServiceException
     */

    boolean addDiscount(String date, String value) throws ServiceException;

    /**
     * pay a price for training at the specified userId and trainerId
     * @param trainerId
     * @param trainingAmount
     * @param sum
     * @param userId
     * @param cardNumber
     * @return return boolean was payed training
     * @throws ServiceException
     */

    boolean payTraining(double sum, String cardNumber, int userId, String trainingAmount, int trainerId) throws ServiceException;

    /**
     * Find all statuses
     * @return return Map statuses
     * @throws ServiceException
     */

    Map<Integer, String> finsPayStatuses() throws ServiceException;

}

