package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

import java.util.List;

public class YourCartPage {
    WebDriver driver;
    @FindBy(id = "checkout")
    WebElement checkoutButton;

    @FindBy(className = "cart_item")
    List<WebElement> cartItems;

    @FindBy(className = "shopping_cart_badge")
    List<WebElement> cartBadge;

    @FindBy(id = "react-burger-menu-btn")
    WebElement menuButton;

    @FindBy(id = "reset_sidebar_link")
    WebElement resetAppStateLink;

    public YourCartPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public int getCartItemsCount(){
        try { return DriverManager.getDriver().driver.findElements(By.className("cart_item")).size(); } catch(Exception e) { return cartItems.size(); }
    }

    public List<WebElement> getCartItems(){
        try { return DriverManager.getDriver().driver.findElements(By.className("cart_item")); } catch(Exception e) { return cartItems; }
    }

    public String getCartBadgeText(){
        if(cartBadge.isEmpty()) return "";
        return cartBadge.get(0).getText();
    }

    public void resetAppState(){
        menuButton.click();
        try { Thread.sleep(800); } catch(InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", resetAppStateLink); } catch(Exception e) { resetAppStateLink.click(); }
        try { Thread.sleep(400); } catch(InterruptedException ignored) {}
        try { driver.findElement(By.id("react-burger-cross-btn")).click(); } catch(Exception ignored) {}
    }

    public void clickOnCheckoutButton(){
        checkoutButton.click();
    }

}
