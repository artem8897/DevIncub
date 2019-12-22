package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for reviewDao.
 *
 * @author A. Kuzmik
 */


public interface ReviewDao {

    /**
     * Create students review at the specified userId and trainerId
     * @param userId
     * @param trainerId
     * @param review
     * @return boolean was review created
     * @throws DaoException
     */

     boolean create(int userId, int trainerId, Review review) throws DaoException;

    /**
     * Find reviews on trainer at the specified trainerId
     * @param id
     * @return map of reviews
     * @throws DaoException
     */

     Map<Integer, Review> findReviewsByTrainerId(Integer id) throws DaoException;

    /**
     * Find reviews by student at the specified userId
     * @param id
     * @return map of reviews
     * @throws DaoException
     */

     Map<Integer, Review> findReviewsByUserId(Integer id) throws DaoException;
}
