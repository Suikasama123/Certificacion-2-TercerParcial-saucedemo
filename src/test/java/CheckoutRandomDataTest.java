import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.UUID;

public class CheckoutRandomDataTest extends BaseTest {

    @Test
    public void checkoutWithRandomUnlimitedDataAndNoPostalValidation() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.openCart();

        CartPage cart = new CartPage(driver);
        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        Assertions.assertTrue(checkout.isOnStepOne());

        String longFirstName = "A".repeat(200) + UUID.randomUUID().toString();
        String longLastName = "B".repeat(200) + "_Test";
        String invalidPostal = "XYZ-!@#_ABC_" + "9".repeat(50);

        checkout.fillCheckoutForm(longFirstName, longLastName, invalidPostal);

        Assertions.assertTrue(checkout.getFirstNameValue().length() > 100, "Debe aceptar nombre largo sin limite");
        Assertions.assertTrue(checkout.getFirstNameValue().startsWith("AAAA"), "Debe conservar inicio del nombre largo");
        Assertions.assertTrue(checkout.getPostalCodeValue().contains("XYZ"), "Debe aceptar codigo postal alfanumerico sin validacion");
        Assertions.assertTrue(checkout.getPostalCodeValue().length() > 20);

        checkout.clickContinue();

        String errText = "";
        try { errText = checkout.getErrorText(); } catch(Exception e) { errText = ""; }
        Assertions.assertFalse(checkout.isErrorDisplayed(), "No debe mostrar error con datos random sin validacion");
        Assertions.assertTrue(errText.equals("") || errText.contains("Epic"));
        Assertions.assertTrue(checkout.isOnStepTwo(), "Debe avanzar a checkout-step-two aun con datos invalidos");
        Assertions.assertEquals("Checkout: Overview", checkout.getTitleText());

        Assertions.assertTrue(driver.findElement(By.cssSelector(".summary_total_label")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.id("finish")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//div[@class='inventory_item_name' and text()='Sauce Labs Backpack']")).isDisplayed());
    }
}
