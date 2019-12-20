package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.exception.LogicException;

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
     * @throws LogicException
     */

    boolean createPersonalTrainingForUser(int userId, String periodicity, String trainingType, String personality, Map<String, String> map) throws LogicException;

    /**
     * Update training at the specified training entity
     * @param trainingType
     * @param trainingId
     * @param periodicity
     * @param personality
     * @param map
     * @return boolean was updated training
     * @throws LogicException
     */

    boolean updateTraining(int trainingId,String personality, String periodicity, String trainingType,  Map<String, String> map) throws LogicException;

    /**
     * Delete a training at the specified trainingId
     * @param trainingId
     * @return
     * @throws LogicException
     */

    boolean deleteTraining(int trainingId) throws LogicException;

    /**
     * Find all trainings at the specified userId
     * @param userId
     * @return map of trainings
     * @throws LogicException
     */

    Map<Integer, Training> findUsersTraining(int userId) throws LogicException;

    /**
     * Find limited amount of trainings at the specified userId
     * @param userId
     * @param currentPageString
     * @param recordPageString
     * @return map of trainings
     * @throws LogicException
     */

    Map<Integer, Training> findUsersTrainings(String currentPageString, String recordPageString, int userId ) throws LogicException;

    /**
     * Find number of trainings
     * @return number of trainings
     * @throws LogicException
     */

    Integer findNumberOfRows(int userId) throws LogicException;

    /**
     * Find trainer at the specified trainingId
     * @param trainingId
     * @return map of training
     * @throws LogicException
     */

    Map<String, String> findTrainingById(int trainingId) throws LogicException;
}
