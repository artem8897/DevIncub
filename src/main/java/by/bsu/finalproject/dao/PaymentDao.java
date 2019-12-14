package by.bsu.finalproject.dao;

import by.bsu.finalproject.exception.DaoException;

/**
 * Basic interface for PaymentDao.
 *
 * @author A. Kuzmik
 */

public interface PaymentDao {

    /**
     * Calculate a price for user training at the specified userId
     * @param trainingAmount
     * @param userId
     * @return price
     */

     Double calcPrice(int userId, int trainingAmount) throws DaoException;

    /**
     * Insert discount date
     * @param discount
     * @param date
     * @return return boolean was discount date inserted
     */

     boolean insertDiscountDate(String date, int discount) throws DaoException;

    /**
     * pay a price for training at the specified userId and trainerId
     * @param trainerId
     * @param trainingAmount
     * @param price
     * @param userId
     * @param card
     * @return return boolean was payed training
     */

     boolean payTraining(int card,int userId,double price,int trainingAmount,int trainerId) throws DaoException;


    }
