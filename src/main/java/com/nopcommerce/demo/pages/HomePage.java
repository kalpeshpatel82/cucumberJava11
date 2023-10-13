package com.nopcommerce.demo.pages;
/* 
 Created by Kalpesh Patel
 */

import com.nopcommerce.demo.driverfactory.ManageDriver;
import com.nopcommerce.demo.utility.Utility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends Utility {
    private static final Logger LOGGER = LogManager.getLogger(HomePage.class.getName());

    public HomePage() {
        PageFactory.initElements(ManageDriver.driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "/html/body/div[6]/div[1]/div[1]/div[2]/div[1]/ul/li[2]/a")
    WebElement loginButton;

    public void clickOnLoginButton(){
        clickOnElement(loginButton);
        LOGGER.info("Clicking on Login Button = "+loginButton);
    }
}
