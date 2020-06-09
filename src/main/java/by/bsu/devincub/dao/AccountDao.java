package by.bsu.devincub.dao;

import by.bsu.devincub.entity.Account;
import by.bsu.devincub.exception.DaoException;

import java.util.List;

public interface AccountDao {

    int findNumberOfRows() throws DaoException;
    List<Account> findAllAccounts() throws DaoException;
}
