package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.StudentDaoImpl;
import by.bsu.finalproject.entity.Student;
import by.bsu.finalproject.service.StudentService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.validator.PersonalInformationValidator;
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

    public Map<Integer, Student> findAllPersonalInformationMap() throws LogicException {

        Map<Integer, Student> personInformationMap ;

        try {
            personInformationMap = personalInformationDao.findAllStudents();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return personInformationMap;
    }

    public Map<Integer, Student> findLimitTrainerMap(String currentPageString, String recordPageString, int trainerId) throws LogicException {

        Map<Integer, Student> personInformationMap = new HashMap<>();

        if(currentPageString != null && recordPageString != null){

            int currentPage;
            int recordsPerPage;
            Pattern pat = Pattern.compile(REGULAR_PAGE_NUMBER);
            Matcher matcherCurrent = pat.matcher(currentPageString);
            Matcher matcherRecord = pat.matcher(recordPageString);
            if(matcherCurrent.matches() && matcherRecord.matches()) {
                currentPage = Integer.parseInt(currentPageString);
                recordsPerPage = Integer.parseInt(recordPageString);
                try {
                    personInformationMap = personalInformationDao.findStudentsByTrainer(currentPage,recordsPerPage,trainerId);
                } catch (DaoException e) {
                    throw new LogicException(e);
                }
            }
        }
        return personInformationMap;
    }


    public Integer findNumberOfRows () throws LogicException {

        try {
            return personalInformationDao.findNumberStudents();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public boolean updatePaymentStatus(int userId, int paymentStatus) throws LogicException {
        try {
            return personalInformationDao.updatePayStatus(userId, paymentStatus);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Integer findNumberOfRowsStudentsWithPaidTraining (int trainerId) throws LogicException {

        try {
            return personalInformationDao.findNumberOfStudentsWhoPaid(trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Integer findNumberOfRowsStudentsWithNoDiet (int trainerId) throws LogicException {

        try {
            return personalInformationDao.findNumberStudentsWithNoDiet(trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public boolean addInformation(int userId, String name,String secondName,String sex, int weight, int height, Map<String, String> map) throws LogicException {

        Student person = new Student();
        person.setId(userId);
        person.setSex(sex);
        person.setName(name);
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        boolean isValidPersonalInformation = ValidatePersonalInformation(person, map );

        if(isValidPersonalInformation){
            try {
                boolean isAlreadyExist = personalInformationDao.isCreated(userId);
                if(!isAlreadyExist) {
                    return personalInformationDao.createStudent(person);
                }
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }
        return false;

    }

    public Map<String, String> findPersonalInformation(int userId) throws LogicException {

        Student student;
        Map<String, String> attributeMap = new HashMap<>();
        try {
            student = personalInformationDao.findStudentInformation(userId);
            attributeMap.put(ServiceName.NAME, student.getName());
            attributeMap.put(ServiceName.SECOND_NAME, student.getSecondName());
            attributeMap.put(ServiceName.WEIGHT, String.valueOf(student.getWeight()));
            attributeMap.put(ServiceName.HEIGHT, String.valueOf(student.getHeight()));

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return attributeMap;
    }

    public Map<Integer, Student> findStudentsByTrainer(int trainerId, String condition, String currentPageString, String recordPageString) throws LogicException {

        Map<Integer, Student> personalInformationMap = new HashMap<>();

        if(currentPageString != null && recordPageString != null){

            int currentPage;
            int recordsPerPage;
            Pattern pat = Pattern.compile(REGULAR_PAGE_NUMBER);
            Matcher matcherCurrent = pat.matcher(currentPageString);
            Matcher matcherRecord = pat.matcher(recordPageString);
            if(matcherCurrent.matches() && matcherRecord.matches()) {
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
                        throw new LogicException(e);
                    }
                }
            }
        }
        return personalInformationMap;
    }

    public Map<Integer, Student> findLimitUserMap(String currentPageString, String recordPageString ) throws LogicException {

        Map<Integer, Student> personInformationMap = new HashMap<>() ;

        if(currentPageString != null && recordPageString != null){

            int currentPage;
            int recordsPerPage;
            Pattern pat = Pattern.compile(REGULAR_PAGE_NUMBER);
            Matcher matcherCurrent = pat.matcher(currentPageString);
            Matcher matcherRecord = pat.matcher(recordPageString);
            if(matcherCurrent.matches() && matcherRecord.matches()) {
                currentPage = Integer.parseInt(currentPageString);
                recordsPerPage = Integer.parseInt(recordPageString);
                try {
                    personInformationMap = personalInformationDao.findAllStudents(currentPage,recordsPerPage);
                } catch (DaoException e) {
                    throw new LogicException(e);
                }
            }
        }
        return personInformationMap;
    }

    public boolean isExist(int id) throws LogicException {

        try {
            return personalInformationDao.isCreated(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public  boolean updateUserInformation(int userId, String name,String secondName,String sex, int weight, int height,  Map<String, String> map) throws LogicException {

        Student person = new Student();
        person.setId(userId);
        person.setSex(sex);
        person.setName(name);
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        boolean isValidPersonalInformation = ValidatePersonalInformation(person, map );

        if(isValidPersonalInformation){

            try {
                return personalInformationDao.updateStudent(person);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }

    private boolean ValidatePersonalInformation(Student student, Map<String,String> map){

        boolean isValidHeight = PersonalInformationValidator.INSTANCE.isValidHeight(student.getHeight());

        if(isValidHeight){
            map.put(ServiceName.HEIGHT,String.valueOf(student.getHeight()));
        }else{
            map.put(ServiceName.HEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidWeight = PersonalInformationValidator.INSTANCE.isValidWeight(student.getWeight());

        if(isValidWeight){
            map.put(ServiceName.WEIGHT,String.valueOf(student.getWeight()));
        }else{
            map.put(ServiceName.WEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidSex = student.getSex() != null && PersonalInformationValidator.INSTANCE.isValidSex(student.getSex());

        if(isValidSex){
            map.put(ServiceName.SEX, student.getSex());
        }else{
            map.put(ServiceName.SEX,ServiceName.WRONG_FIELD);
        }

        boolean isValidSecondName = student.getSecondName() != null && PersonalInformationValidator.INSTANCE.isValidName(student.getSecondName());

        if(isValidSecondName){
            map.put(ServiceName.SECOND_NAME, student.getSecondName());
        }else{
            map.put(ServiceName.SECOND_NAME,ServiceName.WRONG_FIELD);
        }

        boolean isValidName = student.getName() != null && PersonalInformationValidator.INSTANCE.isValidName(student.getName());

        if(isValidName){
            map.put(ServiceName.NAME, student.getName());
        }else{
            map.put(ServiceName.NAME,ServiceName.WRONG_FIELD);
        }

        return isValidHeight && isValidWeight && isValidSex && isValidSecondName && isValidName;

    }
}
