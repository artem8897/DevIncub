package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.TrainerDaoImpl;
import by.bsu.finalproject.dao.impl.TrainingDaoImpl;
import by.bsu.finalproject.entity.Trainer;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.service.TrainerService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.validator.TrainerInformationValidator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TrainerServiceImpl implements TrainerService {

    private TrainerDaoImpl trainerDao = DaoFactory.INSTANCE.getTrainerDao();

    public Map<Integer, Trainer> findAllTrainerMap() throws LogicException {

        Map<Integer, Trainer> trainerMap ;

        try {
            trainerMap = trainerDao.findAll();
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


    public List<Trainer> findLimitTrainerMap(int currentPage,int recordPage ) throws LogicException {

        List<Trainer> trainerList ;

        try {
            trainerList = trainerDao.findAllInLimit(currentPage,recordPage);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return trainerList;
    }
    public Integer findNumberOfRows () throws LogicException {

        int number = 0 ;

        try {
            number = trainerDao.findNumberOfRows();
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return number;
    }

    public boolean createTrainer(int userId, String name,int experience, String trainingType, Map map) throws LogicException {

        Trainer trainer = new Trainer();
        trainer.setTrainingType(trainingType);
        trainer.setName(name);
        trainer.setWorkExperience(experience);

        boolean isTrainerInformationValid = validateTrainerInformation(trainer, map);

        if(isTrainerInformationValid){

            try {
                return trainerDao.create(userId,trainer);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            return false;
        // toDO
        }
    }
    public  boolean updateTrainerInformation(int trainerId,String name, int workExperience, String trainingType, Map map) throws LogicException {

        Trainer trainer = new Trainer();
        trainer.setId(trainerId);
        trainer.setName(name);
        trainer.setTrainingType(trainingType);
        trainer.setWorkExperience(workExperience);

        boolean isTrainerInformationValid = validateTrainerInformation(trainer, map);

        if(isTrainerInformationValid){
            TrainerDaoImpl trainerDao = new TrainerDaoImpl();
            try {
                return trainerDao.update(trainer);
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

            map.put(ServiceName.NAME,trainer.getName());
            map.put(ServiceName.WORK_EXPERIENCE,String.valueOf(trainer.getWorkExperience()));
            map.put(ServiceName.TRAINING_TYPE,trainer.getTrainingType());

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return map;
    }

    private boolean validateTrainerInformation(Trainer trainer, Map<String, String> map){

        boolean isNameValid = TrainerInformationValidator.INSTANCE.isValidName(trainer.getName());

        if(isNameValid){
            map.put(ServiceName.NAME,trainer.getName());
        }else{
            map.put(ServiceName.NAME,ServiceName.WRONG_FIELD);
        }

        boolean isExperienceValid = TrainerInformationValidator.INSTANCE.isWorkExperienceValid(trainer.getWorkExperience());

        if(isExperienceValid){
            map.put(ServiceName.EXPERIENCE,String.valueOf(trainer.getWorkExperience()));
        }else{
            map.put(ServiceName.EXPERIENCE,ServiceName.WRONG_FIELD);
        }

        boolean isTrainingTypeValid = TrainerInformationValidator.INSTANCE.isValidDietType(trainer.getTrainingType());

        if(isTrainingTypeValid){
            map.put(ServiceName.TRAINING_TYPE,trainer.getTrainingType());
        }else{
            map.put(ServiceName.TRAINING_TYPE,ServiceName.WRONG_FIELD);
        }

        return isNameValid && isExperienceValid && isTrainingTypeValid;
    }

}
