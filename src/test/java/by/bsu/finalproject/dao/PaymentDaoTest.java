package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.PaymentDaoImpl;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.manager.DatabaseResourceManager;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.*;
import java.util.Map;

public class PaymentDaoTest {

    private final String DATABASE_URL = ("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
    PaymentDaoImpl paymentDao = DaoFactory.INSTANCE.getPaymentDao();
    Connection connection;
    PreparedStatement statement;
    ResultSet resultSet;

    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws SQLException, ConnectionPoolException {
       ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void addDiscount() throws DaoException, SQLException {
        String date = "2019-11-16";
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
    public double calcPriceForTraining(int userId, int trainingAmount) throws LogicException {

        try {
            return paymentDao.calcPrice(userId,trainingAmount);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public boolean payTraining(double sum, int cardNumber, int userId, int trainingAmount, int trainerId) throws LogicException {

        try {
            return paymentDao.payTraining(cardNumber,userId,sum,trainingAmount, trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Map<Integer, String> selectStatuses() throws LogicException {
        try {
            return paymentDao.findAllPayStatuses();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

//
//    @Test
//    void testRemoveEntity() throws SQLException, EntityRepositoryException {
//        statement = connection.prepareStatement(SQL_SELECT_USER);
//        statement.setString(1,"mail@gmail.com");
//        resultSet = statement.executeQuery();
//        User user = new User();
//        while (resultSet.next()) {
//            user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
//            user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
//            user.setId(resultSet.getInt(ColumnName.ID));
//            user.setPass(resultSet.getString(ColumnName.PASSWORD));
//            user.setEmail(resultSet.getString(ColumnName.EMAIL));
//            user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
//        }
//        userRepository.removeEntity(user);
//        statement = connection.prepareStatement(SQL_SELECT_USER);
//        statement.setString(1,"mail@gmail.com");
//        resultSet = statement.executeQuery();
//        User actual = new User();
//        while (resultSet.next()) {
//            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
//            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
//            actual.setId(resultSet.getInt(ColumnName.ID));
//            actual.setPass(resultSet.getString(ColumnName.PASSWORD));
//            actual.setEmail(resultSet.getString(ColumnName.EMAIL));
//            actual.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
//        }
//        userRepository.addEntity(user);
//        User expected = new User();
//        Assert.assertEquals(actual,expected);
//    }
//
//    @Test
//    void testQuery() throws EntityRepositoryException, SQLException {
//        List<User> actual = userRepository.query(new UserEmailSpecification("mail@gmail.com"));
//        List<User> expected = new ArrayList<>();
//        statement = connection.prepareStatement(SQL_SELECT_USER);
//        statement.setString(1,"mail@gmail.com");
//        resultSet = statement.executeQuery();
//        User user = new User();
//        while (resultSet.next()) {
//            user.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
//            user.setLastName(resultSet.getString(ColumnName.LAST_NAME));
//            user.setId(resultSet.getInt(ColumnName.ID));
//            user.setPass(resultSet.getString(ColumnName.PASSWORD));
//            user.setEmail(resultSet.getString(ColumnName.EMAIL));
//            user.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
//            expected.add(user);
//        }
//        Assert.assertEquals(actual,expected);
//    }
//
//    @Test
//    void testUpdateEntity() throws SQLException, EntityRepositoryException {
//        statement = connection.prepareStatement(SQL_SELECT_USER);
//        statement.setString(1,"mail@gmail.com");
//        resultSet = statement.executeQuery();
//        User actual = new User();
//        while (resultSet.next()) {
//            actual.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
//            actual.setLastName(resultSet.getString(ColumnName.LAST_NAME));
//            actual.setId(resultSet.getInt(ColumnName.ID));
//            actual.setPass(resultSet.getString(ColumnName.PASSWORD));
//            actual.setEmail(resultSet.getString(ColumnName.EMAIL));
//            actual.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
//        }
//        actual.setLastName("last" + new Random().nextInt(10000));
//        actual.setFirstName("first" + new Random().nextInt(10000));
//        userRepository.updateEntity(actual);
//        statement = connection.prepareStatement(SQL_SELECT_USER);
//        statement.setString(1,"mail@gmail.com");
//        resultSet = statement.executeQuery();
//        User expected = new User();
//        while (resultSet.next()) {
//            expected.setFirstName(resultSet.getString(ColumnName.FIRST_NAME));
//            expected.setLastName(resultSet.getString(ColumnName.LAST_NAME));
//            expected.setId(resultSet.getInt(ColumnName.ID));
//            expected.setPass(resultSet.getString(ColumnName.PASSWORD));
//            expected.setEmail(resultSet.getString(ColumnName.EMAIL));
//            expected.setRole(UserRole.valueOf(resultSet.getString(ColumnName.ROLE).toUpperCase()));
//        }
//        Assert.assertEquals(actual,expected);
//    }
}
