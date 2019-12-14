package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.PersonalInformationDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Data access object for Student.
 * @author A. Kuzmik
 */


public class PersonalInformationDaoImpl implements PersonalInformationDao<Integer, PersonInformation> {

    private static final Logger logger = LogManager.getLogger(PersonalInformationDaoImpl.class);

    @Override
    public Map<Integer, PersonInformation> findAllStudents() throws DaoException {

        Map<Integer, PersonInformation> personInformationMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_ALL_PERSONAL_INFORMATION)){

            personInformationMap = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return personInformationMap;
    }

    public boolean isCreated(Integer id) throws DaoException {

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = cn.prepareStatement(Query.SQL_SELECT_USER_IN_FORMATION_BY_ID)){

            statement.setInt(1, id);

            try(ResultSet resultSet = statement.executeQuery()){
                 return resultSet.first();
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DaoException(e);
        }
    }

    public Integer findNumberOfRows() throws DaoException {

        int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_STUDENTS_COUNT);
             ResultSet resultSet = statement.executeQuery()){

            if(resultSet.next()){
                number = resultSet.getInt(TablesColumnName.COUNT);
            }

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Map<Integer, PersonInformation> findAllByTrainerInLimit(int currentPage, int recordPage, int trainerId) throws DaoException {

        Map<Integer, PersonInformation> personInformationMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_INFORMATION_BY_TRAINER)){

            statement.setInt(1,trainerId);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            personInformationMap = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return personInformationMap;
    }
    public Map<Integer, PersonInformation> findAllInLimit(int currentPage, int recordPage) throws DaoException {

        Map<Integer, PersonInformation> personInformationMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_PERSONAL_INFORMATION)){

            statement.setInt(1,currentPage);
            statement.setInt(2,recordPage);

            personInformationMap = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return personInformationMap;
    }

    public Map<Integer, PersonInformation> findAllStudentsByTrainerId(Integer id) throws DaoException {

        Map<Integer,PersonInformation> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_USER_BY_TRAINER)){

            statement.setInt(1,id);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }

    public Map<Integer, PersonInformation> findAllStudentsHavingPaidTrainingsByTrainerId(int currentPage, int recordPage, Integer id) throws DaoException {

        Map<Integer,PersonInformation> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_STUDENT_WITH_PAID_TRAININGS)){

            statement.setInt(1,id);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }
    public Integer findNumberOfRowsStudentsHavingPaid(int trainerId) throws DaoException {

        int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_STUDENTS_COUNT_WITH_PAID_TRAINERS)){

            statement.setInt(1, trainerId);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next()){
                    number = resultSet.getInt(TablesColumnName.COUNT);
                }
            }

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Map<Integer, PersonInformation> findAllStudentsHavingNoDietByTrainerId(int currentPage, int recordPage, Integer id) throws DaoException {

        Map<Integer,PersonInformation> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_STUDENTS_WITH_NO_DIET)){

            statement.setInt(1,id);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }
    public Integer findNumberOfRowsStudentsWithNoDiet(int trainerId) throws DaoException {

        int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_STUDENTS_COUNT_WITH_NO_DIET)){

            statement.setInt(1, trainerId);

            try(ResultSet resultSet = statement.executeQuery()){

                if(resultSet.next()){
                    number = resultSet.getInt(TablesColumnName.COUNT);
                }
            }

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
        return number;
    }

    public PersonInformation findPersonalInformation(Integer userId) throws DaoException {

        PersonInformation personInformation = new PersonInformation();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_ALL_USER_PERSONAL_INFORMATION)){

            st.setInt(1, userId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.next()){
                    personInformation.setSecondName(resultSet.getString(TablesColumnName.SECOND_NAME));
                    personInformation.setName(resultSet.getString(TablesColumnName.NAME));
                    personInformation.setSex(resultSet.getString(TablesColumnName.SEX));
                    personInformation.setWeight(resultSet.getInt(TablesColumnName.WEIGHT));
                    personInformation.setHeight(resultSet.getInt(TablesColumnName.HEIGHT));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DaoException(e);
        }
        return personInformation;
    }

    @Override
    public boolean create(PersonInformation entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_USER_INFORMATION)){

            return insertPersonInformationInDatabase(entity,statement);

        } catch (SQLException | ConnectionPoolException e) {
            logger.debug(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean update(PersonInformation entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_PERSONAL_INFORMATION)) {

            return insertPersonInformationInDatabase(entity,statement);

        }catch (ConnectionPoolException | SQLException e){

            logger.error(e);
            throw new DaoException(e);
        }
    }

    public boolean updatePayStatus(Integer userId, Integer payStatus) throws DaoException {

        //todo
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_STATUS_PAY)) {

            statement.setInt(1, payStatus);
            statement.setInt(2, userId);
            int insertedRow = statement.executeUpdate();
            return (insertedRow > 0);

        }catch (ConnectionPoolException | SQLException e){
            logger.debug(e);
            throw new DaoException(e);
        }
    }

    private Map<Integer, PersonInformation> createPersonMap(PreparedStatement statement) throws SQLException {

        Map<Integer, PersonInformation> personInformationMap = new HashMap<>();

        try(ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {

                PersonInformation personInformation = new PersonInformation();
                personInformation.setId(resultSet.getInt(TablesColumnName.INFORMATION_ID));
                personInformation.setSecondName(resultSet.getString(TablesColumnName.SECOND_NAME));
                personInformation.setName(resultSet.getString(TablesColumnName.NAME));
                personInformation.setSex(resultSet.getString(TablesColumnName.SEX));
                personInformation.setWeight(resultSet.getInt(TablesColumnName.WEIGHT));
                personInformation.setHeight(resultSet.getInt(TablesColumnName.HEIGHT));
                personInformationMap.put(personInformation.getId(),personInformation);
            }
        }
        return personInformationMap;
    }

    private boolean insertPersonInformationInDatabase(PersonInformation entity, PreparedStatement statement) throws SQLException {

        int insertedRow ;

        statement.setInt(6, entity.getId());
        statement.setString(2,entity.getSecondName());
        statement.setString(1,entity.getName());
        statement.setString(3,entity.getSex());
        statement.setInt(4,entity.getWeight());
        statement.setInt(5,entity.getHeight());

        insertedRow = statement.executeUpdate();

        return (insertedRow > 0);

    }
}
