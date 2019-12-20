package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.TrainingDaoImpl;
import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.Connection;

public class TrainingDaoTest {

    private final String DATABASE_URL = ("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
    Connection connection;
    TrainingDaoImpl trainingDao = DaoFactory.INSTANCE.getTrainingDao();
    int userId = 12;
    String date = "2019-12-14";
    String trainingType = "hard working";
    String personality = "make an supp in running";
    int trainingId = 1;

    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testCreatePersonalTrainingForUser() throws DaoException {

        Training training = new Training();
        training.setTrainingType(trainingType);
        training.setPeriodicity(date);
        training.setPersonality(personality);

        trainingDao.createTraining(userId, training);

        Training createdTraining = trainingDao.findTrainingById(1);

        Assert.assertEquals(training, createdTraining);

    }
    @Test
    public void testUpdateTraining() throws DaoException {

        Training training = new Training();

        training.setPersonality(personality);
        training.setPeriodicity(date);
        training.setId(trainingId);
        training.setTrainingType(trainingType);

        trainingDao.update(training);

        Training updatedTraining = trainingDao.findTrainingById(1);

        Assert.assertEquals(training, updatedTraining);

    }

    @Test
    public void testDeleteTraining() throws DaoException {

        boolean isDeleted = trainingDao.deleteTraining(trainingId);
        Training training = trainingDao.findTrainingById(trainingId);
        boolean isEmpty = (training == null);
        Assert.assertEquals(isDeleted, isEmpty);

    }
}
