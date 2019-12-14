package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PersonalInformationValidator {

    INSTANCE;

    private static final String REGULAR_NAME = "(([А-Яа-я]{2,14})|([A-Za-z]{2,20}))";
    private static final String REGULAR_SEX = "[A-Za-z]{4,10}";

    public boolean isValidName(String name){
        Pattern pat = Pattern.compile(REGULAR_NAME);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
    public boolean isValidHeight(int height){
        return height > 110 && height < 250;
    }
    public boolean isValidWeight(int weight){
        return weight > 40 && weight < 160;
    }

    public boolean isValidSex(String sex){
        Pattern pat = Pattern.compile(REGULAR_SEX);
        Matcher matcher = pat.matcher(sex);
        return matcher.matches();
    }
}
