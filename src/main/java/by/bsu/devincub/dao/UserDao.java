package by.bsu.devincub.dao;

import by.bsu.devincub.exception.DaoException;
import by.bsu.devincub.entity.User;
import java.util.List;

public interface UserDao {

    List<User> findAllUsers() throws DaoException;
    User findEntityById(Integer id) throws DaoException;
    User findRichestUser() throws DaoException;
}