package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * Basic interface for TrainerDao.
 *
 * @author A. Kuzmik
 */

public interface TrainerDao {

    /**
     * Find all trainers
     * @return trainers map
     * @throws DaoException
     */

    Map<Integer, Trainer> findAllTrainers() throws DaoException;

    /**
     * Update trainer at the specified trainer entity
     * @param trainer
     * @return boolean was updated
     * @throws DaoException
     */

    boolean updateTrainer(Trainer trainer) throws DaoException;

    /**
     * Define was created trainer information at the specified trainerId
     * @param id
     * @return boolean is exist information
     * @throws DaoException
     */

    boolean isCreated(int id) throws DaoException;

    /**
     * Find limited amount of all trainers
     * @param recordPage
     * @param currentPage
     * @return list of trainers
     * @throws DaoException
     */

    List<Trainer> findTrainersList(int currentPage, int recordPage) throws DaoException;

    /**
     * Find number of trainers
     * @return number of trainers
     * @throws DaoException
     */

    int findTrainersNumber() throws DaoException;

    /**
     * Create trainer information at the specified trainer entity
     * @param trainer
     * @return boolean was created information
     * @throws DaoException
     */

    boolean createTrainer(Trainer trainer) throws DaoException;


    /**
     * Find trainer at the specified trainerID
     * @param trainerId
     * @return map of trainers
     * @throws DaoException
     */

    Trainer findTrainerInformation(int trainerId) throws DaoException;
}
