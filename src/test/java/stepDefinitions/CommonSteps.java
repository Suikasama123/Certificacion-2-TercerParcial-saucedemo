package stepDefinitions;

import io.cucumber.java.en.Given;
import utils.DriverManager;

public class CommonSteps {

    @Given("I am in sauce demo web page")
    public void goToSauceDemoPage(){
        DriverManager.getDriver().driver.get("https://www.saucedemo.com");
        DriverManager.getDriver().driver.manage().window().maximize();
    }
}
