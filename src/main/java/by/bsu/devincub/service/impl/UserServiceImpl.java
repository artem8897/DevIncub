package by.bsu.devincub.service.impl;

import by.bsu.devincub.dao.UserDao;
import by.bsu.devincub.dao.impl.UserDaoImpl;
import by.bsu.devincub.exception.DaoException;
import by.bsu.devincub.exception.ServiceException;
import by.bsu.devincub.service.UserService;
import by.bsu.devincub.entity.User;

public class UserServiceImpl implements UserService {

    private UserDao userDao = new UserDaoImpl();

    public User findUserInBase(Integer id) throws ServiceException {
        try {
            return userDao.findEntityById(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public User findRichestUser() throws ServiceException {

        try {
            return userDao.findRichestUser();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

}
