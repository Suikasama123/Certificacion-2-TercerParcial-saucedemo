package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.LoginPage;
import utils.DriverManager;

public class LoginSteps {
    @Given("I set the user name text box with {string}")
    public void setUserName(String userName){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setUserNameTextBox(userName);
    }

    @And("I set the password text box with {string}")
    public void setPassword(String password){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.setPasswordTextBox(password);
    }

    @When("I click on the login button")
    public void clickOnLoginButton(){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        loginPage.clickOnLoginButton();
    }

    @Then("A error message that says {string} should be displayed")
    public void verifyErrorMessageIsDisplayed(String message){
        LoginPage loginPage = new LoginPage(DriverManager.getDriver().driver);
        Assertions.assertTrue(loginPage.isErrorMessageDisplayed());
        Assertions.assertEquals(message, loginPage.getErrorMessage());
    }
}
