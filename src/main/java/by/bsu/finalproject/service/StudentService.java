package by.bsu.finalproject.service;

import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.exception.LogicException;

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
     * @throws LogicException
     */

    Map<Integer, Student> findAllPersonalInformationMap() throws LogicException;

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
     * @throws LogicException
     */

    boolean addInformation(int userId, String name,String secondName,String sex, int weight, int height,  Map<String, String> map) throws LogicException;


    /**
     * Find students at the specified userId
     * @param userId
     * @return map of students
     * @throws LogicException
     */

    Map<String, String> findPersonalInformation(int userId) throws LogicException;

    /**
     * Define was created student information at the specified userId
     * @param id
     * @return boolean is exist information
     * @throws LogicException
     */

    boolean isExist(int id) throws LogicException;
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
     * @throws LogicException
     */

    boolean updateUserInformation(int userId, String name,String secondName,String sex, int weight, int height, Map<String, String> map) throws LogicException;


    /**
     * Find number of students
     * @return number of students
     * @throws LogicException
     */

    Integer findNumberOfRows() throws LogicException;

    /**
     * Find limited amount of students at the specified trainerId
     * @param trainerId
     * @param recordPageString
     * @param currentPageString
     * @return map of students if all fields are valid
     * @throws LogicException
     */

    Map<Integer, Student> findLimitTrainerMap(String currentPageString, String recordPageString, int trainerId) throws LogicException;


    /**
     * Find limited amount of all students
     * @param currentPageString
     * @param recordPageString
     * @return map of students
     * @throws LogicException
     */

    Map<Integer, Student> findLimitUserMap(String currentPageString, String recordPageString ) throws LogicException;

    /**
     * Find limited amount of all students with paid trainings or diet in case of set condition at the specified trainerId
     * @param recordPageString
     * @param id
     * @param currentPageString
     * @param condition
     * @return map of students
     * @throws LogicException
     */

    Map<Integer, Student> findStudentsByTrainer(int id, String condition, String currentPageString, String recordPageString) throws LogicException;

    /**
     * Define number of students with Paid trainings  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws LogicException
     */

    Integer findNumberOfRowsStudentsWithPaidTraining (int trainerId) throws LogicException;

    /**
     * Define number of students with no diets  at the specified trainerId
     * @param trainerId
     * @return map of students
     * @throws LogicException
     */

    Integer findNumberOfRowsStudentsWithNoDiet(int trainerId) throws LogicException;


    /**
     * Update student status at the specified userId
     * @param userId
     * @param paymentStatus
     * @return boolean was updated
     * @throws LogicException
     */


    boolean updatePaymentStatus(int userId, int paymentStatus) throws LogicException;

}
