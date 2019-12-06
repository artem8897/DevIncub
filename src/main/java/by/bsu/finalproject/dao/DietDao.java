package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

public interface DietDao<K, T extends Entity> {
    Map<K, T> findAll() throws DaoException;
    boolean delete(K id);
    boolean create(T entity, K userId) throws DaoException;
    boolean update(K userId, T entity) throws DaoException;
    T findUsersDiet(K userId) throws DaoException;
}
