package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for DietDao.
 *
 * @author A. Kuzmik
 */

public interface DietDao<K, T extends Entity> {

    /**
     * @return map all users diets
     * @throws DaoException
     */

    Map<K, T> findAllDiets() throws DaoException;

    /**
     * Create a diet at the specified userId
     * @param entity
     * @param userId
     * @return boolean if was created
     * @throws DaoException
     */

    boolean create(T entity, K userId) throws DaoException;

    /**
     * Update a diet at the specified userId
     * @param entity
     * @param userId
     * @return boolean if was updated
     * @throws DaoException
     */

    boolean update(K userId, T entity) throws DaoException;

    /**
     * Find a diet at the specified userId
     * @param userId
     * @return diet entity
     * @throws DaoException
     */

    T findUsersDiet(K userId) throws DaoException;
}
