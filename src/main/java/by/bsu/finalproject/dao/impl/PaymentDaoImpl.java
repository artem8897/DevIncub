package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PaymentDaoImpl {

    private static final Logger logger = LogManager.getLogger(PaymentDaoImpl.class);

    public Double calcPrice(int userId, int trainingAmount) throws DaoException {

        double price;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_PRICE_FOR_TRAININGS);){
            statement.setInt(1,userId);
            statement.setInt(2,trainingAmount);
            try(ResultSet resultSet = statement.executeQuery()){
                resultSet.next();
                price = resultSet.getInt(1);
                logger.info("Created review");
            } // todo ????????????????????????????????
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return price;
    }
    public boolean payTraining(int card,int userId,double price,int trainingAmount,int trainerId) throws DaoException {

        int moneyOnCard = -1;
        boolean doesTrainerAlreadyWorkWithStudent ;
        int userTrainerKey;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection()){

            connection.setAutoCommit(false);

            try( PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_MONEY_FROM_ACCOUNT)){
                statement.setInt(1,card);

                try(ResultSet resultSet = statement.executeQuery()){

                    if(resultSet.next()){
                        moneyOnCard = resultSet.getInt(1);
                    }
                }
                if(moneyOnCard>=price){

                    try(PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_UPDATE_MONEY_CARD)){

                        preparedStatement.setDouble(1,price);
                        preparedStatement.setInt(2,card);
                        preparedStatement.executeUpdate();
                    }
                    try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_FIND_USERS_TRAINER)){

                        preparedStatement.setInt(1,userId);
                        preparedStatement.setInt(2,trainerId);

                        try(ResultSet resultSet = preparedStatement.executeQuery()){

                            doesTrainerAlreadyWorkWithStudent = resultSet.next();
                            userTrainerKey = doesTrainerAlreadyWorkWithStudent?resultSet.getInt(1):-1;
                        // todo
                        }
                    }
                    if(doesTrainerAlreadyWorkWithStudent){
                        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_UPDATE_PAID_TRAINING)){
                            preparedStatement.setInt(1,trainingAmount);
                            preparedStatement.setInt(2,userTrainerKey);
                            preparedStatement.executeUpdate();
                        }
                    }else{
                        try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CREATE_USERS_TRAINER_WITH_PAID_TRAININGS)){
                            preparedStatement.setInt(1,trainingAmount);
                            preparedStatement.setInt(2,userId);
                            preparedStatement.setInt(3,trainerId);
                            preparedStatement.executeUpdate();
                            }
                    }
                    try (PreparedStatement preparedStatement = connection.prepareStatement(Query.SQL_CREATE_BANK_TRANSACTION_INFORMATION)){
                        preparedStatement.setDouble(1,price);
                        preparedStatement.setInt(2,card);
                        preparedStatement.executeUpdate();
                    }
                    connection.commit();
                    logger.info("payed training");
                    return true;
                }else{
                    connection.rollback();
                    logger.info("no money");
                    return false;
                }
            }catch (SQLException e){
                connection.rollback();
                logger.error(e);
                return false;
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }

}
