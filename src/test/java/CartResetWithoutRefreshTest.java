import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import pages.CartPage;
import pages.InventoryPage;
import pages.LoginPage;

public class CartResetWithoutRefreshTest extends BaseTest {

    @Test
    public void verifyProductsRemovedAfterResetWithoutRefresh() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.addProductToCart("Sauce Labs Bike Light");

        Assertions.assertEquals("2", inventory.getCartBadgeText());

        inventory.openCart();

        CartPage cart = new CartPage(driver);
        Assertions.assertEquals(2, cart.getCartItemsCount());
        Assertions.assertEquals(2, cart.getCartItems().size());
        Assertions.assertEquals("2", cart.getCartBadgeText());

        cart.resetAppState();
        Thread.sleep(500);

        Assertions.assertEquals("", cart.getCartBadgeText(), "Badge debe vaciarse tras Reset App State sin refresh");

        int itemsWithoutRefresh = cart.getCartItemsCount();
        Assertions.assertEquals(2, cart.getCartItems().size());
        Assertions.assertEquals(2, itemsWithoutRefresh, "Sin refresh los items siguen visibles en DOM (bug conocido)");

        driver.navigate().refresh();
        Thread.sleep(800);

        int itemsAfterRefresh = driver.findElements(By.className("cart_item")).size();
        Assertions.assertEquals(0, itemsAfterRefresh, "Tras refresh el carrito debe quedar vacio");
        Assertions.assertEquals("", cart.getCartBadgeText());
    }
}
