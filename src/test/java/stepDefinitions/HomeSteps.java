package stepDefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import pages.HomePage;
import utils.DriverManager;
import java.util.List;

public class HomeSteps {

    @Then("The home pages should be displayed")
    public void verifyHomePageIsDisplayed(){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        boolean isHomePageDisplayed = homePage.homeTitleIsDisplayed();
        Assertions.assertTrue(isHomePageDisplayed);
    }

    @Then("The product {string} should be displayed")
    public void verifyProductIsDisplayed(String productName){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        List<String> actualProducts = homePage.getProductNames();
        Assertions.assertTrue(actualProducts.contains(productName));
    }

    @And("I add the product {string} to the cart")
    public void addProductToCart(String product){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.addProductToCart(product);
    }

    @And("I verify that the cart icon displays {string}")
    public void verifyCartIconAmount(String amount){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        Assertions.assertEquals(amount, homePage.getShoppingCartIconText());
    }

    @When("I remove the product {string} from the cart")
    public void removeProductFromCart(String product){
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.removeProductToCart(product);
    }

    @And("I click on the cart icon")
    public void clickOnCartIcon() throws InterruptedException {
        HomePage homePage = new HomePage(DriverManager.getDriver().driver);
        homePage.clickOnCartIcon();
        Thread.sleep(1000);
    }
}
