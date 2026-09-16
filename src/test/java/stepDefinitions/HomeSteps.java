package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import utils.DriverManager;

public class HomeSteps {

    @Then("The home pages should be displayed")
    public void verifyHomePageIsDisplayed(){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        boolean isHomePageDisplayed = homePage.homeTitleIsDisplayed();
        Assertions.assertTrue(isHomePageDisplayed);
    }

    @And("I click on the cart icon")
    public void clickOnCartIcon() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnCartIcon();
        Thread.sleep(1000);
    }
}
