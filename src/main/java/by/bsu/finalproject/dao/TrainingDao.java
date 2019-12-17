package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for TrainingDao.
 *
 * @author A. Kuzmik
 */

public interface TrainingDao<K, T extends Entity> {

    /**
     * Find all trainings
     * @return training map
     * @throws DaoException
     */

    Map<K, T> findAll() throws DaoException;

    /**
     * Delete a training at the specified trainingId
     * @param trainingId
     * @return
     * @throws DaoException
     */

    boolean deleteTraining(K trainingId) throws DaoException;

    /**
     * Create training information at the specified training entity
     * @param training
     * @param userId
     * @return boolean was created training
     * @throws DaoException
     */

    boolean create(K userId, T training) throws DaoException;

    /**
     * Update training at the specified training entity
     * @param training
     * @return boolean was updated training
     * @throws DaoException
     */

    boolean update(T training) throws DaoException;

    /**
     * Find all trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws DaoException
     */

    Map<K, T> findUsersTrainingMap(int userId) throws DaoException;

    /**
     * Find limited amount of trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws DaoException
     */

    Map<K, T> findAllInLimit(int currentPage, int recordPage,int userId) throws DaoException;

    /**
     * Find number of trainings
     * @return number of trainings
     * @throws DaoException
     */

    Integer findNumberOfRows(int userId) throws DaoException;

    /**
     * Find trainer at the specified trainingId
     * @param trainingId
     * @return map of training
     * @throws DaoException
     */

    T findTrainingById(int trainingId) throws DaoException;

    }
