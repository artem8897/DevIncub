package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.PaymentDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for Payment.
 * @author A. Kuzmik
 */

public class PaymentDaoImpl implements PaymentDao {

    private static final Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

    public Double calcPrice(int userId, int trainingAmount) throws DaoException {

        double price = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_PRICE_FOR_TRAININGS);){

            statement.setInt(1,userId);
            statement.setInt(2,trainingAmount);

            try(ResultSet resultSet = statement.executeQuery()){

                if(resultSet.first()){
                    price = resultSet.getInt(1);
                }
            }
        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return price;
    }

    public boolean insertDiscountDate(String date, int discount) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_DISCOUNT)){

            statement.setString(1, date);
            statement.setInt(2, discount);
            int updatedRow = statement.executeUpdate();
            return updatedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
    }
    public boolean isDiscountDateExist(String date) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_DISCOUNT_DATE)){

            statement.setString(1, date);

            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.first();
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }
    public Map<Integer, String> findDiscountDates() throws DaoException {

        Map<Integer, String> discountMap = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_DISCOUNT_DATE_AND_PRICE);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                Integer id = resultSet.getInt(1);
                String value = resultSet.getString(2);
                discountMap.put(id, value);
            }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return discountMap;
    }

    public Map<Integer, String> findAllPayStatuses() throws DaoException {

        Map<Integer, String> statusMap = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_STATUS);
             ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    Integer id = resultSet.getInt(1);
                    String value = resultSet.getString(2);
                    statusMap.put(id,value);
                }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return statusMap;
    }

    public boolean payTraining(int card,int userId,double price,int trainingAmount,int trainerId) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){

            connection.setAutoCommit(false);

            try{
                int moneyOnCard = calcMoneyOnCard(connection, card);

                if(moneyOnCard>=price){
                    payProcess(connection, price, card);
                    createStudentsPaidTrainings(connection, userId, trainerId, trainingAmount);
                } else{
                    logger.info("no money");
                    return false;
                }
                connection.commit();
                logger.info("payed training");
                return true;

            }catch (SQLException e){
                connection.rollback();
                logger.error(e);
                return false;
            }
        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    private int calcMoneyOnCard(Connection connection, int card) throws SQLException {

        int moneyOnCard = 0;

        try( PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_MONEY_FROM_ACCOUNT)){
            statement.setInt(1,card);

            try(ResultSet resultSet = statement.executeQuery()){

                if(resultSet.first()){
                    moneyOnCard = resultSet.getInt(1);
                }
            }
        }
        return moneyOnCard;
    }

    private void payProcess(Connection connection, double price, int card) throws SQLException {

        try(PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_UPDATE_MONEY_CARD)){

            preparedStatement.setDouble(1,price);
            preparedStatement.setInt(2,card);

        }
        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CREATE_BANK_TRANSACTION_INFORMATION)){

            preparedStatement.setDouble(1,price);
            preparedStatement.setInt(2,card);
        }
    }
    private void createStudentsPaidTrainings(Connection connection, int userId, int trainerId, int trainingAmount) throws SQLException {

        boolean userHasTrainer = false ;
        int userTrainerKey = -1;

        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_FIND_USERS_TRAINER)){

            preparedStatement.setInt(1,userId);
            preparedStatement.setInt(2,trainerId);

            try(ResultSet resultSet = preparedStatement.executeQuery()){

               if(resultSet.first()){
                   userHasTrainer = true;
                   userTrainerKey = resultSet.getInt(1);
               }
            }
        }
        if(userHasTrainer){
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_UPDATE_PAID_TRAINING)){
                preparedStatement.setInt(1,trainingAmount);
                preparedStatement.setInt(2,userTrainerKey);
            }
        }else{
            try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CREATE_USERS_TRAINER_WITH_PAID_TRAININGS)){
                preparedStatement.setInt(1,trainingAmount);
                preparedStatement.setInt(2,userId);
                preparedStatement.setInt(3,trainerId);
            }
        }
    }

}
