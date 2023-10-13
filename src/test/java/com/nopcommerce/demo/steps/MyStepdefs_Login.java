package com.nopcommerce.demo.steps;
/* 
 Created by Kalpesh Patel
 */

import com.nopcommerce.demo.pages.HomePage;
import com.nopcommerce.demo.pages.LoginPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

public class MyStepdefs_Login {
    @Given("I am on home page of given URL")
    public void iAmOnHomePageOfGivenURL() {


    }

    @When("I click on Login URL")
    public void iClickOnLoginURL() {
        new HomePage().clickOnLoginButton();
    }

    @Then("I verify if I navigates to Login Page")
    public void iVerifyIfINavigatesToLoginPage() {
        Assertions.assertEquals("Welcome, Please Sign In!",new LoginPage().getHeaderOfLoginPage());
    }
}
