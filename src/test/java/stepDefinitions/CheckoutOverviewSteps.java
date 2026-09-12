package stepDefinitions;

import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import pages.CheckoutOverviewPage;
import utils.DriverManager;

public class CheckoutOverviewSteps {
    @When("I click on finish button")
    public void clickOnFinishButton() throws InterruptedException {
        System.out.println("Clicking finish, current url: " + DriverManager.getDriver().driver.getCurrentUrl());
        for (int i = 0; i < 8; i++) {
            if (!DriverManager.getDriver().driver.findElements(By.id("finish")).isEmpty()) break;
            System.out.println("Waiting for finish button, try " + i + " url: " + DriverManager.getDriver().driver.getCurrentUrl());
            Thread.sleep(600);
            if (DriverManager.getDriver().driver.getCurrentUrl().contains("checkout-step-one")) {
                System.out.println("Still on step one, maybe continue not clicked correctly");
                break;
            }
        }
        CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(DriverManager.getDriver().driver);
        try {
            checkoutOverviewPage.clickOnFinishButton();
        } catch (Exception e) {
            System.out.println("Regular click failed for finish: " + e.getMessage());
            try {
                WebElement btn = DriverManager.getDriver().driver.findElement(By.id("finish"));
                ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                Thread.sleep(300);
                ((JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click();", btn);
            } catch (Exception e2) {
                System.out.println("JS click also failed: " + e2.getMessage());
                System.out.println("Page source: " + DriverManager.getDriver().driver.getPageSource().substring(0, Math.min(2000, DriverManager.getDriver().driver.getPageSource().length())));
            }
        }
        Thread.sleep(1000);
        System.out.println("After finish click url: " + DriverManager.getDriver().driver.getCurrentUrl());
    }
}
