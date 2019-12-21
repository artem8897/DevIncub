package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.exception.ServiceException;

import java.util.Map;

/**
 * Basic interface for TrainingService.
 *
 * @author A. Kuzmik
 */

public interface TrainingService {

    /**
     * Create training information at the specified training entity
     * @param trainingType
     * @param userId
     * @param periodicity
     * @param personality
     * @param map
     * @return boolean was created training
     * @throws ServiceException
     */

    boolean createPersonalTrainingForUser(int userId, String periodicity, String trainingType, String personality, Map<String, String> map) throws ServiceException;

    /**
     * Update training at the specified training entity
     * @param trainingType
     * @param trainingId
     * @param periodicity
     * @param personality
     * @param map
     * @return boolean was updated training
     * @throws ServiceException
     */

    boolean updateTraining(int trainingId,String personality, String periodicity, String trainingType,  Map<String, String> map) throws ServiceException;

    /**
     * Delete a training at the specified trainingId
     * @param trainingId
     * @return
     * @throws ServiceException
     */

    boolean deleteTraining(int trainingId) throws ServiceException;

    /**
     * Find all trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws ServiceException
     */

    Map<Integer, Training> findUsersTraining(int userId) throws ServiceException;

    /**
     * Find limited amount of trainings at the specified userId
     * @param userId
     * @param currentPageString
     * @param recordPageString
     * @return map of trainings
     * @throws ServiceException
     */

    Map<Integer, Training> findUsersTrainings(String currentPageString, String recordPageString, int userId ) throws ServiceException;

    /**
     * Find number of trainings
     * @return number of trainings
     * @throws ServiceException
     */

    Integer findNumberOfRows(int userId) throws ServiceException;

    /**
     * Find trainer at the specified trainingId
     * @param trainingId
     * @return map of training
     * @throws ServiceException
     */

    Map<String, String> findTrainingById(int trainingId) throws ServiceException;
}
