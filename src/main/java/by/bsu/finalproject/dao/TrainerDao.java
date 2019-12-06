package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

public interface TrainerDao<K, T extends Entity> {
    Map<K, T> findAll() throws DaoException;
    //  boolean delete(K id);
    boolean delete(T entity);
    //    int create(T entity) throws DaoException;
    boolean update(T entity) throws DaoException;

}
