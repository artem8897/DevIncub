package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.LogicException;

import java.util.List;
import java.util.Map;

/**
 * Basic interface for TrainerService.
 *
 * @author A. Kuzmik
 */

public interface TrainerService {

    /**
     * Create trainer information at the specified trainer entity
     * @param userId
     * @param name
     * @param experience
     * @param trainingType
     * @param map
     * @return boolean was created information
     * @throws LogicException
     */

    boolean createTrainer(int userId, String name,int experience, String trainingType, Map<String, String> map) throws LogicException;

    /**
     * Update trainer at the specified trainer entity
     * @param trainerId
     * @param name
     * @param workExperience
     * @param trainingType
     * @param map
     * @return boolean was updated
     * @throws LogicException
     */

    boolean updateTrainerInformation(int trainerId,String name, int workExperience, String trainingType, Map<String, String> map) throws LogicException;

    /**
     * Find trainer at the specified trainerID
     * @param userId
     * @return map of trainers
     * @throws LogicException
     */

    Map<String, String> findTrainerInformation(int userId) throws LogicException;

    /**
     * Find all trainers
     * @return trainers map
     * @throws LogicException
     */

    Map<Integer, Trainer> findAllTrainerMap() throws LogicException;


    /**
     * Define was created trainer information at the specified trainerId
     * @param id
     * @return boolean is exist information
     * @throws LogicException
     */

    boolean isExist(int id) throws LogicException;

    /**
     * Find limited amount of all trainers
     * @param currentPageString
     * @param recordPageString
     * @return list of trainers
     * @throws LogicException
     */

    List<Trainer> findLimitTrainerMap(String currentPageString,String recordPageString ) throws LogicException;

    /**
     * Find number of trainers
     * @return number of trainers
     * @throws LogicException
     */

    Integer findNumberOfRows() throws LogicException;

}
