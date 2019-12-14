package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.List;
import java.util.Map;

/**
 * Basic interface for TrainerDao.
 *
 * @author A. Kuzmik
 */

public interface TrainerDao<K, T extends Entity> {

    /**
     * Find all trainers
     * @return trainers map
     */

    Map<K, T> findAll() throws DaoException;

    /**
     * Update trainer at the specified trainer entity
     * @param trainer
     * @return boolean was updated
     */

    boolean update(T trainer) throws DaoException;

    /**
     * Define was created trainer information at the specified trainerId
     * @param id
     * @return boolean is exist information
     */

    boolean isCreated(K id) throws DaoException;

    /**
     * Find limited amount of all trainers
     * @param recordPage
     * @param currentPage
     * @return list of trainers
     */

    List<T> findAllInLimit(int currentPage, int recordPage) throws DaoException;

    /**
     * Find number of trainers
     * @return number of trainers
     */

    Integer findNumberOfRows() throws DaoException;

    /**
     * Create trainer information at the specified trainer entity
     * @param trainer
     * @return boolean was created information
     */

    boolean create(T trainer) throws DaoException;


    /**
     * Find trainer at the specified trainerID
     * @param trainerId
     * @return map of trainers
     */

    T findTrainerInformation(int trainerId) throws DaoException;
}
