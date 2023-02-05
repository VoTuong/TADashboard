package com.tadashboard.helpers;


import com.tadashboard.common.Constants;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertiesHelpers {

    private static Properties properties;
    private static String linkFile;
    private static FileInputStream file;

    public static void loadAllFiles() {
        LinkedList<String> files = new LinkedList<>();

        files.add("src/test/resources/properties/configs.properties");

        try {
            properties = new Properties();

            for (String f : files) {
                Properties tempProp = new Properties();
                linkFile = Helpers.getCurrentDir() + f;
                file = new FileInputStream(linkFile);
                tempProp.load(file);
                properties.putAll(tempProp);
            }
        } catch (IOException ioe) {
            new Properties();
        }
    }

    public static String getValue(String key) {
        String keyValue = null;
        try {
            if (file == null) {
                properties = new Properties();
                linkFile = Helpers.getCurrentDir() + Constants.PROPERTIES_FILE_PATH_ROOT;
                file = new FileInputStream(linkFile);
                properties.load(file);
                file.close();
            }
            // Get value from file
            keyValue = properties.getProperty(key);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return keyValue;
    }

}
