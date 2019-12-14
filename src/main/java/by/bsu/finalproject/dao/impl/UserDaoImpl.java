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

/**
 * Data access object for User.
 * @author A. Kuzmik
 */

public class UserDaoImpl implements UserDao<Integer, String, User> {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);

    @Override
    public Map<Integer, User> findAll(Integer currentPage, Integer recordPerPage) throws DaoException {

        Map<Integer, User> users = new HashMap<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement st = connection.prepareStatement(Query.SQL_SELECT_ALL_USERS)){

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

    public boolean create(User user) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_INSERT_USER)) {

            statement.setString(1, user.getUsername());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getUserType().toString());
            int updatedRow = statement.executeUpdate();
            return updatedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }

    public boolean isExistMailOrUsername(String email, String login) throws DaoException {

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USER_BY_EMAIL_AND_USERNAME)){

            st.setString(1, email);
            st.setString(2,login);

            try(ResultSet resultSet = st.executeQuery()){
                return resultSet.first();
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
    }

    public boolean deleteUser(Integer userId) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_DELETE_USER)) {

                statement.setInt(1, userId);
                int insertedRow = statement.executeUpdate();
                return insertedRow > 0;


        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }
    public boolean changeUserStatus(Integer userId , String status, UserType userType) throws DaoException {

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_CHANGE_STATUS_USER)) {

                statement.setInt(3,userId);
                statement.setString(1, status);
                statement.setString(2, userType.toString());
                int updatedRow = statement.executeUpdate();
                return updatedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }

    public boolean updateUsername(Integer userId, String username) throws DaoException{

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_USERNAME)) {

                statement.setInt(2,userId);
                statement.setString(1, username);
                int insertedRow = statement.executeUpdate();
                return insertedRow > 0;

        }catch (ConnectionPoolException | SQLException e){
            throw new DaoException(e);
        }
    }

    public boolean updatePassword(Integer userId, String password) throws DaoException{

            try (Connection connection = ConnectionPool.INSTANCE.getConnection();
                 PreparedStatement  statement = connection.prepareStatement(Query.SQL_UPDATE_USER_PASSWORD)) {
                statement.setInt(2,userId);
                statement.setString(1, password);
                int insertedRow = statement.executeUpdate();
                return insertedRow > 0;

            }catch (ConnectionPoolException | SQLException e){
                throw new DaoException(e);
            }
    }

    public User findEntityByEmail(String email) throws DaoException {

        User user = new User();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(Query.SQL_SELECT_USER_BY_EMAIL)){

            st.setString(1, email);

            try(ResultSet resultSet = st.executeQuery()){
                if(resultSet.next()){
                    user.setId(resultSet.getInt(TablesColumnName.COLUMN_LABEL_ID));
                    user.setPassword(resultSet.getString(TablesColumnName.COLUMN_LABEL_PASSWORD));
                    user.setUsername(resultSet.getString(TablesColumnName.COLUMN_LABEL_USERNAME));
                    user.setUserType(UserType.valueOf(resultSet.getString(TablesColumnName.COLUMN_LABEL_USER_TYPE).toUpperCase()));
                    user.setEmail(email);
                }else {
                    user = null;
                }
            }
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException(e);
        }
        return user;
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
