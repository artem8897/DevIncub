package by.bsu.finalproject.dao.impl;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.TablesColumnName;
import by.bsu.finalproject.dao.UserDao;
import by.bsu.finalproject.dao.Query;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class UserDaoImpl implements UserDao<Integer, User> {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public Map<Integer, User> findAll(Integer currentPage, Integer recordPerPage) throws DaoException {
        Map<Integer, User> users = new HashMap<>();
            try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(Query.SQL_SELECT_ALL_USERS);
                 ){
                st.setInt(1,currentPage);
                st.setInt(2,recordPerPage);
                try(ResultSet resultSet = st.executeQuery()){
                    while (resultSet.next()) {
                        User user = new User();
                        user.setId(resultSet.getInt(TablesColumnName.COLUMN_LABEL_ID));
                        user.setEmail(resultSet.getString(TablesColumnName.COLUMN_LABEL_EMAIL));
                        user.setPassword(resultSet.getString(TablesColumnName.COLUMN_LABEL_PASSWORD));
                        user.setUsername(resultSet.getString(TablesColumnName.COLUMN_LABEL_USERNAME));
                        user.setStatus(resultSet.getString(TablesColumnName.STATUS));
                        user.setUserType(UserType.valueOf(resultSet.getString(TablesColumnName.COLUMN_LABEL_USER_TYPE).toUpperCase()));
                        users.put(user.getId(),user);
                }
                }
            }catch (ConnectionPoolException | SQLException e){
                throw new DaoException(e);
            }
            return users;
    }

    public Map<Integer, User> findAllTrainers() throws DaoException {
        Map<Integer, User> users = new HashMap<>();
        try ( Connection connection = ConnectionPool.INSTANCE.getConnection();
              PreparedStatement st = connection.prepareStatement(Query.SQL_SELECT_ALL_USERS);
              ResultSet resultSet = st.executeQuery()){
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(TablesColumnName.COLUMN_LABEL_ID));
                user.setEmail(resultSet.getString(TablesColumnName.COLUMN_LABEL_EMAIL));
                user.setPassword(resultSet.getString(TablesColumnName.COLUMN_LABEL_PASSWORD));
                user.setUsername(resultSet.getString(TablesColumnName.COLUMN_LABEL_USERNAME));
                user.setUserType(UserType.valueOf(resultSet.getString(TablesColumnName.COLUMN_LABEL_USER_TYPE).toUpperCase()));
                users.put(user.getId(),user);
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return users;
    }

//    @Override
//    public boolean delete(Integer id) {
//        throw new UnsupportedOperationException();
//    }
//    @Override
    public boolean create(User user) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_USER)) {
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserType().toString());
            statement.executeUpdate();
            logger.info("Created user");
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return true;
    }

    public boolean isExistMailOrUsername(String email, String login) throws DaoException {

        boolean isInBase = false;

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USER_BY_EMAIL_AND_USERNAME)){
            st.setString(1, email);
            st.setString(2,login);
            try(ResultSet resultSet = st.executeQuery()){
                isInBase = resultSet.next();
            }catch (SQLException e){
                logger.error("wrong acces",e);
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return isInBase;
    }

    public boolean deleteUser(int userId) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_DELETE_USER)) {
            try{
                statement.setInt(1, userId);
                statement.executeUpdate();
                logger.info("Deleted user");
                return true;
            } catch (SQLException e) {
                logger.error("wrong field", e);
            }
            // todo
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return false;
    }
    public boolean changeUserStatus(int userId , String status, UserType userType) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_CHANGE_STATUS_USER)) {

                statement.setInt(3,userId);
                statement.setString(1, status);
                statement.setString(2, userType.toString());
                int update = statement.executeUpdate();
                if(update > 0){
                    logger.info("changed user status to" +" "+ status);
                    return true;
                }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return false;
    }

    @Override
    public boolean updateUserType(UserType userType) throws DaoException {
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_USER_TYPE)) {
            try{
                statement.setString(1,userType.toString());
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

    public boolean createUsersReview(int trainerId,int userId,int rate,String message) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_USERS_REVIEW)) {
            try{
                statement.setInt(1, trainerId);
                statement.setInt(2, userId);
                statement.setInt(3, rate);
                statement.setString(4, message);
                statement.executeUpdate();
                logger.info("Created user");
                return true;
            } catch (SQLException e) {
                logger.error("wrong field");
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return false;
    }

    public boolean updateUsername(int userId, String username) throws DaoException{
        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_USERNAME)) {
            try{
                statement.setInt(2,userId);
                statement.setString(1, username);
                statement.executeUpdate();
                logger.info("updated username user");
                return true;
            } catch (SQLException e) {
                logger.error("wrong field");
            }
        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return false;
    }
    public boolean updatePassword(int userId, String password) throws DaoException{
        {
            try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_USER_PASSWORD)) {
                try{statement.setInt(2,userId);
                    statement.setString(1, password);
                    statement.executeUpdate();
                    logger.info("updated password user");
                    return true;
                } catch (SQLException e) {
                    logger.error("wrong field");
                }
            }catch (ConnectionPoolException | SQLException e){
                throw new DaoException(e);
            }
            return false;
        }
    }

    public User findEntityByEmail(String email) throws DaoException {
        if(email==null){
            return null;
        }
        User user = new User();
        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
                PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USER_BY_EMAIL)){
            st.setString(1, email);
            try(ResultSet resultSet = st.executeQuery()){
                if(resultSet.next()){
                    user.setId(resultSet.getInt(TablesColumnName.COLUMN_LABEL_ID));
                    user.setPassword(resultSet.getString(TablesColumnName.COLUMN_LABEL_PASSWORD));
                    user.setUserType(UserType.valueOf(resultSet.getString(TablesColumnName.COLUMN_LABEL_USER_TYPE).toUpperCase()));
                    user.setEmail(email);
                }else {
                    user = null;
                }
            }catch (SQLException e){
                logger.error("wrong acces",e);
                user = null;
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return user;
    }
    @Override
    public boolean delete(User entity) {
        throw new UnsupportedOperationException();
    }

    public Integer findNumberOfRows() throws DaoException {

        int number = 0 ;

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement statement = connection.prepareStatement(Query.SQL_CALCULATE_AMOUNT_OF_USERS);
             ResultSet resultSet = statement.executeQuery()){

                if(resultSet.next()){
                    number = resultSet.getInt(TablesColumnName.COUNT);
                }

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
        return number;
    }

}
