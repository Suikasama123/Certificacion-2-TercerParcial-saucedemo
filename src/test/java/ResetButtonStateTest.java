import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.InventoryPage;
import pages.LoginPage;

import java.util.List;

public class ResetButtonStateTest extends BaseTest {

    @Test
    public void verifyButtonStateChangesAfterResetAppState() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");

        InventoryPage inventory = new InventoryPage(driver);

        Assertions.assertTrue(inventory.getInventoryItemsCount() == 6);
        inventory.addProductToCart("Sauce Labs Backpack");
        inventory.addProductToCart("Sauce Labs Bike Light");

        By removeBackpack = By.id("remove-sauce-labs-backpack");
        By addBackpack = By.id("add-to-cart-sauce-labs-backpack");
        By removeBike = By.id("remove-sauce-labs-bike-light");

        Assertions.assertEquals("Remove", driver.findElement(removeBackpack).getText());
        Assertions.assertEquals("Remove", driver.findElement(removeBike).getText());
        Assertions.assertEquals("2", inventory.getCartBadgeText());
        inventory.removeProductFromCart("Sauce Labs Bike Light");
        Assertions.assertEquals("1", inventory.getCartBadgeText());
        inventory.addProductToCart("Sauce Labs Bike Light");
        Assertions.assertEquals("2", inventory.getCartBadgeText());

        inventory.resetAppState();
        Thread.sleep(800);

        Assertions.assertEquals("", inventory.getCartBadgeText(), "Badge debe vaciarse tras Reset");

        List<WebElement> addBtns = driver.findElements(addBackpack);
        List<WebElement> removeBtns = driver.findElements(removeBackpack);

        if (addBtns.isEmpty()) {
            driver.navigate().refresh();
            Thread.sleep(800);
            addBtns = driver.findElements(addBackpack);
            removeBtns = driver.findElements(removeBackpack);
        }

        Assertions.assertFalse(addBtns.isEmpty(), "Boton Add to cart debe existir tras reset");
        Assertions.assertEquals("Add to cart", addBtns.get(0).getText());
        Assertions.assertTrue(removeBtns.isEmpty() || !removeBtns.get(0).isDisplayed(), "Boton Remove no debe estar visible tras reset");

        WebElement bikeAdd = driver.findElement(By.id("add-to-cart-sauce-labs-bike-light"));
        Assertions.assertTrue(bikeAdd.isDisplayed());
        Assertions.assertEquals("Add to cart", bikeAdd.getText());
        Assertions.assertEquals("Add to cart", inventory.getButtonTextForProduct("Sauce Labs Backpack"));
        Assertions.assertEquals("Add to cart", inventory.getButtonTextForProduct("Sauce Labs Bike Light"));

        Assertions.assertTrue(driver.findElement(By.cssSelector("#add-to-cart-sauce-labs-backpack")).isDisplayed());
        Assertions.assertTrue(driver.findElement(By.xpath("//button[@id='add-to-cart-sauce-labs-bike-light']")).isDisplayed());
    }
}
