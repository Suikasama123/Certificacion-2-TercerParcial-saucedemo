package stepDefinitions;

import io.cucumber.java.en.Then;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import pages.CheckoutCompletePage;
import utils.DriverManager;

public class CheckoutCompleteSteps {
    @Then("A message that says {string} should be displayed")
    public void verifyCheckoutCompleteMessage(String expectedMessage) throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            try {
                if (!DriverManager.getDriver().driver.findElements(By.className("complete-header")).isEmpty()) {
                    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver().driver);
                    String actual = checkoutCompletePage.getCheckoutCompleteMessage();
                    System.out.println("Complete message try " + i + ": '" + actual + "' expected: '" + expectedMessage + "' url: " + DriverManager.getDriver().driver.getCurrentUrl());
                    if (actual.equals(expectedMessage)) {
                        Assertions.assertEquals(expectedMessage, actual);
                        return;
                    }
                }
                if (DriverManager.getDriver().driver.getCurrentUrl().contains("checkout-complete")) {
                    CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver().driver);
                    String actual = checkoutCompletePage.getCheckoutCompleteMessage();
                    if (!actual.isEmpty()) {
                        System.out.println("Found complete message on complete page: '" + actual + "'");
                        Assertions.assertEquals(expectedMessage, actual);
                        return;
                    }
                }
                System.out.println("Waiting for complete-header, try " + i + " url: " + DriverManager.getDriver().driver.getCurrentUrl());
            } catch (Exception e) {
                System.out.println("Complete check failed try " + i + ": " + e.getMessage());
            }
            Thread.sleep(800);
        }
        // Final try with direct find
        try {
            String actual = DriverManager.getDriver().driver.findElement(By.className("complete-header")).getText();
            System.out.println("Final direct complete message: '" + actual + "'");
            Assertions.assertEquals(expectedMessage, actual);
            return;
        } catch (Exception e) {
            System.out.println("Final direct find failed: " + e.getMessage());
        }
        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(DriverManager.getDriver().driver);
        String actual = checkoutCompletePage.getCheckoutCompleteMessage();
        System.out.println("Final complete message via page: '" + actual + "' url: " + DriverManager.getDriver().driver.getCurrentUrl());
        Assertions.assertEquals(expectedMessage, actual);
    }
}
