package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.ServiceException;

import java.util.Map;

/**
 * Basic interface for UserService.
 *
 * @author A. Kuzmik
 */

public interface UserService {

    /**
     * Find user at the specified email
     * @param enterLogin
     * @param enterPass
     * @return user
     * @throws ServiceException
     */

    User findUserInBase(String enterLogin, String enterPass) throws ServiceException;

    /**
     * Create user at the specified user entity
     * @param login
     * @param pass
     * @param confirmedPassword
     * @param username
     * @param sex
     * @param map
     * @return boolean was created user
     * @throws ServiceException
     */

    boolean register(String login,String pass,String confirmedPassword,String username,String sex, Map<String, String> map) throws ServiceException;

    /**
     * Delete a user at the specified userId
     * @param userId
     * @return  boolean was deleted
     * @throws ServiceException
     */

    boolean deleteUser(int userId) throws ServiceException;

    /**
     * Update status and userType at the specified userId
     * @param userId
     * @param status
     * @param userType
     * @return boolean was updated user
     * @throws ServiceException
     */

    boolean changeUserStatus(int userId,int adminId, String status, String userType) throws ServiceException;

    /**
     * Update password at the specified userId
     * @param userId
     * @param confirmedPassword
     * @param password
     * @return boolean was updated training
     * @throws ServiceException
     */

    boolean changePassword(int userId, String confirmedPassword, String password) throws ServiceException;

    /**
     * Update username at the specified userId
     * @param userId
     * @param username
     * @return boolean was updated training
     * @throws ServiceException
     */

    boolean changeUsername(int userId, String username) throws ServiceException;

    /**
     * Find all users
     * @param currentPageString
     * @param recordPageString
     * @return users map
     * @throws ServiceException
     */

     Map<Integer, User> findAllUserMap(String currentPageString, String recordPageString) throws ServiceException;

    /**
     * Find number of users
     * @return number of users
     * @throws ServiceException
     */

    Integer findNumberOfUsers() throws ServiceException;


}
