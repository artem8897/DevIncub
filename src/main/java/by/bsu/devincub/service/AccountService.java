package by.bsu.devincub.service;

import by.bsu.devincub.exception.ServiceException;

public interface AccountService {

    Integer findNumberOfUsers() throws ServiceException;

}
