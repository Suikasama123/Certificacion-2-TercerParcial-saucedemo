import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.CartPage;
import pages.CheckoutPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CheckoutWithoutProductsTest extends BaseTest {

    @Test
    public void checkoutWithoutAddingProductsToCart() {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        Assertions.assertTrue(inventory.isOnInventoryPage());
        Assertions.assertTrue(inventory.getInventoryItemsCount() > 0);
        Assertions.assertEquals("", inventory.getCartBadgeText());

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assertions.assertEquals(0, cart.getCartItemsCount());
        Assertions.assertEquals(0, cart.getCartItems().size());
        Assertions.assertTrue(cart.isCheckoutDisplayed());

        cart.clickCheckout();

        CheckoutPage checkout = new CheckoutPage(driver);
        Assertions.assertTrue(checkout.isOnStepOne());
        Assertions.assertEquals("Checkout: Your Information", checkout.getTitleText());

        WebElement firstName = driver.findElement(By.id("first-name"));
        WebElement lastName = driver.findElement(By.id("last-name"));
        WebElement postal = driver.findElement(By.cssSelector("#postal-code"));
        Assertions.assertTrue(firstName.isDisplayed());
        Assertions.assertTrue(lastName.isDisplayed());
        Assertions.assertTrue(postal.isDisplayed());
    }
}
