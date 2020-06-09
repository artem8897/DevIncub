package by.bsu.devincub.service.impl;

import by.bsu.devincub.exception.DaoException;
import by.bsu.devincub.dao.AccountDao;
import by.bsu.devincub.dao.impl.AccountDaoImpl;
import by.bsu.devincub.exception.ServiceException;
import by.bsu.devincub.service.AccountService;

public class AccountServiceImpl implements AccountService {

    private AccountDao accountDao = new AccountDaoImpl();
    public Integer findNumberOfUsers() throws ServiceException {

        try {
            return accountDao.findNumberOfRows();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
