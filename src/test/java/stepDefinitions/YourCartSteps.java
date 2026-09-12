package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import pages.YourCartPage;
import utils.DriverManager;
import java.util.List;

public class YourCartSteps {
    @When("I click on the checkout button")
    public void clickOnCheckoutButton() throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            YourCartPage yourCartPage = new YourCartPage(DriverManager.getDriver().driver);
            if (DriverManager.getDriver().driver.findElements(By.id("checkout")).isEmpty() && DriverManager.getDriver().driver.findElements(By.cssSelector("[data-test='checkout']")).isEmpty()) {
                Thread.sleep(500);
                continue;
            }
            yourCartPage.clickOnCheckoutButton();
            Thread.sleep(1000);
            if (DriverManager.getDriver().driver.getCurrentUrl().contains("checkout-step-one")) break;
            // try JS
            try {
                List<org.openqa.selenium.WebElement> btns = DriverManager.getDriver().driver.findElements(By.id("checkout"));
                if (btns.isEmpty()) btns = DriverManager.getDriver().driver.findElements(By.cssSelector("[data-test='checkout']"));
                if (!btns.isEmpty()) {
                    ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click();", btns.get(0));
                    Thread.sleep(800);
                    if (DriverManager.getDriver().driver.getCurrentUrl().contains("checkout-step-one")) break;
                }
            } catch (Exception e) {}
            Thread.sleep(500);
        }
        System.out.println("After checkout click url: " + DriverManager.getDriver().driver.getCurrentUrl());
    }
}
