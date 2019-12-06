package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum DietValidator {

    INSTANCE;

    private static final String REGULAR_DIET_TYPE = "[A-Z a-z]{6,20}";

    public boolean isValidParameter(int parameter){

        return parameter > 0 && parameter < 3000 ? true : false;

    }
    public boolean isValidDietType(String name){
        Pattern pat = Pattern.compile(REGULAR_DIET_TYPE);
        Matcher matcher = pat.matcher(name);
        return matcher.matches();

    }
}
