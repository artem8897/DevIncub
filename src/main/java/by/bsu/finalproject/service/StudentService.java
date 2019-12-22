package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.exception.ServiceException;

import java.util.Map;

/**
 * Basic interface for InformationService.
 *
 * @author A. Kuzmik
 */

public interface StudentService {

    /**
     * Find all students
     * @return students map
     * @throws ServiceException
     */

    Map<Integer, Student> findAllPersonalInformationMap() throws ServiceException;

    /**
     * Create student at the specified personal information entity
     * @param userId
     * @param name
     * @param secondName
     * @param sex
     * @param weight
     * @param height
     * @param map
     * @return true if fields are valid and created
     * @throws ServiceException
     */

    boolean addInformation(int userId, String name,String secondName,String sex, String weight, String height,  Map<String, String> map, String code, String email) throws ServiceException;


    /**
     * Find students at the specified userId
     * @param userId
     * @return map of students
     * @throws ServiceException
     */

    Map<String, String> findPersonalInformation(int userId) throws ServiceException;

    /**
     * Define was created student information at the specified userId
     * @param id
     * @return boolean is exist information
     * @throws ServiceException
     */

    boolean isExist(int id) throws ServiceException;
    /**
     * Update student at the specified personal information entity
     * @param userId
     * @param name
     * @param secondName
     * @param sex
     * @param weight
     * @param height
     * @param map
     * @return true if fields are valid and created
     * @throws ServiceException
     */

    boolean updateUserInformation(int userId, String name,String secondName,String sex, String weight, String height, Map<String, String> map) throws ServiceException;


    /**
     * Find number of students
     * @return number of students
     * @throws ServiceException
     */

    Integer findNumberOfRows() throws ServiceException;

    /**
     * Find limited amount of students at the specified trainerId
     * @param trainerId
     * @param recordPageString
     * @param currentPageString
     * @return map of students if all fields are valid
     * @throws ServiceException
     */

    Map<Integer, Student> findLimitTrainerMap(String currentPageString, String recordPageString, int trainerId) throws ServiceException;


    /**
     * Find limited amount of all students
     * @param currentPageString
     * @param recordPageString
     * @return map of students
     * @throws ServiceException
     */

    Map<Integer, Student> findLimitUserMap(String currentPageString, String recordPageString ) throws ServiceException;

    /**
     * Find limited amount of all students with paid trainings or diet in case of set condition at the specified trainerId
     * @param recordPageString
     * @param id
     * @param currentPageString
     * @param condition
     * @return map of students
     * @throws ServiceException
     */

    Map<Integer, Student> findStudentsByTrainer(int id, String condition, String currentPageString, String recordPageString) throws ServiceException;

    /**
     * Define number of students with Paid trainings  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws ServiceException
     */

    Integer findNumberOfRowsStudentsWithPaidTraining (int trainerId) throws ServiceException;

    /**
     * Define number of students with no diets  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws ServiceException
     */

    Integer findNumberOfRowsStudentsWithNoDiet(int trainerId) throws ServiceException;


    /**
     * Update student status at the specified userId
     * @param userId
     * @param paymentStatus
     * @return boolean was updated
     * @throws ServiceException
     */


    boolean updatePaymentStatus(int userId, int paymentStatus) throws ServiceException;

}
