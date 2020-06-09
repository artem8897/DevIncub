package by.bsu.finalproject.dao;

import by.bsu.devincub.connectionpool.ConnectionPool;
import by.bsu.devincub.dao.UserDao;
import by.bsu.devincub.dao.impl.UserDaoImpl;
import by.bsu.devincub.entity.User;
import by.bsu.devincub.exception.ConnectionPoolException;
import by.bsu.devincub.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;

public class UserDaoTest {
    Connection connection;
    UserDao dietDao = new UserDaoImpl();

    @BeforeTest
    void beforeTest() {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testAddInformation() throws DaoException {
        User user = new User();
        user.setSecondName("kuzmik");
        user.setId(1);
        user.setUsername("artsiom");
        User actualUser = dietDao.findEntityById(1);
        System.out.println(actualUser);
        Assert.assertEquals(actualUser, user);
    }

   @Test
    public void testIsDietExist() throws DaoException {
       User diet = new User();
       diet.setSecondName("Faneev");
       diet.setId(5);
       diet.setUsername("Locj");
       User user = dietDao.findRichestUser();
       System.out.println(user);
       Assert.assertEquals(diet, user);
    }

}
