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

public class LoginPage extends Utility {
    private static final Logger LOGGER = LogManager.getLogger(LoginPage.class.getName());

    public LoginPage() {
        PageFactory.initElements(ManageDriver.driver, this);
    }

    @CacheLookup
    @FindBy(xpath = "/html/body/div[6]/div[3]/div/div/div/div[1]/h1")
    WebElement LoginPageHeader;

    public String getHeaderOfLoginPage(){
        LOGGER.info("Fetching a page Header for assertion = "+LoginPageHeader);
        return getTextFromElement(LoginPageHeader);
    }


}
