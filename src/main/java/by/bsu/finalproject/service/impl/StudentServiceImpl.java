package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.StudentDaoImpl;
import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.service.StudentService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.ServiceException;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.validator.StudentInformationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for student information
 * @author A. Kuzmik
 */

public class StudentServiceImpl implements StudentService {

    private static final Logger logger = LogManager.getLogger(StudentServiceImpl.class);
    private StudentDaoImpl personalInformationDao = DaoFactory.INSTANCE.getPersonalInformationDao();
    private static final String REGULAR_PAGE_NUMBER = "\\d{1,2}";

    public Map<Integer, Student> findAllPersonalInformationMap() throws ServiceException {

        Map<Integer, Student> personInformationMap ;

        try {
            personInformationMap = personalInformationDao.findAllStudents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return personInformationMap;
    }

    public Map<Integer, Student> findLimitTrainerMap(String currentPageString, String recordPageString, int trainerId) throws ServiceException {

        Map<Integer, Student> personInformationMap = new HashMap<>();

        if (currentPageString != null && recordPageString != null) {

            int currentPage;
            int recordsPerPage;

            boolean isCurrentPageValid = isPageValid(currentPageString);
            boolean isRecordsPerPageValid = isPageValid(recordPageString);

            if (isCurrentPageValid && isRecordsPerPageValid) {
                currentPage = Integer.parseInt(currentPageString);
                recordsPerPage = Integer.parseInt(recordPageString);
                try {
                    personInformationMap = personalInformationDao.findStudentsByTrainer(currentPage, recordsPerPage, trainerId);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            }
        }
        return personInformationMap;
    }


    public Integer findNumberOfRows () throws ServiceException {

        try {
            return personalInformationDao.findNumberStudents();
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public boolean updatePaymentStatus(int userId, int paymentStatus) throws ServiceException {

        try {
            return personalInformationDao.updatePayStatus(userId, paymentStatus);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public Integer findNumberOfRowsStudentsWithPaidTraining (int trainerId) throws ServiceException {

        try {
            return personalInformationDao.findNumberOfStudentsWhoPaid(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
    public Integer findNumberOfRowsStudentsWithNoDiet (int trainerId) throws ServiceException {

        try {
            return personalInformationDao.findNumberStudentsWithNoDiet(trainerId);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean addInformation(int userId, String name,String secondName,String sex, String weight, String height, Map<String, String> map) throws ServiceException {

        boolean isValidPersonalInformation = ValidatePersonalInformation(name, secondName, sex, weight, height, map);

        if (isValidPersonalInformation) {

            Student person = new Student();
            person.setId(userId);
            person.setSex(sex);
            person.setName(name);
            person.setHeight(Integer.parseInt(height));
            person.setWeight(Integer.parseInt(weight));
            person.setSecondName(secondName);

            try {
                boolean isAlreadyExist = personalInformationDao.isCreated(userId);
                if (!isAlreadyExist) {
                    return personalInformationDao.createStudent(person);
                }
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }
        return false;
    }

    public Map<String, String> findPersonalInformation(int userId) throws ServiceException {

        Student student;
        Map<String, String> attributeMap = new HashMap<>();
        try {
            student = personalInformationDao.findStudentInformation(userId);
            attributeMap.put(ServiceName.NAME, student.getName());
            attributeMap.put(ServiceName.SECOND_NAME, student.getSecondName());
            attributeMap.put(ServiceName.WEIGHT, String.valueOf(student.getWeight()));
            attributeMap.put(ServiceName.HEIGHT, String.valueOf(student.getHeight()));

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
        return attributeMap;
    }

    public Map<Integer, Student> findStudentsByTrainer(int trainerId, String condition, String currentPageString, String recordPageString) throws ServiceException {

        Map<Integer, Student> personalInformationMap = new HashMap<>();

        if(currentPageString != null && recordPageString != null){

            int currentPage;
            int recordsPerPage;

            boolean isCurrentPageValid = isPageValid(currentPageString);
            boolean isRecordsPerPageValid = isPageValid(recordPageString);

            if(isCurrentPageValid && isRecordsPerPageValid) {
                currentPage = Integer.parseInt(currentPageString);
                recordsPerPage = Integer.parseInt(recordPageString);
                if(!condition.isBlank()){
                    try {
                        switch (condition){
                            case ServiceName.DIET : personalInformationMap = personalInformationDao.findStudentsWithNoDiet(currentPage, recordsPerPage, trainerId);
                                break;
                            case ServiceName.TRAINING : personalInformationMap = personalInformationDao.findStudentsWithPaidTrainings(currentPage, recordsPerPage, trainerId);
                                break;
                            default: personalInformationMap = personalInformationDao.findStudentsByTrainer(currentPage, recordsPerPage, trainerId);
                        }
                    } catch (DaoException e) {
                        throw new ServiceException(e);
                    }
                }
            }
        }
        return personalInformationMap;
    }

    public Map<Integer, Student> findLimitUserMap(String currentPageString, String recordPageString ) throws ServiceException {

        Map<Integer, Student> personInformationMap = new HashMap<>() ;

        if(currentPageString != null && recordPageString != null){

            int currentPage;
            int recordsPerPage;

            boolean isCurrentPageValid = isPageValid(currentPageString);
            boolean isRecordsPerPageValid = isPageValid(recordPageString);

            if(isRecordsPerPageValid && isCurrentPageValid) {
                currentPage = Integer.parseInt(currentPageString);
                recordsPerPage = Integer.parseInt(recordPageString);
                try {
                    personInformationMap = personalInformationDao.findAllStudents(currentPage,recordsPerPage);
                } catch (DaoException e) {
                    throw new ServiceException(e);
                }
            }
        }
        return personInformationMap;
    }

    public boolean isExist(int id) throws ServiceException {

        try {
            return personalInformationDao.isCreated(id);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    public boolean updateUserInformation(int userId, String name,String secondName,String sex, String weight, String height,  Map<String, String> map) throws ServiceException {

        boolean isValidPersonalInformation = ValidatePersonalInformation(name, secondName, sex, weight, height, map);

        if(isValidPersonalInformation){

            Student person = new Student();
            person.setId(userId);
            person.setSex(sex);
            person.setName(name);
            person.setHeight(Integer.parseInt(height));
            person.setWeight(Integer.parseInt(weight));
            person.setSecondName(secondName);

            try {
                return personalInformationDao.updateStudent(person);
            } catch (DaoException e) {
                throw new ServiceException(e);
            }
        }else{
            return false;
        }
    }

    private boolean ValidatePersonalInformation(String name,String secondName,String sex, String weight, String height, Map<String,String> map){

        boolean isValidHeight = height != null &&  StudentInformationValidator.INSTANCE.isValidHeight(height);

        if(isValidHeight){
            map.put(ServiceName.HEIGHT, height);
        }else{
            map.put(ServiceName.HEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidWeight = weight != null && StudentInformationValidator.INSTANCE.isValidWeight(weight);

        if(isValidWeight){
            map.put(ServiceName.WEIGHT, weight);
        }else{
            map.put(ServiceName.WEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidSex = sex != null && StudentInformationValidator.INSTANCE.isValidSex(sex);

        if(isValidSex){
            map.put(ServiceName.SEX, sex);
        }else{
            map.put(ServiceName.SEX,ServiceName.WRONG_FIELD);
        }

        boolean isValidSecondName = secondName != null && StudentInformationValidator.INSTANCE.isValidName(secondName);

        if(isValidSecondName){
            map.put(ServiceName.SECOND_NAME, secondName);
        }else{
            map.put(ServiceName.SECOND_NAME,ServiceName.WRONG_FIELD);
        }

        boolean isValidName = name != null && StudentInformationValidator.INSTANCE.isValidName(name);

        if(isValidName){
            map.put(ServiceName.NAME, name);
        }else{
            map.put(ServiceName.NAME,ServiceName.WRONG_FIELD);
        }

        return isValidHeight && isValidWeight && isValidSex && isValidSecondName && isValidName;
    }

    private boolean isPageValid(String page){

        Pattern pat = Pattern.compile(REGULAR_PAGE_NUMBER);
        Matcher matcher = pat.matcher(page);
        return matcher.matches();
    }
}
