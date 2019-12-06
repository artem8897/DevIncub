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

public class TrainerDaoImpl implements TrainerDao<Integer, Trainer> {

    private static final Logger logger = LogManager.getLogger(TrainingDaoImpl.class);

    @Override
    public Map<Integer,Trainer> findAll() throws DaoException {

        Map<Integer, Trainer> trainers = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_TRAINERS);
             ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {
                Trainer trainer = new Trainer();
                trainer.setId(resultSet.getInt(TablesColumnName.TRAINER_ID));
                trainer.setName(resultSet.getString(TablesColumnName.NAME));
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

        boolean isInBase ;

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = cn.prepareStatement(Query.SQL_SELECT_TRAINER_BY_ID)){

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                isInBase = resultSet.next();
            }
            // todo result set sam????
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DaoException(e);
        }
        return isInBase;
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
                    trainer.setName(resultSet.getString(ParamName.PARAM_NAME_NAME));
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

            if(resultSet.next()){
                number = resultSet.getInt(TablesColumnName.COUNT);
            }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return number;
    }


    @Override
    public boolean delete(Trainer entity) {
        return false;
    }

//    @Override
    public boolean create(int trainerId, Trainer entity) throws DaoException {

        int insertedRows;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_TRAINER, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1,entity.getTrainingType());
            statement.setString(2,entity.getName());
            statement.setInt(3,entity.getWorkExperience());
            statement.setInt(4,trainerId);

            insertedRows = statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        if(insertedRows > 0){
            logger.info("Created trainer information");
            return true;
        }else{
            logger.info("user wasn't created");
            return false;
        }
    }

    public Trainer findTrainerInformation(int trainerId) throws DaoException {

        Trainer trainer = new Trainer();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_TRAINER_INFORMATION)){

            st.setInt(1, trainerId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.next()){

                    trainer.setWorkExperience(resultSet.getInt(TablesColumnName.WORK_EXPERIENCE));
                    trainer.setName(resultSet.getString(TablesColumnName.NAME));
                    trainer.setTrainingType(resultSet.getString(TablesColumnName.TRAINING_TYPE));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return trainer;
    }
    @Override
    public boolean update(Trainer entity) throws DaoException {

        int updatedRow;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_TRAINER)){

            statement.setInt(4,entity.getId());
            statement.setString(1,entity.getName());
            statement.setString(3,entity.getTrainingType());
            statement.setInt(2,entity.getWorkExperience());
            updatedRow = statement.executeUpdate();

        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }

        if(updatedRow > 0){
            logger.info("Created trainer information");
            return true;
        }else{
            logger.info("didn't crated");
            return false;
        }
    }
}
