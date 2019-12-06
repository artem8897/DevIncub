package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.entity.UserType;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

public interface UserDao<K, T extends Entity>  {
    Map<K, T> findAll(K currentPage, K recordPerPage) throws DaoException;
    //  boolean delete(K id);
    boolean delete(T entity);
    //    int create(T entity) throws DaoException;
    boolean updateUserType(UserType userType) throws DaoException;

}
