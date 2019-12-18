package by.bsu.finalproject.security;

/**
 * XSS protection class
 * @author A. Kuzmik
 */

public class XssSecurity {

        private static final String LEFT_TAG = "<";
        private static final String RIGHT_TAG = ">";
        private static final String REPLACING_LEFT_TAG = "(lt)";
        private static final String REPLACING_RIGHT_TAG = "(rt)";

        public static String protectFromXssAttack(String string) {
            return string == null ? null : string.replaceAll(LEFT_TAG, REPLACING_LEFT_TAG).replaceAll(RIGHT_TAG, REPLACING_RIGHT_TAG);
    }
}
