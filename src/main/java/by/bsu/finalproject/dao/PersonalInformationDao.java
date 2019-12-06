package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

public interface PersonalInformationDao<K, T extends Entity>{
    Map<K, T> findAllStudents() throws DaoException;
    //  boolean delete(K id);
    boolean delete(T entity);
    boolean create(T entity) throws DaoException;
    boolean update(T entity) throws DaoException;
    Map<K, T> findAllStudentsByTrainerId(K id) throws DaoException;
    T findPersonalInformation(K userId) throws DaoException;
}
