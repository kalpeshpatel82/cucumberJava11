package com.nopcommerce.demo.driverfactory;
/* 
 Created by Kalpesh Patel
 */

import com.nopcommerce.demo.propertyReader.PropertyReader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class ManageDriver {
    private static final Logger LOGGER = LogManager.getLogger(ManageDriver.class.getName());
    public static WebDriver driver;
    public static String baseUrl;

    public ManageDriver() {
        PageFactory.initElements(driver, this);
        
    }

    public static void setupBrowser(String environment, String browser, String language, Long implicitlyWait) {
        LOGGER.info("Setting up Selenium driver for Local Environment : ");
        baseUrl = PropertyReader.getInstance().getEnvironmentProperty(environment,"baseUrl");

        if (browser.equalsIgnoreCase("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("----incognito");
            options.addArguments("--lang="+language);
            driver = new ChromeDriver(options);
        } else if (browser.equalsIgnoreCase("firefox")) {
            FirefoxOptions options = new FirefoxOptions();
            options.addArguments("--disable-notifications");
            options.addArguments("-private");
            //options.addArguments("--lang="+language);
            driver = new FirefoxDriver(options);
        } else if (browser.equalsIgnoreCase("edge")) {
            driver = new EdgeDriver();
        } else if (browser.equalsIgnoreCase("safari")) {
            driver = new SafariDriver();
        } else {
            LOGGER.error("Browser not Supported " + browser);
            LOGGER.error("Please use one of the following : Chrome | Firefox | Edge | Safari");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(implicitlyWait));
        driver.get(baseUrl);
    }

    public static void closeBrowser() {
        if (driver != null) {
            LOGGER.info("Closing Browser");
            driver.quit();
        }
    }

}
