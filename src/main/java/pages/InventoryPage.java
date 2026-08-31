package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class InventoryPage {
    WebDriver driver;

    @FindBy(className = "shopping_cart_link")
    WebElement cartIcon;

    @FindBy(className = "shopping_cart_badge")
    List<WebElement> cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(className = "inventory_item")
    List<WebElement> inventoryItems;

    public InventoryPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void addProductToCart(String productName) {
        String id = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public void removeProductFromCart(String productName) {
        String id = "remove-" + productName.toLowerCase().replace(" ", "-");
        driver.findElement(By.id(id)).click();
    }

    public String getButtonTextForProduct(String productName) {
        String addId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        String removeId = "remove-" + productName.toLowerCase().replace(" ", "-");
        List<WebElement> removeBtn = driver.findElements(By.id(removeId));
        if (!removeBtn.isEmpty() && removeBtn.get(0).isDisplayed()) return removeBtn.get(0).getText();
        List<WebElement> addBtn = driver.findElements(By.id(addId));
        if (!addBtn.isEmpty()) return addBtn.get(0).getText();
        return "";
    }

    public String getCartBadgeText() {
        if (cartBadge.isEmpty()) return "";
        return cartBadge.get(0).getText();
    }

    public void openCart() {
        cartIcon.click();
    }

    public void openMenu() {
        menuButton.click();
    }

    public void resetAppState() {
        openMenu();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", resetAppStateLink); } catch (Exception e) { resetAppStateLink.click(); }
        try { Thread.sleep(400); } catch (InterruptedException ignored) {}
        try { driver.findElement(By.id("react-burger-cross-btn")).click(); } catch (Exception ignored) {}
    }

    public void logout() {
        openMenu();
        try { Thread.sleep(1000); } catch (InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink); } catch (Exception e) { logoutLink.click(); }
    }

    public boolean isOnInventoryPage() {
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public int getInventoryItemsCount() {
        return inventoryItems.size();
    }
}
