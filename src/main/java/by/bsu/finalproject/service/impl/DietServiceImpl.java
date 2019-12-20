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

/**
 * Service for Diet.
 * @author A. Kuzmik
 */

public class DietServiceImpl implements DietService {

    private static final Logger logger = LogManager.getLogger(DietServiceImpl.class);

    private DietDaoImpl dietDao = DaoFactory.INSTANCE.getDietDao();

    public boolean addInformation(int userId, String dietType,String carbohydrates,String fats,String proteins,  Map<String, String> map) throws LogicException {

        boolean isCorrectDiet = validateDiet(dietType, carbohydrates, fats, proteins, map);

        if(isCorrectDiet){

            Diet diet = new Diet();
            diet.setDietType(dietType);
            diet.setFats(Integer.parseInt(fats));
            diet.setCarbohydrates(Integer.parseInt(carbohydrates));
            diet.setProteins(Integer.parseInt(proteins));

            try {
                return dietDao.createDiet(diet, userId);
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

        try {
            return dietDao.isUsersDietExist(userId);
        } catch (DaoException e) {
            throw new LogicException(e);
        }
    }

    public  boolean updateDiet(int userId, String dietType,String carbohydrates,String fats,String proteins,  Map<String, String> map) throws LogicException {

        boolean isCorrectDiet = validateDiet(dietType, carbohydrates, fats, proteins, map);

        if(isCorrectDiet){

            Diet diet = new Diet();
            diet.setDietType(dietType);
            diet.setProteins(Integer.parseInt(proteins));
            diet.setCarbohydrates(Integer.parseInt(carbohydrates));
            diet.setFats(Integer.parseInt(fats));
            try {
                return dietDao.updateDiet(userId,diet);
            } catch (DaoException e) {
                throw new LogicException(e);
            }
        }else{
            logger.info("not valid diet");
            return false;
        }
    }

    private boolean validateDiet(String dietType,String carbohydrates,String fats,String proteins, Map<String, String> map){

        boolean isValidProteins =  proteins != null && DietValidator.INSTANCE.isValidParameter(proteins);

        if(isValidProteins){
            map.put(ServiceName.PROTEINS, proteins);
        }else{
            map.put(ServiceName.PROTEINS,ServiceName.WRONG_FIELD);
        }

        boolean isValidFats = fats != null && DietValidator.INSTANCE.isValidParameter(fats);

        if(isValidFats){
            map.put(ServiceName.FATS, fats);
        }else{
            map.put(ServiceName.FATS,ServiceName.WRONG_FIELD);
        }

        boolean isValidCarbohydrates = carbohydrates != null && DietValidator.INSTANCE.isValidParameter(carbohydrates);

        if(isValidCarbohydrates){
            map.put(ServiceName.CARBOHYDRATES, carbohydrates);
        }else{
            map.put(ServiceName.CARBOHYDRATES,ServiceName.WRONG_FIELD);
        }

        boolean isDIetTypeValid = dietType != null && DietValidator.INSTANCE.isValidDietType(dietType);

        if(isDIetTypeValid){
            map.put(ServiceName.DIET_TYPE, dietType);
        }else{
            map.put(ServiceName.DIET_TYPE,ServiceName.WRONG_FIELD);
        }

       return isDIetTypeValid && isValidCarbohydrates && isValidFats && isValidProteins ;

    }
}
