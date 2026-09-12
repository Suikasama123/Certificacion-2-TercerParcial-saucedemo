package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.ArrayList;
import java.util.List;

public class HomePage {
    WebDriver driver;

    @FindBy(className = "app_logo")
    WebElement homeTitle;

    @FindBy(className = "shopping_cart_link")
    WebElement shoppingCartIcon;

    @FindBy(className = "inventory_item_name")
    List<WebElement> productNames;

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

    public HomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public String getCartBadgeText(){
        List<WebElement> badge = driver.findElements(By.className("shopping_cart_badge"));
        if(badge.isEmpty()) return "";
        return badge.get(0).getText();
    }

    public boolean isCartBadgeDisplayed(){
        try { return !cartBadge.isEmpty() && cartBadge.get(0).isDisplayed(); } catch(Exception e) { return false; }
    }

    public int getInventoryItemsCount(){
        return inventoryItems.size();
    }

    public String getButtonTextForProduct(String productName){
        String addId = "add-to-cart-" + productName.toLowerCase().replace(" ", "-");
        String removeId = "remove-" + productName.toLowerCase().replace(" ", "-");
        List<WebElement> removeBtn = driver.findElements(By.id(removeId));
        if(!removeBtn.isEmpty() && removeBtn.get(0).isDisplayed()) return removeBtn.get(0).getText();
        List<WebElement> addBtn = driver.findElements(By.id(addId));
        if(!addBtn.isEmpty()) return addBtn.get(0).getText();
        return "";
    }

    public void openMenu(){
        menuButton.click();
    }

    public void resetAppState(){
        openMenu();
        try { Thread.sleep(800); } catch(InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", resetAppStateLink); } catch(Exception e) { resetAppStateLink.click(); }
        try { Thread.sleep(400); } catch(InterruptedException ignored) {}
        try { driver.findElement(By.id("react-burger-cross-btn")).click(); } catch(Exception ignored) {}
    }

    public void logout(){
        openMenu();
        try { Thread.sleep(1000); } catch(InterruptedException ignored) {}
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", logoutLink); } catch(Exception e) { logoutLink.click(); }
    }

    public boolean isOnInventoryPage(){
        return driver.getCurrentUrl().contains("inventory.html");
    }

    public boolean homeTitleIsDisplayed(){
        return homeTitle.isDisplayed();
    }

    public void addProductToCart(String productName){
        String addToCartButtonId = "add-to-cart-"+productName.replace(" ", "-").toLowerCase();
        driver.findElement(By.id(addToCartButtonId)).click();
    }

    public void removeProductToCart(String productName){
        String removeFromCartButtonId = "remove-"+productName.replace(" ", "-").toLowerCase();
        driver.findElement(By.id(removeFromCartButtonId)).click();
    }

    public String getShoppingCartIconText(){
        return shoppingCartIcon.getText();
    }

    public List<String> getProductNames(){
        List<String> productNamesText = new ArrayList<>();
        for(WebElement productName: productNames){
            productNamesText.add(productName.getText());
        }
        return productNamesText;
    }

    public void clickOnCartIcon(){
        try { ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].click();", shoppingCartIcon); } catch(Exception e) { shoppingCartIcon.click(); }
        try { Thread.sleep(800); } catch(InterruptedException ignored) {}
    }


}
