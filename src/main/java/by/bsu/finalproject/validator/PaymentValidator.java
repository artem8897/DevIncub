package by.bsu.finalproject.validator;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Validator diet
 * @author A. Kzumik
 */

public enum PaymentValidator {

    INSTANCE;

    private static final String REGULAR_NUMBER = "\\d{1,9}";
    private static final String REGULAR_PAGE = "\\d{1,2}";
    private static final String REGULAR_DATE = "[0-9]{4}-[0-9]{2}-[0-9]{2}";
    private static final String REGULAR_CARD = "\\d{9}";


    /**
     * Validate date
     * @param date
     * @return true if the experience is valid
     */

    public boolean isValidDate(String date){
        Pattern pat = Pattern.compile(REGULAR_DATE);
        Matcher matcher = pat.matcher(date);
        return matcher.matches();

    }
    /**
     * Validate card
     * @param card
     * @return true if the card is valid
     */
    public boolean isValidCard(String card){
        Pattern pat = Pattern.compile(REGULAR_CARD);
        Matcher matcher = pat.matcher(card);
        return matcher.matches();

    }
    /**
     * Validate discount amount
     * @param discount
     * @return true if the experience is valid
     */

    public boolean isValidDiscount(String discount){
        Pattern pat = Pattern.compile(REGULAR_NUMBER);
        Matcher matcher = pat.matcher(discount);
        return matcher.matches();
    }

    /**
     * Validate parameters for page
     * @param pageNumber
     * @return true if the page is valid
     */

    private boolean isPageValid(String pageNumber){
        Pattern pat = Pattern.compile(REGULAR_PAGE);
        Matcher matcher = pat.matcher(pageNumber);
        return matcher.matches();
    }

}
