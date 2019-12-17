package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.ReviewDaoImpl;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.security.XssSecurity;
import by.bsu.finalproject.service.ReviewService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.validator.ReviewValidator;

import java.util.HashMap;
import java.util.Map;

/**
 * Service for review
 * @author A. Kuzmik
 */

public class ReviewServiceImpl implements ReviewService {

    private ReviewDaoImpl reviewDao = DaoFactory.INSTANCE.getReviewDao();

    public boolean createReview(int userId, String userReview, int rate, int trainerId, Map map) throws LogicException {

        Review review = new Review();
        userReview = XssSecurity.protectFromXssAttack(userReview);
        review.setRate(rate);
        review.setReview(userReview);

        boolean isValidReview = validateReview(review, map);

        if(isValidReview){
            try {
                return reviewDao.create(userId, trainerId, review);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }

    public Map<Integer, Review> findReview(int userId, UserType userType) throws LogicException {

        Map<Integer, Review> reviewMap = new HashMap<>();

        try {
            switch (userType){
                case USER: reviewMap = reviewDao.findAllByUser(userId);
                break;
                case TRAINER:  reviewMap = reviewDao.findAllByTrainer(userId);
                break;
            }
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return reviewMap;
    }
    private boolean validateReview(Review review, Map<String, String> map){

        boolean isValidReview = review.getReview() != null && ReviewValidator.INSTANCE.isValidReview(review.getReview());

        if(isValidReview){
            map.put(ServiceName.REVIEW, review.getReview());
        }else{
            map.put(ServiceName.REVIEW,ServiceName.WRONG_FIELD);
        }

        boolean isValidRate = ReviewValidator.INSTANCE.isValidRate(review.getRate());

        if(isValidRate){
            map.put(ServiceName.RATE, Integer.toString(review.getRate()));
        }else{
            map.put(ServiceName.RATE,ServiceName.WRONG_FIELD);
        }

        return isValidReview && isValidRate;
    }

}
