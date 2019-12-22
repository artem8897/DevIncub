package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.DietDaoImpl;
import by.bsu.finalproject.entity.Diet;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;


public class DietDaoTest {

    Connection connection;
    DietDaoImpl dietDao = DaoFactory.INSTANCE.getDietDao();
    int userId = 68;

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

        Diet diet = new Diet();
        diet.setDietType("Low calorine diet");
        diet.setFats(123);
        diet.setCarbohydrates(120);
        diet.setProteins(111);
        dietDao.createDiet(diet, 68);
        Diet createdDiet = dietDao.findUsersDiet(userId);

        Assert.assertEquals(diet, createdDiet);

    }
   @Test
    public void testIsDietExist() throws DaoException {

        boolean isExist = dietDao.isUsersDietExist(userId);

        Assert.assertTrue(isExist);

    }
    @Test
    public void testUpdateDiet() throws DaoException {

        Diet diet = new Diet();
        diet.setDietType("High calorine diet");
        diet.setFats(123);
        diet.setCarbohydrates(120);
        diet.setProteins(111);
        dietDao.updateDiet(68,diet);
        Diet createdDiet = dietDao.findUsersDiet(userId);

        Assert.assertEquals(diet, createdDiet);
    }

}
