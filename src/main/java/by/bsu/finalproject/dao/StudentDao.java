package by.bsu.finalproject.dao;

import by.bsu.finalproject.entity.Entity;
import by.bsu.finalproject.exception.DaoException;

import java.util.Map;

/**
 * Basic interface for PersonalInformationDao.
 *
 * @author A. Kuzmik
 */

public interface StudentDao<K, T extends Entity>{

    /**
     * Find all students
     * @return students map
     * @throws DaoException
     */

    Map<K, T> findAllStudents() throws DaoException;

    /**
     * Create student at the specified personal information entity
     * @param student
     * @return price
     * @throws DaoException
     */

    boolean createStudent(T student) throws DaoException;

    /**
     * Define was created student information at the specified userId
     * @param studentId
     * @return boolean is exist information
     * @throws DaoException
     */

    boolean isCreated(K studentId) throws DaoException;

    /**
     * Find number of students
     * @return number of students
     * @throws DaoException
     */

    K findNumberStudents() throws DaoException;

    /**
     * Find limited amount of students at the specified trainerId
     * @param trainerId
     * @param recordPage
     * @param currentPage
     * @return map of students
     * @throws DaoException
     */

    Map<K, T> findStudentsByTrainer(int currentPage, int recordPage, int trainerId) throws DaoException;

    /**
     * Find limited amount of all students
     * @param recordPage
     * @param currentPage
     * @return map of students
     * @throws DaoException
     */

    Map<K, T> findAllStudents(int currentPage, int recordPage) throws DaoException;

    /**
     * Find limited amount of all students with paid trainings at the specified trainerId
     * @param recordPage
     * @param trainerId
     * @param currentPage
     * @return map of students
     * @throws DaoException
     */

    Map<K, T> findStudentsWithPaidTrainings(int currentPage, int recordPage, Integer trainerId) throws DaoException;

    /**
     * Define number of students with Paid trainings  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws DaoException
     */

    K findNumberOfStudentsWhoPaid(int trainerId) throws DaoException;

    /**
     * Find limited amount of all students with no diets at the specified trainerId
     * @param recordPage
     * @param id
     * @param currentPage
     * @return map of students
     * @throws DaoException
     */

    Map<K, T> findStudentsWithNoDiet(int currentPage, int recordPage, Integer id) throws DaoException;


    /**
     * Define number of students with no diets  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws DaoException
     */

    K findNumberStudentsWithNoDiet(int trainerId) throws DaoException;

    /**
     * Find students at the specified userId
     * @param userId
     * @return map of students
     * @throws DaoException
     */

    T findStudentInformation(K userId) throws DaoException;


    /**
     * Update student status at the specified userId
     * @param userId
     * @param payStatus
     * @return boolean was updated
     * @throws DaoException
     */


    boolean updatePayStatus(K userId, K payStatus) throws DaoException;

    /**
     * Update student at the specified personal information entity
     * @param student
     * @return boolean was updated
     * @throws DaoException
     */

    boolean updateStudent(T student) throws DaoException;
}
