package com.nopcommerce.demo.propertyReader;
/* 
 Created by Kalpesh Patel
 */

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
    /*
    Rules for implementing singleton design pattern
    1. Make the constructor private
    2. Create a static method to get the instance
    3. Create a static member variable
    */

    //Declare the PropertyReader variable
    private static final Logger LOGGER = LogManager.getLogger(PropertyReader.class.getName());
    private static volatile PropertyReader propInstance;

    //Create Private constructor Because of prevent the Instantiation of class
    private PropertyReader() {

    }

    /**
     * This method will return instance of PropertyReader class
     * @return PropertyReader propInstance
     */
    public static synchronized PropertyReader getInstance() {
        if (propInstance == null) {
            LOGGER.debug("Creating new instance of PropertyReader");
            propInstance = new PropertyReader();
        }
        return propInstance;
    }

    /*
     This method will read property from property file
     * @param environment - The target environment
     * @param propertyName - The property from the configuration file to read
     * @return The property value for the given name
     * */

    public String getProperty(String propertyName) {

        Properties prop = new Properties();
        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(System.getProperty("user.dir") + "/src/test/java/resources/propertiesfiles/config.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (Exception e) {
            System.out.println("Property not found");
        }
        return null;
    }

    public String getEnvironmentProperty(String environment, String propertyName) {
        Properties prop = new Properties();
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("configuration/" + environment + ".properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (IOException e) {
            LOGGER.error("An error occurred while trying to read configuration file: ", e);
        }
        return null;
    }

    private String getDefaultProperty(String propertyName) {
        Properties prop = new Properties();
        InputStream inputStream;
        try {
            inputStream = getClass().getClassLoader().getResourceAsStream("configuration/defaults.properties");
            prop.load(inputStream);
            if (prop.getProperty(propertyName) != null) {
                return prop.getProperty(propertyName);
            }
        } catch (IOException e) {
            LOGGER.error("An error occurred while trying to read default configuration file: ", e);
        }
        return null;
    }

    public String getConfigurationValue(String configurationKey) {
        String configurationValue = System.getProperty(configurationKey);
        if (configurationValue == null) {
        }
        configurationValue = getDefaultProperty(configurationKey);
        return configurationValue;
    }
}
