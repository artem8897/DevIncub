package by.bsu.devincub.dao.impl;

import by.bsu.devincub.dao.UserDao;
import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.exception.DaoException;
import by.bsu.devincub.connectionpool.ConnectionPool;
import by.bsu.devincub.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private static final Logger logger = LogManager.getLogger(UserDaoImpl.class);
    private static final String SQL_SELECT_USER_BY_ID = "SELECT id,name,surname FROM user WHERE id = ? ;";
    private static final String SQL_SELECT_RICHEST_USER = "SELECT id,name,surname,account FROM user join account on id=user_id having account = (select max(account.account) from account)";
    private static final String SQL_SELECT_ALL_USERS = "SELECT id,name,surname FROM user ";
    private static final String ID = "id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();

        try (Connection connection = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = connection.prepareStatement(SQL_SELECT_ALL_USERS)) {
            try (ResultSet resultSet = st.executeQuery()) {
                while (resultSet.next()) {
                    User user = new User();
                    user.setId(resultSet.getInt(ID));
                    user.setUsername(resultSet.getString(NAME));
                    user.setSecondName(resultSet.getString(SURNAME));
                    users.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException(e);
        }
        return users;
    }

    @Override
    public User findEntityById(Integer id) throws DaoException {
        User user = new User();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_USER_BY_ID)) {
            st.setInt(1, id);
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(ID));
                    user.setUsername(resultSet.getString(NAME));
                    user.setSecondName(resultSet.getString(SURNAME));
                } else {
                    user = null;
                }
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
        return user;
    }

    public User findRichestUser() throws DaoException{
        User user = new User();

        try (Connection cn = ConnectionPool.INSTANCE.getConnection();
             PreparedStatement st = cn.prepareStatement(SQL_SELECT_RICHEST_USER)) {
            try (ResultSet resultSet = st.executeQuery()) {
                if (resultSet.next()) {
                    user.setId(resultSet.getInt(ID));
                    user.setUsername(resultSet.getString(NAME));
                    user.setSecondName(resultSet.getString(SURNAME));
                } else {
                    user = null;
                }
            }
        } catch (SQLException e) {
            logger.catching(e);
            throw new DaoException(e);
        }
        return user;
    }
}
