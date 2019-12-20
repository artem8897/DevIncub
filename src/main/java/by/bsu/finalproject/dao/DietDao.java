package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Diet;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for DietDao.
 *
 * @author A. Kuzmik
 */

public interface DietDao {

    /**
     * @return map all users diets
     * @throws DaoException
     */

    Map<Integer, Diet> findAllDiets() throws DaoException;

    /**
     * Create a diet at the specified userId
     * @param entity
     * @param userId
     * @return boolean if was created
     * @throws DaoException
     */

    boolean create(Diet entity, Integer userId) throws DaoException;

    /**
     * Update a diet at the specified userId
     * @param entity
     * @param userId
     * @return boolean if was updated
     * @throws DaoException
     */

    boolean update(Integer userId, Diet entity) throws DaoException;

    /**
     * Find a diet at the specified userId
     * @param userId
     * @return diet entity
     * @throws DaoException
     */

    Diet findUsersDiet(Integer userId) throws DaoException;
}
