package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;
import java.util.List;

public class TrainerDaoTest {

    private final String DATABASE_URL = ("jdbc:mysql://localhost:3306/new_schema?useUnicode=true&serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true");
    private Connection connection;
    private TrainerDaoImpl trainerDao = DaoFactory.INSTANCE.getTrainerDao();
    private int userId = 12;
    private String name = "admin";
    private int experience = 12;
    private String trainingType = "hard working";

    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testCreatingTrainer() throws DaoException {

        Trainer trainer = new Trainer();
        trainer.setId(userId);
        trainer.setTrainingType(trainingType);
        trainer.setTrainerName(name);
        trainer.setWorkExperience(experience);

         trainerDao.create(trainer);

        Trainer actualTrainer = trainerDao.findTrainerInformation(userId);

        Assert.assertEquals(trainer, actualTrainer);


    }
    @Test
    public void testExisting() throws DaoException {

        boolean isExist = trainerDao.isCreated(userId);
        Assert.assertTrue(isExist);


    }
    @Test
    public  void testUpdateTrainerInformation() throws DaoException {

        Trainer trainer = new Trainer();
        trainer.setId(userId);
        trainer.setTrainerName(name);
        trainer.setTrainingType(trainingType);
        trainer.setWorkExperience(18);

        trainerDao.updateTrainer(trainer);

        Trainer actualTrainer = trainerDao.findTrainerInformation(userId);

        Assert.assertEquals(trainer, actualTrainer);

    }
    @Test
    public void testfindingLimitTrainerMap() throws DaoException {

        List<Trainer> trainerList ;

        Trainer trainer = new Trainer();
        trainer.setId(userId);
        trainer.setTrainingType(trainingType);
        trainer.setTrainerName(name);
        trainer.setWorkExperience(experience);

        trainerList = trainerDao.findLimitTrainersList(0,10);

        boolean isExist = trainerList.contains(trainer);

        Assert.assertTrue(isExist);

    }

}
