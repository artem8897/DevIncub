package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.entity.Review;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

public class ReviewDaoTest {


    private final String DATABASE_URL = ("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
    Connection connection;
    ReviewDao reviewDao = DaoFactory.INSTANCE.getReviewDao();


    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testCreatingReview() throws DaoException {

        int userId = 21;
        int secondUserId = 68;
        int trainerId = 9;

        Review firstUserReview = new Review();
        Review secondUserReview = new Review();

        firstUserReview.setRate(9);
        firstUserReview.setReview("userReview");

        secondUserReview.setReview("secondReview");
        secondUserReview.setRate(6);

        reviewDao.create(userId, trainerId, firstUserReview);
        reviewDao.create(secondUserId, trainerId, secondUserReview);

        Map<Integer, Review> expectedReviewMap = new HashMap<>(){{
            put(16, firstUserReview);
            put(17, secondUserReview);
        }};

        Map<Integer, Review> reviewMap = reviewDao.findAllByTrainer(userId);

        Assert.assertEquals(expectedReviewMap, reviewMap);


    }
}
