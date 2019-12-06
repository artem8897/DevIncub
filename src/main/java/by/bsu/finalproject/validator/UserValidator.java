package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum UserValidator {

    INSTANCE;

    private static final String REGULAR_MAIL = "[A-Za-z0-9]{4,20}@[a-z]{3,7}.[a-z]{2,3}";
    private static final String REGULAR_PASSWORD = "[0-9a-zA-Z]{5,12}";
    private static final String REGULAR_USERNAME = "[0-9a-zA-Z_]{5,12}";
    private static final String REGULAR_USER_TYPE = "[A-Za-z]{4,10}";


    public boolean isEmailValid(String email){
        Pattern pat = Pattern.compile(REGULAR_MAIL);
        Matcher matcher = pat.matcher(email);
        return matcher.matches();

    }
    public boolean isPasswordValid(String password){
        Pattern pat = Pattern.compile(REGULAR_PASSWORD);
        Matcher matcher = pat.matcher(password);
        return matcher.matches();

    }
    public boolean isUsernameValid(String username){
        Pattern pat = Pattern.compile(REGULAR_USERNAME);
        Matcher matcher = pat.matcher(username);
        return matcher.matches();
    }

    public boolean isValidUserType(String userType){
        Pattern pat = Pattern.compile(REGULAR_USER_TYPE);
        Matcher matcher = pat.matcher(userType);
        return matcher.matches();
    }

}
