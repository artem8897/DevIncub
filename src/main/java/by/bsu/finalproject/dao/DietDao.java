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
     */

    Map<K, T> findAll() throws DaoException;

    /**
     * Delete a diet at the specified dietId
     * @param dietId
     * @return
     */

    boolean delete(K dietId);

    /**
     * Create a diet at the specified userId
     * @param entity
     * @param userId
     * @return
     */

    boolean create(T entity, K userId) throws DaoException;

    /**
     * Update a diet at the specified userId
     * @param entity
     * @param userId
     * @return
     */

    boolean update(K userId, T entity) throws DaoException;

    /**
     * Update a diet at the specified userId
     * @param userId
     * @return diet entity
     */

    T findUsersDiet(K userId) throws DaoException;
}
