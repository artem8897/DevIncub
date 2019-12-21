package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class PaymentDaoTest {

    private PaymentDaoImpl paymentDao = DaoFactory.INSTANCE.getPaymentDao();
    private Connection connection;
    private PreparedStatement statement;
    private ResultSet resultSet;

    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
       ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testAddDiscount() throws DaoException, SQLException {
        String date = "2019-12-26";
        int value = 20;
        int actualValue = 0;
        paymentDao.insertDiscountDate(date, value);
        statement = connection.prepareStatement("SELECT discount_date.discount_amount FROM discount_date WHERE date = ?");
        statement.setString(1,  date);
        resultSet = statement.executeQuery();
        if(resultSet.first()){
            actualValue = resultSet.getInt(1);
        }
        Assert.assertEquals(value, actualValue);

    }

    @Test
    public void testCalcPriceForTraining() throws DaoException {

        double expectedPrice = 60.0;

        double actualPrice = paymentDao.calcPrice(68,8);

        Assert.assertEquals(expectedPrice, actualPrice);
    }

    @Test
    public void testSelectStatuses() throws DaoException {

        Map<Integer, String> expectedStatuses = new HashMap<>(){{
            put(1, "basic");
            put(2, "corporate");
            put(3, "premium");
            put(4, "lux");
        }};

        Map<Integer, String> actualStatuses = paymentDao.findAllPayStatuses();

        Assert.assertEquals(actualStatuses, expectedStatuses);

    }


}
