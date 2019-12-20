package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for UserDao.
 *
 * @author A. Kuzmik
 */

public interface UserDao {

    /**
     * Find all users
     * @return users map
     * @throws DaoException
     */

    Map<Integer, User> findAllUsers(int currentPage, int recordPerPage) throws DaoException;

    /**
     * Create user at the specified user entity
     * @param user
     * @return boolean was created user
     * @throws DaoException
     */

    boolean createUser(User user) throws DaoException;

    /**
     * Define was created user at the specified email and login
     * @param email
     * @param login
     * @return boolean is exist user
     * @throws DaoException
     */

    boolean isExistMailOrUsername(String email, String login) throws DaoException;

    /**
     * Delete a user at the specified userId
     * @param userId
     * @return  boolean was deleted
     * @throws DaoException
     */

    boolean deleteUser(int userId) throws DaoException;

    /**
     * Update status and userType at the specified userId
     * @param userId
     * @param status
     * @param userType
     * @return boolean was updated user
     * @throws DaoException
     */

    boolean changeUserStatus(int userId , String status, UserType userType) throws DaoException;

    /**
     * Update username at the specified userId
     * @param userId
     * @param username
     * @return boolean was updated training
     * @throws DaoException
     */

    boolean updateUsername(int userId, String username) throws DaoException;

    /**
     * Update password at the specified userId
     * @param userId
     * @param password
     * @return boolean was updated training
     * @throws DaoException
     */

    boolean updatePassword(int userId, String password) throws DaoException;

    /**
     * Find user at the specified email
     * @param email
     * @return map of training
     * @throws DaoException
     */

    User findEntityByEmail(String email) throws DaoException;

    /**
     * Find number of users
     * @return number of users
     * @throws DaoException
     */

    int findNumberOfRows() throws DaoException;
}
