package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    WebDriver driver;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(className = "shopping_cart_badge")
    List<WebElement> cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    public CartPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCartItemsCount() {
        return cartItems.size();
    }

    public List<WebElement> getCartItems() {
        return cartItems;
    }

    public void clickCheckout() {
        checkoutButton.click();
    }

    public String getCartBadgeText() {
        if (cartBadge.isEmpty()) return "";
        return cartBadge.get(0).getText();
    }

    public void resetAppState() {
        menuButton.click();
        try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", resetAppStateLink); } catch (Exception e) { resetAppStateLink.click(); }
        try { Thread.sleep(400); } catch (InterruptedException ignored) {}
        try { driver.findElement(By.id("react-burger-cross-btn")).click(); } catch (Exception ignored) {}
    }

    public boolean isCheckoutDisplayed() {
        return checkoutButton.isDisplayed();
    }
}
