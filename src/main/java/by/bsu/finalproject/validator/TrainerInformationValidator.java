package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator trainers information
 * @author A. Kzumik
 */

public enum TrainerInformationValidator {

    INSTANCE;

    private static final String REGULAR_TRAINING_TYPE = "(([А-Яа-я ]{2,40})|([A-Za-z ]{2,40}))";
    private static final String REGULAR_NAME = "(([А-Яа-я]{2,14})|([A-Za-z]{2,20}))";

    /**
     * Validate experience of work
     * @param workExperience
     * @return true if the experience is valid
     */

    public boolean isWorkExperienceValid(int workExperience){

        return workExperience >= 0 && workExperience < 40 ;

    }

    /**
     * Validate dietType
     * @param dietType
     * @return true if the string review is valid
     */

    public boolean isValidDietType(String dietType){

        Pattern pat = Pattern.compile(REGULAR_TRAINING_TYPE);
        Matcher matcher = pat.matcher(dietType);
        return matcher.matches();

    }

    /**
     * Validate name
     * @param name
     * @return true if the string name is valid
     */

    public boolean isValidName(String name){

        Pattern pat = Pattern.compile(REGULAR_NAME);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
}
