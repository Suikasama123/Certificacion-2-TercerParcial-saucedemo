package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import pages.CheckoutYourInformationPage;
import utils.DriverManager;
import java.util.List;
import org.openqa.selenium.By;

public class CheckoutYourInformationSteps {
    @And("I fill the checkout information with")
    public void fillCheckoutInformationForm(DataTable checkoutYourInformation) throws InterruptedException {
        for (int i = 0; i < 8; i++) {
            if (!DriverManager.getDriver().driver.findElements(By.id("first-name")).isEmpty()) break;
            Thread.sleep(400);
        }
        List<String> data = checkoutYourInformation.transpose().asList(String.class);
        String firstName = data.get(0);
        String lastName = data.get(1);
        String zip = data.get(2);
        System.out.println("Filling checkout info: " + firstName + " " + lastName + " " + zip + " url: " + DriverManager.getDriver().driver.getCurrentUrl());
        for (int attempt = 0; attempt < 3; attempt++) {
            try {
                org.openqa.selenium.WebElement fn = DriverManager.getDriver().driver.findElement(By.id("first-name"));
                org.openqa.selenium.WebElement ln = DriverManager.getDriver().driver.findElement(By.id("last-name"));
                org.openqa.selenium.WebElement pc = DriverManager.getDriver().driver.findElement(By.id("postal-code"));
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].scrollIntoView(true);", fn);
                Thread.sleep(200);
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles:true})); arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", fn, firstName);
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles:true})); arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", ln, lastName);
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].value=arguments[1]; arguments[0].dispatchEvent(new Event('input', {bubbles:true})); arguments[0].dispatchEvent(new Event('change', {bubbles:true}));", pc, zip);
                Thread.sleep(400);
                String check = DriverManager.getDriver().driver.findElement(By.id("first-name")).getAttribute("value");
                System.out.println("After fill attempt " + attempt + " firstName val: '" + check + "'");
                if (check.equals(firstName)) break;
            } catch (Exception e) {
                System.out.println("Fill attempt " + attempt + " failed: " + e.getMessage());
                try {
                    CheckoutYourInformationPage p = new CheckoutYourInformationPage(DriverManager.getDriver().driver);
                    p.setFirstNameTextBox(firstName);
                    p.setLastNameTextBox(lastName);
                    p.setZipCodeTextBox(zip);
                    Thread.sleep(400);
                    break;
                } catch (Exception e2) {}
            }
            Thread.sleep(400);
        }
        Thread.sleep(500);
        System.out.println("Final fill, firstName val: '" + DriverManager.getDriver().driver.findElement(By.id("first-name")).getAttribute("value") + "' error: '" + DriverManager.getDriver().driver.findElements(By.cssSelector("h3[data-test='error']")).size() + "'");
    }

    @And("I click on the continue button")
    public void clickOnContinueButton() throws InterruptedException {
        System.out.println("Clicking continue, current url: " + DriverManager.getDriver().driver.getCurrentUrl() + " firstName val: '" + DriverManager.getDriver().driver.findElement(By.id("first-name")).getAttribute("value") + "'");
        for (int i = 0; i < 5; i++) {
            if (!DriverManager.getDriver().driver.findElements(By.id("continue")).isEmpty()) break;
            Thread.sleep(400);
        }
        for (int attempt = 0; attempt < 4; attempt++) {
            try {
                org.openqa.selenium.WebElement btn = DriverManager.getDriver().driver.findElement(By.id("continue"));
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].scrollIntoView(true);", btn);
                Thread.sleep(300);
                ((org.openqa.selenium.JavascriptExecutor) DriverManager.getDriver().driver).executeScript("arguments[0].click();", btn);
                System.out.println("Clicked continue via JS attempt " + attempt);
            } catch (Exception e) {
                System.out.println("Continue click failed attempt " + attempt + ": " + e.getMessage());
                try {
                    CheckoutYourInformationPage checkoutYourInformationPage = new CheckoutYourInformationPage(DriverManager.getDriver().driver);
                    checkoutYourInformationPage.clickOnContinueButton();
                } catch (Exception e2) {}
            }
            Thread.sleep(1500);
            String url = DriverManager.getDriver().driver.getCurrentUrl();
            System.out.println("After continue attempt " + attempt + " url: " + url + " error displayed: " + DriverManager.getDriver().driver.findElements(By.cssSelector("h3[data-test='error']")).size() + " finish present: " + !DriverManager.getDriver().driver.findElements(By.id("finish")).isEmpty());
            if (url.contains("checkout-step-two") || !DriverManager.getDriver().driver.findElements(By.id("finish")).isEmpty()) {
                System.out.println("Successfully navigated to step two");
                break;
            }
            if (!DriverManager.getDriver().driver.findElements(By.cssSelector("h3[data-test='error']")).isEmpty()) {
                System.out.println("Error shown after continue: " + DriverManager.getDriver().driver.findElement(By.cssSelector("h3[data-test='error']")).getText());
                break;
            }
            Thread.sleep(400);
        }
        System.out.println("Final url after continue: " + DriverManager.getDriver().driver.getCurrentUrl());
    }
}
