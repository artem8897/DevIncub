package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.ReviewDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for Review.
 * @author A. Kuzmik
 */


public class ReviewDaoImpl implements ReviewDao {

    private static final Logger logger = LogManager.getLogger(ReviewDaoImpl.class);

    public boolean create(int userId, int trainerId, Review entity) throws DaoException {

        int reviewId ;
        int createdRow ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {

            connection.setAutoCommit(false);

            try{

                try(PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS)){

                    statement.setString(1,entity.getReview());
                    statement.setInt(2,entity.getRate());
                    statement.executeUpdate();

                    try(ResultSet resultSet = statement.getGeneratedKeys()){

                        if(resultSet.first()){
                            reviewId = resultSet.getInt(1);
                            logger.info("Created review");
                        }else {
                            connection.rollback();
                            logger.error("Did not created");
                            return false;
                        }
                    }
                }
                try(PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_USERS_REVIEW)){

                    statement.setInt(1, userId);
                    statement.setInt(2, trainerId);
                    statement.setInt(3, reviewId);
                    createdRow = statement.executeUpdate();
                }
                if(createdRow > 0){
                    connection.commit();
                    logger.info("Created users_review");
                    return true;
                }else{
                    connection.rollback();
                    logger.error("Did not created users_review");
                    return false;
                }
            }catch (SQLException e){
                logger.error(e);
                connection.rollback();
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public Map<Integer, Review> findReviewsByTrainerId(Integer id) throws DaoException {

        Map<Integer, Review> reviewMap;
        String sqlQuery = Query.SQL_SELECT_TRAINERS_REVIEW;

        try {
            reviewMap = findReviewMap(sqlQuery,id);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reviewMap;
    }
    public Map<Integer, Review> findReviewsByUserId(Integer id) throws DaoException {

        Map<Integer, Review> reviewMap;
        String sqlQuery = Query.SQL_SELECT_USERS_REVIEW;

        try {
            reviewMap = findReviewMap(sqlQuery,id);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return reviewMap;

    }

    private Map<Integer, Review> findReviewMap(String query, Integer id) throws SQLException, ConnectionPoolException {

        HashMap<Integer, Review> mapReview = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(query)){

            statement.setInt(1,id);

            try(ResultSet resultSet = statement.executeQuery()){

                while(resultSet.next()){
                    Review review = new Review();
                    review.setReviewId(resultSet.getInt(TablesColumnName.REVIEW_ID));
                    review.setReview(resultSet.getString(TablesColumnName.REVIEW));
                    review.setRate(resultSet.getInt(TablesColumnName.RATE));
                    mapReview.put(review.getReviewId(),review);
                }
            }
            return mapReview;
        }
    }
}
