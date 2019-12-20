package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.StudentDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.entity.Student;
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


public class StudentDaoImpl implements StudentDao {

    private static final Logger logger = LogManager.getLogger(StudentDaoImpl.class);

    @Override
    public Map<Integer, Student> findAllStudents() throws DaoException {

        Map<Integer, Student> personInformationMap;

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
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    public Integer findNumberStudents() throws DaoException {

        int number = 0;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_STUDENTS_COUNT);
             ResultSet resultSet = statement.executeQuery()){

            if(resultSet.next()){
                number = resultSet.getInt(TablesColumnName.COUNT);
            }

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Map<Integer, Student> findStudentsByTrainer(int currentPage, int recordPage, int trainerId) throws DaoException {

        Map<Integer, Student> personInformationMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_INFORMATION_BY_TRAINER)){

            statement.setInt(1,trainerId);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            personInformationMap = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return personInformationMap;
    }
    public Map<Integer, Student> findAllStudents(int currentPage, int recordPage) throws DaoException {

        Map<Integer, Student> personInformationMap;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_LIMIT_PERSONAL_INFORMATION)){

            statement.setInt(1,currentPage);
            statement.setInt(2,recordPage);

            personInformationMap = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return personInformationMap;
    }

    public Map<Integer, Student> findAllStudentsByTrainerId(Integer id) throws DaoException {

        Map<Integer, Student> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_USER_BY_TRAINER)){

            statement.setInt(1,id);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }

    public Map<Integer, Student> findStudentsWithPaidTrainings(int currentPage, int recordPage, Integer id) throws DaoException {

        Map<Integer, Student> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_STUDENT_WITH_PAID_TRAININGS)){

            statement.setInt(1,id);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }
    public Integer findNumberOfStudentsWhoPaid(int trainerId) throws DaoException {

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
            logger.catching(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Map<Integer, Student> findStudentsWithNoDiet(int currentPage, int recordPage, Integer id) throws DaoException {

        Map<Integer, Student> mapPerson ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_SELECT_STUDENTS_WITH_NO_DIET)){

            statement.setInt(1,id);
            statement.setInt(2,currentPage);
            statement.setInt(3,recordPage);

            mapPerson = createPersonMap(statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
        return mapPerson;
    }
    public Integer findNumberStudentsWithNoDiet(int trainerId) throws DaoException {

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
            logger.catching(e);
            throw new DaoException(e);
        }
        return number;
    }

    public Student findStudentInformation(Integer userId) throws DaoException {

        Student student = new Student();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_ALL_USER_PERSONAL_INFORMATION)){

            st.setInt(1, userId);

            try(ResultSet resultSet = st.executeQuery()){

                if(resultSet.next()){
                    student.setSecondName(resultSet.getString(TablesColumnName.SECOND_NAME));
                    student.setName(resultSet.getString(TablesColumnName.NAME));
                    student.setSex(resultSet.getString(TablesColumnName.SEX));
                    student.setWeight(resultSet.getInt(TablesColumnName.WEIGHT));
                    student.setHeight(resultSet.getInt(TablesColumnName.HEIGHT));
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
        return student;
    }

    @Override
    public boolean createStudent(Student entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_INSERT_USER_INFORMATION)){

            return insertPersonInformationInDatabase(entity,statement);

        } catch (SQLException | ConnectionPoolException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    @Override
    public boolean updateStudent(Student entity) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_PERSONAL_INFORMATION)) {

            return insertPersonInformationInDatabase(entity,statement);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    public boolean updatePayStatus(Integer userId, Integer payStatus) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_UPDATE_STATUS_PAY)) {

            statement.setInt(1, payStatus);
            statement.setInt(2, userId);
            int insertedRow = statement.executeUpdate();
            return (insertedRow > 0);

        }catch (ConnectionPoolException | SQLException e){
            logger.catching(e);
            throw new DaoException(e);
        }
    }

    private Map<Integer, Student> createPersonMap(PreparedStatement statement) throws SQLException {

        Map<Integer, Student> personInformationMap = new HashMap<>();

        try(ResultSet resultSet = statement.executeQuery()){

            while (resultSet.next()) {

                Student student = new Student();
                student.setId(resultSet.getInt(TablesColumnName.INFORMATION_ID));
                student.setSecondName(resultSet.getString(TablesColumnName.SECOND_NAME));
                student.setName(resultSet.getString(TablesColumnName.NAME));
                student.setSex(resultSet.getString(TablesColumnName.SEX));
                student.setWeight(resultSet.getInt(TablesColumnName.WEIGHT));
                student.setHeight(resultSet.getInt(TablesColumnName.HEIGHT));
                personInformationMap.put(student.getId(), student);
            }
        }
        return personInformationMap;
    }

    private boolean insertPersonInformationInDatabase(Student entity, PreparedStatement statement) throws SQLException {

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
