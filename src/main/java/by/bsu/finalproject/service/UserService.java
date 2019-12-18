package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.User;
import by.bsu.finalproject.exception.LogicException;

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
     * @throws LogicException
     */

    User findUserInBase(String enterLogin, String enterPass) throws LogicException;

    /**
     * Create user at the specified user entity
     * @param login
     * @param pass
     * @param confirmedPassword
     * @param username
     * @param sex
     * @param map
     * @return boolean was created user
     * @throws LogicException
     */

    boolean register(String login,String pass,String confirmedPassword,String username,String sex, Map map) throws LogicException;

    /**
     * Delete a user at the specified userId
     * @param userId
     * @return  boolean was deleted
     * @throws LogicException
     */

    boolean deleteUser(int userId) throws LogicException;

    /**
     * Update status and userType at the specified userId
     * @param userId
     * @param status
     * @param userType
     * @return boolean was updated user
     * @throws LogicException
     */

    boolean changeUserStatus(int userId,int adminId, String status, String userType) throws LogicException;

    /**
     * Update password at the specified userId
     * @param userId
     * @param password
     * @return boolean was updated training
     * @throws LogicException
     */

    boolean changePassword(int userId, String password) throws LogicException;

    /**
     * Update username at the specified userId
     * @param userId
     * @param username
     * @return boolean was updated training
     * @throws LogicException
     */

    boolean changeUsername(int userId, String username) throws LogicException;

    /**
     * Find all users
     * @param currentPageString
     * @param recordPageString
     * @return users map
     * @throws LogicException
     */

     Map<Integer, User> findAllUserMap(String currentPageString, String recordPageString) throws LogicException;

    /**
     * Find number of users
     * @return number of users
     * @throws LogicException
     */

    Integer findNumberOfRows() throws LogicException;


}
