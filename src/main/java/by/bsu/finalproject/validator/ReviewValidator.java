package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator student review
 * @author A. Kzumik
 */

public enum  ReviewValidator {

    INSTANCE;

    private static final String REGULAR_REVIEW = "([а-яА-яa-zA-Z,.)( ]{5,100})";
    private static final String REGULAR_NUMBER = "\\d{1,9}";

    /**
     * Validate review
     * @param review
     * @return true if the string review is valid
     */

    public boolean isValidReview(String review){
        Pattern pat = Pattern.compile(REGULAR_REVIEW);
        Matcher matcher = pat.matcher(review);
        return matcher.matches();

    }

    /**
     * Validate students rate
     * @param rateString
     * @return true if the rate is valid
     */

    public boolean isValidRate(String rateString){

        Pattern pat = Pattern.compile(REGULAR_NUMBER);
        Matcher matcher = pat.matcher(rateString);
        if(matcher.matches()){
            int rate = Integer.parseInt(rateString);
            return rate >= 0 && rate <= 10 ;
        }else{
            return false;
        }
    }

}
