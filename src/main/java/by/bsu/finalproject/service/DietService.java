package by.bsu.finalproject.service;

import by.bsu.finalproject.exception.LogicException;

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
     * @throws LogicException
     */

    boolean addInformation(int userId, String dietType,String carbohydrates,String fats,String proteins, Map<String, String> map) throws LogicException;

    /**
     * Find if valid a diet at the specified userId
     * @param userId
     * @return diet entity
     * @throws LogicException
     */

    Map<String, String>  findUsersDiet(int userId) throws LogicException;

    /**
     * Update a diet at the specified userId
     * @param dietType
     * @param userId
     * @param carbohydrates
     * @param fats
     * @param proteins
     * @param map
     * @return boolean if valid and was updated
     * @throws LogicException
     */

    boolean updateDiet(int userId, String dietType,String carbohydrates,String fats,String proteins,  Map<String, String> map)  throws LogicException;
}
