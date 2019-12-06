package by.bsu.finalproject.manager;

import java.util.ResourceBundle;

public class LocaleManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("local");
    private LocaleManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }

}
