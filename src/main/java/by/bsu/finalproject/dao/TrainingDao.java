package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for TrainingDao.
 *
 * @author A. Kuzmik
 */

public interface TrainingDao {

    /**
     * Find all trainings
     * @return training map
     * @throws DaoException
     */

    Map<Integer, Training> findAll() throws DaoException;

    /**
     * Delete a training at the specified trainingId
     * @param trainingId
     * @return true if was deleted
     * @throws DaoException
     */

    boolean deleteTraining(int trainingId) throws DaoException;

    /**
     * Create training information at the specified training entity
     * @param training id user training
     * @param userId
     * @return boolean was created training
     * @throws DaoException
     */

    boolean createTraining(int userId, Training training) throws DaoException;

    /**
     * Update training at the specified training entity
     * @param training
     * @return boolean was updated training
     * @throws DaoException
     */

    boolean update(Training training) throws DaoException;

    /**
     * Find all trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws DaoException
     */

    Map<Integer, Training> findUsersTrainingMap(int userId) throws DaoException;

    /**
     * Find limited amount of trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws DaoException
     */

    Map<Integer, Training> findUsersTrainings(int currentPage, int recordPage, int userId) throws DaoException;

    /**
     * Find number of trainings
     * @return number of trainings
     * @throws DaoException
     */

    int findNumberOfTrainings(int userId) throws DaoException;

    /**
     * Find trainer at the specified trainingId
     * @param trainingId
     * @return map of training
     * @throws DaoException
     */

    Training findTrainingById(int trainingId) throws DaoException;

    }
