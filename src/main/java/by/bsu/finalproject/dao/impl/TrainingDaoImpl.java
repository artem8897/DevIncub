package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.dao.TrainingDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for Training.
 * @author A. Kuzmik
 */

public class TrainingDaoImpl implements TrainingDao<Integer, Training> {

    private static Logger logger = LogManager.getLogger(TrainingDaoImpl.class);

    @Override
    public Map<Integer, Training> findAll() throws DaoException {

        Map<Integer, Training> trainingMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = connection.prepareStatement(Query.SQL_SELECT_ALL_TRAININGS)){

            trainingMap = findTrainingMap(st);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return trainingMap;
    }
    public Map<Integer, Training> findUsersTrainingMap(int userId) throws DaoException {

        Map<Integer, Training> trainingMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_TRAININGS)){

            statement.setInt(1,userId);

            trainingMap = findTrainingMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return trainingMap;
    }

    public boolean deleteTraining(Integer trainingId) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_DELETE_TRAINING)){

            statement.setInt(1,trainingId);
            int updatedRow = statement.executeUpdate();
            return updatedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    public Map<Integer, Training> findUsersTrainings(int currentPage, int recordPage, int userId) throws DaoException {

        Map<Integer, Training> trainings;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_TRAININGS)){

            statement.setInt(1,userId);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            trainings = findTrainingMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return trainings;
    }

    public Integer findNumberOfTrainings(int userId) throws DaoException {

        int number = 0 ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_TRAINING_COUNT)){

            statement.setInt(1,userId);

            try (ResultSet resultSet = statement.executeQuery()){

                if(resultSet.next()){
                    number = resultSet.getInt(TablesColumnName.COUNT);
                }
            }

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Training findTrainingById(int trainingId) throws DaoException {

        Training training = new Training();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_TRAINING_BY_ID)){

            st.setInt(1, trainingId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.next()) {

                    training.setId(resultSet.getInt(TablesColumnName.TRAINING_ID));
                    training.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                    training.setPeriodicity(resultSet.getString(TablesColumnName.DATE));
                    training.setPersonality(resultSet.getString(TablesColumnName.PERSONALITY));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
        return training;
    }


    public boolean createTraining(Integer userId, Training entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {

            connection.setAutoCommit(false);

            try{

                int trainingId ;

                try(PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_TRAINING, Statement.RETURN_GENERATED_KEYS)) {

                    statement.setString(1, entity.getDate());
                    statement.setString(2, entity.getTrainingType());
                    statement.setString(3, entity.getPersonality());
                    statement.executeUpdate();

                    try (ResultSet resultSet = statement.getGeneratedKeys()) {

                        if (resultSet.first()) {
                            trainingId = resultSet.getInt(1);
                            logger.info("Created training");
                        } else {
                            connection.rollback();
                            return false;
                        }
                    }
                }

                boolean wasTrainingInserted = insertUserTraining(connection, userId, trainingId);
                boolean wasPaymentUpdated = updateUserPaid(connection, userId);

                if(wasTrainingInserted && wasPaymentUpdated){
                    connection.commit();
                    return true;
                }else{
                    connection.rollback();
                    return false;
                }
            }catch (SQLException e){
                connection.rollback();
                logger.catching(e);
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
    }
    private boolean insertUserTraining(Connection connection, int userId, int trainingId) throws SQLException {

        try(PreparedStatement secondStatement = connection.prepareStatement(Query.SQL_INSERT_USER_TRAINING)){

            secondStatement.setInt(1, userId);
            secondStatement.setInt(2, trainingId);
            int insertedRow = secondStatement.executeUpdate();
            logger.info("Created users_training");
            return insertedRow > 0;

        }
    }
    private boolean updateUserPaid(Connection connection, int userId) throws SQLException {

        try(PreparedStatement secondStatement = connection.prepareStatement(Query.SQL_UPDATE_TRAINING_PAID)){

            secondStatement.setInt(1, userId);
            int updatedRow  = secondStatement.executeUpdate();
            logger.info("updated users_paid");
            return updatedRow > 0;

        }
    }

    @Override
    public boolean update(Training entity) throws DaoException {

         try (Connection connection = ConnectionPool.INSTANCE.getConnection();
              PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_TRAINING)) {

                 statement.setInt(4,entity.getId());
                 statement.setString(1, entity.getDate());
                 statement.setString(2, entity.getPersonality());
                 statement.setString(3,entity.getTrainingType());

                 int updatedRow = statement.executeUpdate();
                 return updatedRow > 0;

         }catch (ConnectionPoolException | SQLException e){
             throw new DaoException(e);
         }
     }

     private Map<Integer, Training> findTrainingMap(PreparedStatement statement) throws SQLException {

        Map<Integer, Training> trainingMap = new HashMap<>();

         try(ResultSet resultSet = statement.executeQuery()){
             while (resultSet.next()) {
                 Training training = new Training();
                 training.setId(resultSet.getInt(TablesColumnName.TRAINING_ID));
                 training.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                 training.setPeriodicity(resultSet.getString(TablesColumnName.DATE));
                 trainingMap.put(training.getId(),training);
             }
         }
         return trainingMap;
    }
}
