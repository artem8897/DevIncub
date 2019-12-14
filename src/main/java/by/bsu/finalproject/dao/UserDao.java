package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for UserDao.
 *
 * @author A. Kuzmik
 */

public interface UserDao<K,S, T extends Entity>  {

    /**
     * Find all users
     * @return users map
     */

    Map<K, T> findAll(K currentPage, K recordPerPage) throws DaoException;

    /**
     * Create user at the specified user entity
     * @param user
     * @return boolean was created user
     */

    boolean create(T user) throws DaoException;

    /**
     * Define was created user at the specified email and login
     * @param email
     * @param login
     * @return boolean is exist user
     */

    boolean isExistMailOrUsername(S email, S login) throws DaoException;

    /**
     * Delete a user at the specified userId
     * @param userId
     * @return  boolean was deleted
     */

    boolean deleteUser(K userId) throws DaoException;

    /**
     * Update status and userType at the specified userId
     * @param userId
     * @param status
     * @param userType
     * @return boolean was updated user
     */

    boolean changeUserStatus(K userId , S status, UserType userType) throws DaoException;

    /**
     * Update username at the specified userId
     * @param userId
     * @param username
     * @return boolean was updated training
     */

    boolean updateUsername(K userId, S username) throws DaoException;

    /**
     * Update password at the specified userId
     * @param userId
     * @param password
     * @return boolean was updated training
     */

    boolean updatePassword(K userId, S password) throws DaoException;

    /**
     * Find user at the specified email
     * @param email
     * @return map of training
     */

    T findEntityByEmail(S email) throws DaoException;

    /**
     * Find number of users
     * @return number of users
     */

    K findNumberOfRows() throws DaoException;
}
