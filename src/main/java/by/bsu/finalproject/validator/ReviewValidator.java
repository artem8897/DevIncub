package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum  ReviewValidator {

    INSTANCE;

    private static final String REGULAR_REVIEW = "([а-яА-яa-zA-Z,.)( ]{5,100})";

    public boolean isValidReview(String review){
        Pattern pat = Pattern.compile(REGULAR_REVIEW);
        Matcher matcher = pat.matcher(review);
        return matcher.matches();

    }
    public boolean isValidRate(int rate){
        return rate >= 0 && rate <= 10 ;
    }

}
