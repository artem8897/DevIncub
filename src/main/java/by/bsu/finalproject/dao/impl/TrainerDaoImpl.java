package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.command.ParamName;
import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.dao.TrainerDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Data access object for Trainer.
 * @author A. Kuzmik
 */

public class TrainerDaoImpl implements TrainerDao<Integer, Trainer> {

    private static final Logger logger = LogManager.getLogger(TrainerDaoImpl.class);

    @Override
    public Map<Integer,Trainer> findAll() throws DaoException {

        Map<Integer, Trainer> trainers = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_TRAINERS);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setId(resultSet.getInt(TablesColumnName.TRAINER_ID));
                trainer.setTrainerName(resultSet.getString(TablesColumnName.NAME));
                trainer.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                trainer.setWorkExperience(resultSet.getInt(TablesColumnName.WORK_EXPERIENCE));
                trainers.put(trainer.getId(),trainer);
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return trainers;
    }
    public boolean isCreated(Integer id) throws DaoException {

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = cn.prepareStatement(Query.SQL_SELECT_TRAINER_BY_ID)){

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                return resultSet.first();
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DaoException(e);
        }
    }
    public List<Trainer> findAllInLimit(int currentPage, int recordPage) throws DaoException {

        List<Trainer> trainers = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_TRAINERS)){

            statement.setInt(1,currentPage);
            statement.setInt(2,recordPage);

            try(ResultSet resultSet = statement.executeQuery()){

                while (resultSet.next()) {
                    Trainer trainer = new Trainer();
                    trainer.setId(resultSet.getInt(TablesColumnName.TRAINER_ID));
                    trainer.setTrainerName(resultSet.getString(ParamName.PARAM_NAME_NAME));
                    trainer.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                    trainer.setWorkExperience(resultSet.getInt(TablesColumnName.WORK_EXPERIENCE));
                    trainers.add(trainer);
                }
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return trainers;
    }
    public Integer findNumberOfRows() throws DaoException {

      int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_AMOUNT_OF_TRAINERS);
             ResultSet resultSet = statement.executeQuery()){

            if(resultSet.first()){
                number = resultSet.getInt(TablesColumnName.COUNT);
            }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return number;
    }


//    @Override
    public boolean create(Trainer trainer) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_TRAINER, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1,trainer.getTrainingType());
            statement.setString(2,trainer.getTrainerName());
            statement.setInt(3,trainer.getWorkExperience());
            statement.setInt(4,trainer.getId());
            int insertedRows = statement.executeUpdate();
            return insertedRows > 0;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public Trainer findTrainerInformation(int trainerId) throws DaoException {

        Trainer trainer = new Trainer();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_TRAINER_INFORMATION)){

            st.setInt(1, trainerId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.first()){

                    trainer.setWorkExperience(resultSet.getInt(TablesColumnName.WORK_EXPERIENCE));
                    trainer.setTrainerName(resultSet.getString(TablesColumnName.NAME));
                    trainer.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return trainer;
    }
    @Override
    public boolean update(Trainer trainer) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_TRAINER)){

            statement.setInt(4,trainer.getId());
            statement.setString(1,trainer.getTrainerName());
            statement.setString(3,trainer.getTrainingType());
            statement.setInt(2,trainer.getWorkExperience());
            int updatedRow = statement.executeUpdate();
            return updatedRow > 0;

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

    }
}
