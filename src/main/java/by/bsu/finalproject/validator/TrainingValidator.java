package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator trainings information
 * @author A. Kzumik
 */


public enum TrainingValidator {

    INSTANCE;

    private static final String REGULAR_DATE = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private static final String REGULAR_PERSONALITY = "(([А-Яа-я ]{2,100})|([A-Za-z ]{2,100}))";
    private static final String REGULAR_TRAINING_TYPE = "(([А-Яа-я ]{2,40})|([A-Za-z ]{2,40}))";

    /**
     * Validate date
     * @param date
     * @return true if the experience is valid
     */

    public boolean isValidDate(String date){
        Pattern pat = Pattern.compile(REGULAR_DATE);
        Matcher matcher = pat.matcher(date);
        return matcher.matches();

    }

    /**
     * Validate personality
     * @param personality
     * @return true if the personality is valid
     */

    public boolean isValidPersonality(String personality){
        Pattern pat = Pattern.compile(REGULAR_PERSONALITY);
        Matcher matcher = pat.matcher(personality);
        return matcher.matches();

    }
    /**
     * Validate trainingType
     * @param trainingType
     * @return true if the trainingType is valid
     */
    public boolean isValidTrainingType(String trainingType){
        Pattern pat = Pattern.compile(REGULAR_TRAINING_TYPE);
        Matcher matcher = pat.matcher(trainingType);
        return matcher.matches();

    }

}
