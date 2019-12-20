package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.TrainingDaoImpl;
import by.bsu.finalproject.entity.Training;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.service.TrainingService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.validator.TrainingValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for training
 * @author A. Kuzmik
 */

public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LogManager.getLogger(TrainingServiceImpl.class);

    private TrainingDaoImpl trainingDao = DaoFactory.INSTANCE.getTrainingDao();

    private static final String REGULAR_PAGE_NUMBER = "\\d{1,2}";

    public boolean createPersonalTrainingForUser(int userId, String periodicity, String trainingType, String personality, Map<String, String> map) throws LogicException {

        Training training = new Training();
        training.setTrainingType(trainingType);
        training.setPeriodicity(periodicity);
        training.setPersonality(personality);

        boolean isValidTraining = validateTraining(training, map);

        if(isValidTraining){

            try {
                return trainingDao.createTraining(userId, training);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }
    public boolean deleteTraining(int trainingId) throws LogicException {
        try {
            return trainingDao.deleteTraining(trainingId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public Map<Integer, Training> findUsersTrainings(String currentPageString, String recordPageString, int userId ) throws LogicException {

        Map<Integer, Training> trainingMap = new HashMap<>() ;

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
                    trainingMap = trainingDao.findUsersTrainings(currentPage,recordsPerPage, userId);
                } catch (DaoException e) {
                    throw new LogicException(e);
                }
            }
        }
        return trainingMap;
    }
    public Integer findNumberOfRows (int userId) throws LogicException {
        try {
             return trainingDao.findNumberOfTrainings(userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }
    public Map<Integer, Training> findUsersTraining(int userId) throws LogicException {

        Map<Integer, Training> trainingMap;

        try {
            trainingMap = trainingDao.findUsersTrainingMap(userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return trainingMap;
    }
    public  boolean updateTraining(int trainingId,String personality, String periodicity, String trainingType, Map<String, String> map) throws LogicException {

        Training training = new Training();

        training.setPersonality(personality);
        training.setPeriodicity(periodicity);
        training.setId(trainingId);
        training.setTrainingType(trainingType);

        boolean isValidTraining = validateTraining(training, map);

        if(isValidTraining){

            try {
                return trainingDao.update(training);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }
    public Map<String, String> findTrainingById(int trainingId) throws LogicException {

        Training training;
        Map <String,String> trainingPramsMap = new HashMap<>();

        try {
            training = trainingDao.findTrainingById(trainingId);
            trainingPramsMap.put(ServiceName.DATE, training.getDate());
            trainingPramsMap.put(ServiceName.TRAINING_TYPE, training.getTrainingType());
            trainingPramsMap.put(ServiceName.PERSONALITY, training.getPersonality());
            trainingPramsMap.put(ServiceName.TRAINING_ID, String.valueOf(trainingId));

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return trainingPramsMap;
    }

    private boolean validateTraining(Training training, Map<String, String> map){

        boolean isValidDate = training.getDate() != null && TrainingValidator.INSTANCE.isValidDate(training.getDate());

        if(isValidDate){
            map.put(ServiceName.DATE,training.getDate());
        }else{
            map.put(ServiceName.DATE,ServiceName.WRONG_FIELD);

        }
        boolean isValidTrainingType = training.getTrainingType() != null && TrainingValidator.INSTANCE.isValidTrainingType(training.getTrainingType());

        if(isValidTrainingType){
            map.put(ServiceName.TRAINING_TYPE,training.getTrainingType());
        }else{
            map.put(ServiceName.TRAINING_TYPE,ServiceName.WRONG_FIELD);
        }

        boolean isValidPersonality = training.getPersonality() != null && TrainingValidator.INSTANCE.isValidPersonality(training.getPersonality());

        if(isValidPersonality){
            map.put(ServiceName.PERSONALITY,training.getPersonality());
        }else{
            map.put(ServiceName.PERSONALITY,ServiceName.WRONG_FIELD);
        }

        return isValidDate && isValidTrainingType && isValidPersonality;

    }
}
