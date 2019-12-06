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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainingDaoImpl implements TrainingDao<Integer, Training> {

    private static Logger logger = LogManager.getLogger(TrainingDaoImpl.class);

    @Override
    public Map<Integer, Training> findAll() throws DaoException {

        Map<Integer, Training> trainingMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = connection.prepareStatement(Query.SQL_SELECT_ALL_TRAININGS)){

            trainingMap = findTrainingMap(st);

        }catch (ConnectionPoolException | SQLException e){
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
            throw new DaoException(e);
        }
        return trainingMap;
    }

    public Map<Integer, Training> findAllInLimit(int currentPage, int recordPage,int userId) throws DaoException {

        Map<Integer, Training> trainings;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_TRAININGS)){

            statement.setInt(1,userId);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            trainings = findTrainingMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return trainings;
    }

    public Integer findNumberOfRows(int userId) throws DaoException {

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
            throw new DaoException(e);
        }
        return training;
    }

    @Override
    public boolean delete(Training entity) {
        return false;
    }

//    @Override
    public boolean create(Training entity) throws DaoException {

        int id ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection()) {

            connection.setAutoCommit(false);

            try{
                try(PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_TRAINING, Statement.RETURN_GENERATED_KEYS)){

                    statement.setString(1,entity.getDate());
                    statement.setString(2,entity.getTrainingType());
                    statement.setString(3,entity.getPersonality());
                    statement.executeUpdate();

                    try(ResultSet resultSet = statement.getGeneratedKeys()){

                        if(resultSet.next()){
                            //todo resultSet.first()
                            id = resultSet.getInt(1);
                            logger.info("Created training");
                        }else{
                            connection.rollback();
                            return false;
                        }
                    }
                }
                int insertedRow = 0;

                try(PreparedStatement secondStatement = connection.prepareStatement(Query.SQL_INSERT_USER_TRAINING)){

                    secondStatement.setInt(1, entity.getId());
                    secondStatement.setInt(2, id);
                    secondStatement.executeUpdate();
                    //todo
                    logger.info("Created users_training");
                }
                try(PreparedStatement secondStatement = connection.prepareStatement(Query.SQL_UPDATE_TRAINING_PAID)){

                    secondStatement.setInt(1, entity.getId());
                    secondStatement.executeUpdate();
                    logger.info("Created users_training");
                }

                connection.commit();

            }catch (SQLException e){
                connection.rollback();
                logger.error(e);
                return false;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return true;
    }

    @Override
    public boolean update(Training entity) throws DaoException {

         try (Connection connection = ConnectionPool.INSTANCE.getConnection();
              PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_TRAINING)) {
             try{
                 statement.setInt(4,entity.getId());
                 statement.setString(1, entity.getDate());
                 statement.setString(2, entity.getPersonality());
                 statement.setString(3,entity.getTrainingType());

                 statement.executeUpdate();
                 logger.info("updated type user");
                 return true;
             } catch (SQLException e) {
                 logger.error("wrong field");
             }
         }catch (ConnectionPoolException | SQLException e){
             throw new DaoException(e);
         }
         return false;
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
