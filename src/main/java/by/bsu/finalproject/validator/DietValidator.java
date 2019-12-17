package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator diet
 * @author A. Kzumik
 */

public enum DietValidator {

    INSTANCE;

    private static final String REGULAR_DIET_TYPE = "[A-Z a-z]{6,20}";

    /**
     * Validate parameters for diet
     * @param parameter
     * @return true if the parameter is valid
     */

    public boolean isValidParameter(int parameter){

        return parameter > 0 && parameter < 3000 ;

    }

    /**
     * Validate parameters for diet
     * @param dietType
     * @return true if the string parameter is valid
     */

    public boolean isValidDietType(String dietType){
        Pattern pat = Pattern.compile(REGULAR_DIET_TYPE);
        Matcher matcher = pat.matcher(dietType);
        return matcher.matches();

    }
}
