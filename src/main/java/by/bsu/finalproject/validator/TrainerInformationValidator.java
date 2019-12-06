package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum TrainerInformationValidator {

    INSTANCE;

    private static final String REGULAR_TRAINING_TYPE = "[A-Z a-z]{5,20}";
    private static final String REGULAR_NAME = "[A-Z][a-z]{2,20}";

    public boolean isWorkExperienceValid(int workExperience){

        return workExperience >= 0 && workExperience < 40 ? true : false;

    }
    public boolean isValidDietType(String name){
        Pattern pat = Pattern.compile(REGULAR_TRAINING_TYPE);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
    public boolean isValidName(String name){
        Pattern pat = Pattern.compile(REGULAR_NAME);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
}
