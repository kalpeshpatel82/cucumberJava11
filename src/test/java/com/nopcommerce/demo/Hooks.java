package com.nopcommerce.demo;
/* 
 Created by Kalpesh Patel
 */

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import com.nopcommerce.demo.driverfactory.ManageDriver;
import com.nopcommerce.demo.propertyReader.PropertyReader;
import com.nopcommerce.demo.utility.Utility;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Hooks {
    private static final Logger LOGGER = LogManager.getLogger(Hooks.class.getName());

    @Before
    public void setUp(Scenario scenario) {
        LOGGER.info("Setup before Scenario: " + scenario.getName());
        String node = PropertyReader.getInstance().getConfigurationValue("node");
        String environment = PropertyReader.getInstance().getConfigurationValue("environment");
        String browser = PropertyReader.getInstance().getConfigurationValue("browser");
        String language = PropertyReader.getInstance().getConfigurationValue("language");
        String implicitlyWait = PropertyReader.getInstance().getConfigurationValue("implicitlyWait");
        String os = PropertyReader.getInstance().getConfigurationValue("os");

        ManageDriver.setupBrowser(environment, browser, language, Long.parseLong(implicitlyWait));
    }

    @After
    public void tearDown(Scenario scenario) {
        LOGGER.info("Teardown after Scenario: " + scenario.getName());
        if (scenario.isFailed()) {
            ExtentCucumberAdapter.getCurrentStep().log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromBase64String(Utility.getBase64Screenshot(ManageDriver.driver)).build());
        }
        ManageDriver.closeBrowser();
    }
}


