package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.ReviewDaoImpl;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.service.ReviewService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.ServiceException;
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

    public boolean createReview(int userId, String userReview, String rate, int trainerId, Map<String, String> map) throws ServiceException {

        boolean isValidReview = validateReview(userReview, rate, map);

        if(isValidReview){

            Review review = new Review();
            review.setRate(Integer.parseInt(rate));
            review.setReview(userReview);
            try {
                return reviewDao.create(userId, trainerId, review);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }else{
            return false;
        }
    }

    public Map<Integer, Review> findReview(int userId, UserType userType) throws ServiceException {

        Map<Integer, Review> reviewMap = new HashMap<>();
        try {
            if(userType.equals(UserType.USER)){
                reviewMap = reviewDao.findReviewsByUserId(userId);
            }else if(userType.equals(UserType.TRAINER)){
                reviewMap = reviewDao.findReviewsByTrainerId(userId);
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return reviewMap;
    }
    private boolean validateReview(String userReview, String rate, Map<String, String> map){

        boolean isValidReview = userReview != null && ReviewValidator.INSTANCE.isValidReview(userReview);

        if(isValidReview){
            map.put(ServiceName.REVIEW, userReview);
        }else{
            map.put(ServiceName.REVIEW,ServiceName.WRONG_FIELD);
        }

        boolean isValidRate = userReview != null && ReviewValidator.INSTANCE.isValidRate(rate);

        if(isValidRate){
            map.put(ServiceName.RATE, userReview);
        }else{
            map.put(ServiceName.RATE,ServiceName.WRONG_FIELD);
        }
        return isValidReview && isValidRate;
    }

}
