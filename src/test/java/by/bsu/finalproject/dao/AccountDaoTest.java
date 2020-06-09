package by.bsu.finalproject.dao;

import by.bsu.devincub.connectionpool.ConnectionPool;
import by.bsu.devincub.dao.AccountDao;
import by.bsu.devincub.dao.impl.AccountDaoImpl;
import by.bsu.devincub.entity.Account;
import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;
import java.util.List;

public class AccountDaoTest {
    Connection connection;
    AccountDao accountDao = new AccountDaoImpl();

    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testAddInformation() throws DaoException {
        int expected = 10;
        int actual = accountDao.findNumberOfRows();
        System.out.println(actual);
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void testIsDietExist() throws DaoException {
        List<Account> accountList = accountDao.findAllAccounts();
        Assert.assertFalse(accountList.isEmpty());
    }
}
