package by.bsu.devincub.service;

import by.bsu.devincub.entity.User;
import by.bsu.devincub.exception.ServiceException;

public interface UserService {

    User findUserInBase(Integer id) throws ServiceException;
    User findRichestUser() throws ServiceException;

}
