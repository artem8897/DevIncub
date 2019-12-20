package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.service.TrainerService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.validator.TrainerInformationValidator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Service for Trainer
 * @author A. Kuzmik
 */

public class TrainerServiceImpl implements TrainerService {

    private TrainerDaoImpl trainerDao = DaoFactory.INSTANCE.getTrainerDao();

    private static final String REGULAR_PAGE_NUMBER = "\\d{1,2}";

    public Map<Integer, Trainer> findAllTrainerMap() throws LogicException {

        Map<Integer, Trainer> trainerMap ;

        try {
            trainerMap = trainerDao.findAllTrainers();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return trainerMap;
    }
    public boolean isExist(int id) throws LogicException {

        try {
            return trainerDao.isCreated(id);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }


    public List<Trainer> findLimitTrainerMap(String currentPageString,String recordPageString ) throws LogicException {

        List<Trainer> trainerList = new ArrayList<>();

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
                    trainerList = trainerDao.findLimitTrainersList(currentPage,recordsPerPage);
                } catch (DaoException e) {
                    throw new LogicException(e);
                }
            }
        }
        return trainerList;
    }

    public Integer findNumberOfRows () throws LogicException {

        try {
             return trainerDao.findNumberOfRows();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public boolean createTrainer(int userId, String name,int experience, String trainingType, Map<String, String> map) throws LogicException {

        Trainer trainer = new Trainer();
        trainer.setId(userId);
        trainer.setTrainingType(trainingType);
        trainer.setTrainerName(name);
        trainer.setWorkExperience(experience);

        boolean isTrainerInformationValid = validateTrainerInformation(trainer, map);

        if(isTrainerInformationValid){

            try {
                return trainerDao.create(trainer);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        // toDO
        }
    }
    public  boolean updateTrainerInformation(int trainerId,String name, int workExperience, String trainingType, Map<String, String> map) throws LogicException {

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);
        trainer.setTrainerName(name);
        trainer.setTrainingType(trainingType);
        trainer.setWorkExperience(workExperience);

        boolean isTrainerInformationValid = validateTrainerInformation(trainer, map);

        if(isTrainerInformationValid){

            try {
                return trainerDao.updateTrainer(trainer);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        }
    }
    public Map<String, String> findTrainerInformation(int userId) throws LogicException {

        Trainer trainer;
        Map<String, String> map = new HashMap<>();

        try {
            trainer = trainerDao.findTrainerInformation(userId);

            map.put(ServiceName.NAME,trainer.getTrainerName());
            map.put(ServiceName.WORK_EXPERIENCE,String.valueOf(trainer.getWorkExperience()));
            map.put(ServiceName.TRAINING_TYPE,trainer.getTrainingType());

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return map;
    }

    private boolean validateTrainerInformation(Trainer trainer, Map<String, String> map){

        boolean isNameValid = trainer.getTrainerName() != null && TrainerInformationValidator.INSTANCE.isValidName(trainer.getTrainerName());

        if(isNameValid){
            map.put(ServiceName.NAME,trainer.getTrainerName());
        }else{
            map.put(ServiceName.NAME,ServiceName.WRONG_FIELD);
        }

        boolean isExperienceValid = TrainerInformationValidator.INSTANCE.isWorkExperienceValid(trainer.getWorkExperience());

        if(isExperienceValid){
            map.put(ServiceName.EXPERIENCE,String.valueOf(trainer.getWorkExperience()));
        }else{
            map.put(ServiceName.EXPERIENCE,ServiceName.WRONG_FIELD);
        }

        boolean isTrainingTypeValid = trainer.getTrainingType() != null && TrainerInformationValidator.INSTANCE.isValidDietType(trainer.getTrainingType());

        if(isTrainingTypeValid){
            map.put(ServiceName.TRAINING_TYPE,trainer.getTrainingType());
        }else{
            map.put(ServiceName.TRAINING_TYPE,ServiceName.WRONG_FIELD);
        }

        return isNameValid && isExperienceValid && isTrainingTypeValid;
    }

}
