package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PersonalInformationValidator {

    INSTANCE;

    private static final String REGULAR_NAME = "[A-Z][a-z]{5,10}";
    private static final String REGULAR_SEX = "[A-Za-z]{4,10}";

    public boolean isValidName(String name){
        Pattern pat = Pattern.compile(REGULAR_NAME);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
    public boolean isValidHeight(int height){
        return height > 110 && height < 250 ? true : false;
    }
    public boolean isValidWeight(int weight){
        return weight > 40 && weight < 160 ? true : false;
    }

    public boolean isValidSex(String sex){
        Pattern pat = Pattern.compile(REGULAR_SEX);
        Matcher matcher = pat.matcher(sex);
        return matcher.matches();
    }
}
