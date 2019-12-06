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

public class TrainingServiceImpl implements TrainingService {

    private static final Logger logger = LogManager.getLogger(TrainingServiceImpl.class);
    private TrainingDaoImpl trainingDao = DaoFactory.INSTANCE.getTrainingDao();

    public boolean createPersonalTrainingForUser(int userId, String periodicity, String trainingType, String personality, Map map) throws LogicException {

        Training training = new Training();
        training.setId(userId);
        training.setTrainingType(trainingType);
        training.setPeriodicity(periodicity);
        training.setPersonality(personality);

        boolean isValidTraining = validateTraining(training, map);

        if(isValidTraining){

            try {
                return trainingDao.create(training);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }

    public Map<Integer, Training> findLimitTrainerMap(int currentPage, int recordPage, int userId ) throws LogicException {

        Map<Integer, Training> trainingMap ;

        try {
            trainingMap = trainingDao.findAllInLimit(currentPage,recordPage, userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return trainingMap;
    }
    public Integer findNumberOfRows (int userId) throws LogicException {
        //todo
        try {
             return trainingDao.findNumberOfRows(userId);
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
    public  boolean updateTraining(int trainingId,String personality, String periodicity, String trainingType, Map map) throws LogicException {

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

        boolean isValidDate = TrainingValidator.INSTANCE.isValidDate(training.getDate());

        if(isValidDate){
            map.put(ServiceName.DATE,training.getDate());
        }else{
            map.put(ServiceName.DATE,ServiceName.WRONG_FIELD);

        }
        boolean isValidTrainingType = TrainingValidator.INSTANCE.isValidTrainingType(training.getTrainingType());

        if(isValidTrainingType){
            map.put(ServiceName.TRAINING_TYPE,training.getTrainingType());
        }else{
            map.put(ServiceName.TRAINING_TYPE,ServiceName.WRONG_FIELD);
        }

        boolean isValidPersonality = TrainingValidator.INSTANCE.isValidPersonality(training.getPersonality());

        if(isValidPersonality){
            map.put(ServiceName.PERSONALITY,training.getPersonality());
        }else{
            map.put(ServiceName.PERSONALITY,ServiceName.WRONG_FIELD);
        }

        return isValidDate && isValidTrainingType && isValidPersonality;

    }
}
