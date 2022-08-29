package com.example.arrestmanagement.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import java.util.Properties;



public class PropertiesReader {

    private static final String CONFIG_PROPERTIES = "application.properties";

    public static String getProperties(String key) {
        try (InputStream input = PropertiesReader.class.getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            Properties properties = new Properties();
            if (input == null) {
                throw new NoSuchElementException("Sorry, unable to find application.properties");
            }
            properties.load(input);
            return properties.getProperty(key);

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return null;
    }
}