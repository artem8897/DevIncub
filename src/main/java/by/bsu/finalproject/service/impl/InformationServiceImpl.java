package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.PersonalInformationDaoImpl;
import by.bsu.finalproject.entity.PersonInformation;
import by.bsu.finalproject.service.InformationService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.validator.PersonalInformationValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for student information
 * @author A. Kuzmik
 */

public class InformationServiceImpl implements InformationService {

    private static final Logger logger = LogManager.getLogger(InformationServiceImpl.class);

    private PersonalInformationDaoImpl personalInformationDao = DaoFactory.INSTANCE.getPersonalInformationDao();

    private static final String REGULAR_PAGE_NUMBER = "\\d{1,2}";

    public Map<Integer, PersonInformation> findAllPersonalInformationMap() throws LogicException {

        Map<Integer, PersonInformation> personInformationMap ;

        try {
            personInformationMap = personalInformationDao.findAllStudents();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return personInformationMap;
    }

    public Map<Integer, PersonInformation> findLimitTrainerMap(String currentPageString, String recordPageString, int trainerId) throws LogicException {

        Map<Integer, PersonInformation> personInformationMap = new HashMap<>();

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
                    personInformationMap = personalInformationDao.findAllByTrainerInLimit(currentPage,recordsPerPage,trainerId);
                } catch (DaoException e) {
                    throw new LogicException(e);
                }
            }
        }
        return personInformationMap;
    }


    public Integer findNumberOfRows () throws LogicException {

        try {
            return personalInformationDao.findNumberOfRows();
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
            return personalInformationDao.findNumberOfRowsStudentsHavingPaid(trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Integer findNumberOfRowsStudentsWithNoDiet (int trainerId) throws LogicException {

        try {
            return personalInformationDao.findNumberOfRowsStudentsWithNoDiet(trainerId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public boolean addInformation(int userId, String name,String secondName,String sex, int weight, int height, Map map) throws LogicException {

        PersonInformation person = new PersonInformation();
        person.setId(userId);
        person.setSex(sex);
        person.setName(name);
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        boolean isValidPersonalInformation = ValidatePersonalInformation(person, map );

        if(isValidPersonalInformation){

            try {
                return personalInformationDao.create(person);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }

    public Map<String, String> findPersonalInformation(int userId) throws LogicException {

        PersonInformation personInformation;
        Map<String, String> attributeMap = new HashMap<>();
        try {
            personInformation = personalInformationDao.findPersonalInformation(userId);
            attributeMap.put(ServiceName.NAME, personInformation.getName());
            attributeMap.put(ServiceName.SECOND_NAME, personInformation.getSecondName());
            attributeMap.put(ServiceName.WEIGHT, String.valueOf(personInformation.getWeight()));
            attributeMap.put(ServiceName.HEIGHT, String.valueOf(personInformation.getHeight()));

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return attributeMap;
    }

    public Map<Integer, PersonInformation> findStudentsByTrainer(int trainerId, String condition, String currentPageString, String recordPageString) throws LogicException {

        Map<Integer, PersonInformation> personalInformationMap = new HashMap<>();

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
                            case ServiceName.DIET : personalInformationMap = personalInformationDao.findAllStudentsHavingNoDietByTrainerId(currentPage, recordsPerPage, trainerId);
                                break;
                            case ServiceName.TRAINING : personalInformationMap = personalInformationDao.findAllStudentsHavingPaidTrainingsByTrainerId(currentPage, recordsPerPage, trainerId);
                                break;
                            default: personalInformationMap = personalInformationDao.findAllByTrainerInLimit(currentPage, recordsPerPage, trainerId);
                        }
                    } catch (DaoException e) {
                        throw new LogicException(e);
                    }
                }
            }
        }
        return personalInformationMap;
    }

    public Map<Integer, PersonInformation> findLimitUserMap(String currentPageString, String recordPageString ) throws LogicException {

        Map<Integer, PersonInformation> personInformationMap = new HashMap<>() ;

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
                    personInformationMap = personalInformationDao.findAllInLimit(currentPage,recordsPerPage);
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

    public  boolean updateUserInformation(int userId, String name,String secondName,String sex, int weight, int height, Map map) throws LogicException {

        PersonInformation person = new PersonInformation();
        person.setId(userId);
        person.setSex(sex);
        person.setName(name);
        person.setHeight(height);
        person.setWeight(weight);
        person.setSecondName(secondName);

        boolean isValidPersonalInformation = ValidatePersonalInformation(person, map );

        if(isValidPersonalInformation){

            try {
                return personalInformationDao.update(person);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }

    private boolean ValidatePersonalInformation(PersonInformation personInformation, Map<String,String> map){

        boolean isValidHeight = PersonalInformationValidator.INSTANCE.isValidHeight(personInformation.getHeight());

        if(isValidHeight){
            map.put(ServiceName.HEIGHT,String.valueOf(personInformation.getHeight()));
        }else{
            map.put(ServiceName.HEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidWeight = PersonalInformationValidator.INSTANCE.isValidWeight(personInformation.getWeight());

        if(isValidWeight){
            map.put(ServiceName.WEIGHT,String.valueOf(personInformation.getWeight()));
        }else{
            map.put(ServiceName.WEIGHT,ServiceName.WRONG_FIELD);
        }

        boolean isValidSex = personInformation.getSex() != null && PersonalInformationValidator.INSTANCE.isValidSex(personInformation.getSex());

        if(isValidSex){
            map.put(ServiceName.SEX,personInformation.getSex());
        }else{
            map.put(ServiceName.SEX,ServiceName.WRONG_FIELD);
        }

        boolean isValidSecondName = personInformation.getSecondName() != null && PersonalInformationValidator.INSTANCE.isValidName(personInformation.getSecondName());

        if(isValidSecondName){
            map.put(ServiceName.SECOND_NAME,personInformation.getSecondName());
        }else{
            map.put(ServiceName.SECOND_NAME,ServiceName.WRONG_FIELD);
        }

        boolean isValidName = personInformation.getName() != null && PersonalInformationValidator.INSTANCE.isValidName(personInformation.getName());

        if(isValidName){
            map.put(ServiceName.NAME,personInformation.getName());
        }else{
            map.put(ServiceName.NAME,ServiceName.WRONG_FIELD);
        }

        return isValidHeight && isValidWeight && isValidSex && isValidSecondName && isValidName;

    }
}
