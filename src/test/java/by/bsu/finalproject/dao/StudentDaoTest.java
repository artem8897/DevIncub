package by.bsu.finalproject.dao;

import by.bsu.finalproject.connectionpool.ConnectionPool;
import by.bsu.finalproject.dao.impl.StudentDaoImpl;
import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.exception.ConnectionPoolException;
import by.bsu.finalproject.exception.DaoException;
import org.junit.Test;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.sql.Connection;


public class StudentDaoTest {

    Connection connection;
    StudentDaoImpl personalInformationDao = DaoFactory.INSTANCE.getPersonalInformationDao();
    int userId = 68;
    String name = "Artem";
    String secondName = "Kuzmik";
    String sex = "male";
    int weight = 88;
    int height = 180;


    @BeforeTest
    void beforeTest() throws ConnectionPoolException {
        connection = ConnectionPool.INSTANCE.getConnection();
    }

    @AfterTest
    void afterTest() throws ConnectionPoolException {
        ConnectionPool.INSTANCE.destroyPool();
    }

    @Test
    public void testFindNumberOfRows() throws DaoException {

        int studentsRows = personalInformationDao.findNumberStudents();
        Assert.assertTrue(studentsRows > 0);

    }

    @Test
    public void testUpdatePaymentStatus() throws DaoException {

        boolean isUpdated = personalInformationDao.updatePayStatus(userId, 1);
        Assert.assertTrue(isUpdated);

    }

    @Test
    public void findNumberOfRowsStudentsWithPaidTraining(int trainerId) throws DaoException {

        int studentRowsWithPaidTraining = personalInformationDao.findNumberOfStudentsWhoPaid(trainerId);
        Assert.assertTrue(studentRowsWithPaidTraining > 0);

    }

    @Test
    public void testFindNumberOfRowsStudentsWithNoDiet(int trainerId) throws DaoException {

        int studentRowsWithNoDiet = personalInformationDao.findNumberStudentsWithNoDiet(trainerId);
        Assert.assertTrue(studentRowsWithNoDiet > 0);

    }

    @Test
    public void testAddInformation() throws DaoException {

        Student person = new Student();
        person.setId(userId);
        person.setSex(sex);
        person.setName(name);
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        personalInformationDao.createStudent(person);

        Student createdPerson = personalInformationDao.findStudentInformation(userId);

        Assert.assertEquals(person, createdPerson);

    }

    @Test
    public void testExisting() throws DaoException {

        boolean isExist = personalInformationDao.isCreated(userId);
        Assert.assertTrue(isExist);

    }

    @Test
    public void testUpdateUserInformation() throws DaoException {

        Student person = new Student();
        person.setId(userId);
        person.setSex(sex);
        person.setName("Arseniy");
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        personalInformationDao.updateStudent(person);

        Student updatedPerson = personalInformationDao.findStudentInformation(userId);

        Assert.assertEquals(person, updatedPerson);

    }
}





