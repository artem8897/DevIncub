package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TrainingValidator {

    INSTANCE;

    private static final String REGULAR_DATE = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private static final String REGULAR_PERSONALITY = "(([А-Яа-я ]{2,100})|([A-Za-z ]{2,100}))";
    private static final String REGULAR_TRAINING_TYPE = "(([А-Яа-я ]{2,40})|([A-Za-z ]{2,40}))";


    public boolean isValidDate(String date){
        Pattern pat = Pattern.compile(REGULAR_DATE);
        Matcher matcher = pat.matcher(date);
        return matcher.matches();

    }
    public boolean isValidPersonality(String personality){
        Pattern pat = Pattern.compile(REGULAR_PERSONALITY);
        Matcher matcher = pat.matcher(personality);
        return matcher.matches();

    }
    public boolean isValidTrainingType(String trainingType){
        Pattern pat = Pattern.compile(REGULAR_TRAINING_TYPE);
        Matcher matcher = pat.matcher(trainingType);
        return matcher.matches();

    }

}
