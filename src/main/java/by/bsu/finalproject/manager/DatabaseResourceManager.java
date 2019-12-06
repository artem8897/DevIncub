package by.bsu.finalproject.manager;

import java.util.ResourceBundle;

public class DatabaseResourceManager {
    private final static ResourceBundle resourceBundle = ResourceBundle.getBundle("database");
    private DatabaseResourceManager() { }
    public static String getProperty(String key) {
        return resourceBundle.getString(key);
    }
}
