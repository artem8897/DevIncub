package by.bsu.finalproject.service.impl;

import by.bsu.finalproject.dao.DaoFactory;
import by.bsu.finalproject.dao.impl.DietDaoImpl;
import by.bsu.finalproject.entity.Diet;
import by.bsu.finalproject.service.DietService;
import by.bsu.finalproject.exception.DaoException;
import by.bsu.finalproject.exception.LogicException;
import by.bsu.finalproject.service.ServiceName;
import by.bsu.finalproject.validator.DietValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;
import java.util.Map;

public class DietServiceImpl implements DietService {

    private static final Logger logger = LogManager.getLogger(DietServiceImpl.class);
    private DietDaoImpl dietDao = DaoFactory.INSTANCE.getDietDao();

    public boolean addInformation(int userId, String dietType,int carbohydrates,int fats,int proteins, Map map) throws LogicException {

        Diet diet = new Diet();
        diet.setDietType(dietType);
        diet.setFats(fats);
        diet.setCarbohydrates(carbohydrates);
        diet.setProteins(proteins);

        boolean isCorrectDiet = validateDiet(diet, map);

        if(isCorrectDiet){
            try {
                return dietDao.create(diet, userId);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        } else {
            logger.info("not valid diet");
            return false;
        }
    }
    public Map<String, String> findUsersDiet(int userId) throws LogicException {

        Diet diet;
        Map<String, String> attributeMap = new HashMap<>();

        try {
            diet = dietDao.findUsersDiet(userId);
            attributeMap.put(ServiceName.DIET_TYPE, diet.getDietType());
            attributeMap.put(ServiceName.CARBOHYDRATES, String.valueOf(diet.getCarbohydrates()));
            attributeMap.put(ServiceName.FATS, String.valueOf(diet.getFats()));
            attributeMap.put(ServiceName.PROTEINS, String.valueOf(diet.getProteins()));

        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return attributeMap;
    }
    public boolean isDietExist(int userId) throws LogicException {

        boolean isExist ;

        try {
            isExist = dietDao.isUsersDietExist(userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
        return isExist;
    }

    public  boolean updateDiet(int userId, String dietType,int carbohydrates,int fats,int proteins, Map map) throws LogicException {

        Diet diet = new Diet();
        diet.setDietType(dietType);
        diet.setProteins(proteins);
        diet.setCarbohydrates(carbohydrates);
        diet.setFats(fats);

        boolean isCorrectDiet = validateDiet(diet, map);

        if(isCorrectDiet){
            try {
                return dietDao.update(userId,diet);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            logger.info("not valid diet");
            return false;
        }
    }

    private boolean validateDiet(Diet diet, Map<String, String> map){

        boolean isValidProteins = DietValidator.INSTANCE.isValidParameter(diet.getProteins());

        if(isValidProteins){
            map.put(ServiceName.PROTEINS,String.valueOf(diet.getProteins()));
        }else{
            map.put(ServiceName.PROTEINS,ServiceName.WRONG_FIELD);
        }

        boolean isValidFats = DietValidator.INSTANCE.isValidParameter(diet.getFats());

        if(isValidFats){
            map.put(ServiceName.FATS,String.valueOf(diet.getFats()));
        }else{
            map.put(ServiceName.FATS,ServiceName.WRONG_FIELD);
        }

        boolean isValidCarbohydrates = DietValidator.INSTANCE.isValidParameter(diet.getCarbohydrates());

        if(isValidCarbohydrates){
            map.put(ServiceName.CARBOHYDRATES,String.valueOf(diet.getCarbohydrates()));
        }else{
            map.put(ServiceName.CARBOHYDRATES,ServiceName.WRONG_FIELD);
        }

        boolean isDIetTypeValid = diet.getDietType() != null && DietValidator.INSTANCE.isValidDietType(diet.getDietType());

        if(isDIetTypeValid){
            map.put(ServiceName.DIET_TYPE,diet.getDietType());
        }else{
            map.put(ServiceName.DIET_TYPE,ServiceName.WRONG_FIELD);
        }

       return isDIetTypeValid && isValidCarbohydrates && isValidFats && isValidProteins ;

    }
}
