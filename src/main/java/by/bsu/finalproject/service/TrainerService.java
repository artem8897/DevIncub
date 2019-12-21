package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.ServiceException;

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
     * @throws ServiceException
     */

    boolean createTrainer(int userId, String name,String experience, String trainingType, Map<String, String> map) throws ServiceException;

    /**
     * Update trainer at the specified trainer entity
     * @param trainerId
     * @param name
     * @param workExperience
     * @param trainingType
     * @param map
     * @return boolean was updated
     * @throws ServiceException
     */

    boolean updateTrainerInformation(int trainerId,String name, String workExperience, String trainingType, Map<String, String> map) throws ServiceException;

    /**
     * Find trainer at the specified trainerID
     * @param userId
     * @return map of trainers
     * @throws ServiceException
     */

    Map<String, String> findTrainerInformation(int userId) throws ServiceException;

    /**
     * Find all trainers
     * @return trainers map
     * @throws ServiceException
     */

    Map<Integer, Trainer> findAllTrainerMap() throws ServiceException;


    /**
     * Define was created trainer information at the specified trainerId
     * @param id
     * @return boolean is exist information
     * @throws ServiceException
     */

    boolean isExist(int id) throws ServiceException;

    /**
     * Find limited amount of all trainers
     * @param currentPageString
     * @param recordPageString
     * @return list of trainers
     * @throws ServiceException
     */

    List<Trainer> findLimitTrainerMap(String currentPageString,String recordPageString ) throws ServiceException;

    /**
     * Find number of trainers
     * @return number of trainers
     * @throws ServiceException
     */

    Integer findNumberOfRows() throws ServiceException;

}
