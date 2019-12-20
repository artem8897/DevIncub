package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.exception.LogicException;

import java.util.Map;

/**
 * Basic interface for ReviewService.
 *
 * @author A. Kuzmik
 */

public interface ReviewService {

    /**
     * Create students review at the specified userId and trainerId
     * @param userId
     * @param trainerId
     * @param userReview
     * @param rate
     * @param map
     * @return boolean was review created
     * @throws LogicException
     */

    boolean createReview(int userId, String userReview, int rate, int trainerId,  Map<String, String> map) throws LogicException;

    /**
     * Find reviews on trainer at the specified trainerId
     * @param userId
     * @param userType
     * @return map of reviews
     * @throws LogicException
     */

    Map<Integer, Review> findReview(int userId, UserType userType) throws LogicException;

}
