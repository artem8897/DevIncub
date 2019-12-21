package by.bsu.finalproject.service;

import by.bsu.finalproject.exception.ServiceException;

import java.util.Map;

/**
 * Basic interface for Service.
 *
 * @author A. Kuzmik
 */

public interface DietService {

    /**
     * Create a diet at the specified userId
     * @param dietType
     * @param userId
     * @param carbohydrates
     * @param fats
     * @param proteins
     * @param map
     * @return boolean if valid and was updated
     * @throws ServiceException
     */

    boolean addInformation(int userId, String dietType,String carbohydrates,String fats,String proteins, Map<String, String> map) throws ServiceException;

    /**
     * Find if valid a diet at the specified userId
     * @param userId
     * @return diet entity
     * @throws ServiceException
     */

    Map<String, String>  findUsersDiet(int userId) throws ServiceException;

    /**
     * Update a diet at the specified userId
     * @param dietType
     * @param userId
     * @param carbohydrates
     * @param fats
     * @param proteins
     * @param map
     * @return boolean if valid and was updated
     * @throws ServiceException
     */

    boolean updateDiet(int userId, String dietType,String carbohydrates,String fats,String proteins,  Map<String, String> map)  throws ServiceException;
}
