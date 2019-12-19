package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator user information
 * @author A. Kzumik
 */


public enum UserValidator {

    INSTANCE;

    private static final String REGULAR_MAIL = "[A-Za-z0-9]{4,20}@[a-z]{3,7}.[a-z]{2,3}";
    private static final String REGULAR_PASSWORD = "[0-9a-zA-Z]{5,12}";
    private static final String REGULAR_USERNAME = "[0-9a-zA-Z_]{5,12}";
    private static final String REGULAR_USER_TYPE = "[A-Za-z]{1,2}[A-Za-z0-9]{6,8}";

    /**
     * Validate email
     * @param email
     * @return true if the email is valid
     */

    public boolean isEmailValid(String email){
        Pattern pat = Pattern.compile(REGULAR_MAIL);
        Matcher matcher = pat.matcher(email);
        return matcher.matches();

    }

    /**
     * Validate password
     * @param password
     * @return true if the password is valid
     */

    public boolean isPasswordValid(String password){
        Pattern pat = Pattern.compile(REGULAR_PASSWORD);
        Matcher matcher = pat.matcher(password);
        return matcher.matches();

    }

    /**
     * Validate username
     * @param username
     * @return true if the username is valid
     */

    public boolean isUsernameValid(String username){
        Pattern pat = Pattern.compile(REGULAR_USERNAME);
        Matcher matcher = pat.matcher(username);
        return matcher.matches();
    }

    /**
     * Validate userType
     * @param userType
     * @return true if the userType is valid
     */

    public boolean isValidUserType(String userType){
        Pattern pat = Pattern.compile(REGULAR_USER_TYPE);
        Matcher matcher = pat.matcher(userType);
        return matcher.matches();
    }

}
