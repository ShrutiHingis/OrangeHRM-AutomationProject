package com.orangehrm.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesReader {

    private static Properties properties;

    public static void loadProperties() throws IOException {
        properties = new Properties();

        FileInputStream file = new FileInputStream(
                "src/test/resources/config.properties"
        );

        properties.load(file);
        file.close();
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
