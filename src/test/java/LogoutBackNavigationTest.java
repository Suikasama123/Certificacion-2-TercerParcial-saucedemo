import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.List;

public class LogoutBackNavigationTest extends BaseTest {

    private String waitForEpicError() throws InterruptedException {
        for (int i = 0; i < 12; i++) {
            Thread.sleep(500);
            List<org.openqa.selenium.WebElement> cand = driver.findElements(By.xpath("//*[contains(text(),'Epic sadface')]"));
            if (!cand.isEmpty() && cand.get(0).isDisplayed()) return cand.get(0).getText();
            List<org.openqa.selenium.WebElement> h3 = driver.findElements(By.cssSelector("h3[data-test='error']"));
            if (!h3.isEmpty() && h3.get(0).isDisplayed()) return h3.get(0).getText();
        }
        System.out.println("URL no error: " + driver.getCurrentUrl());
        return "";
    }

    @Test
    public void verifyEpicSadfaceErrorsAfterLogoutAndBackNavigation() throws InterruptedException {
        LoginPage login = new LoginPage(driver);
        Assertions.assertFalse(login.isErrorDisplayed());
        String preError = "";
        try { preError = login.getErrorText(); } catch(Exception e) { preError = ""; }
        Assertions.assertTrue(preError.equals("") || preError.contains("Epic"));
        login.login("standard_user", "secret_sauce");
        Thread.sleep(1500);
        System.out.println("After login URL: " + driver.getCurrentUrl());

        pages.InventoryPage invCheck = new pages.InventoryPage(driver);
        Assertions.assertTrue(invCheck.getInventoryItemsCount() == 6);
        By addBtn = By.id("add-to-cart-sauce-labs-backpack");
        for(int i=0;i<3;i++){
            List<org.openqa.selenium.WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
            String b = badges.isEmpty() ? "" : badges.get(0).getText();
            if("1".equals(b)) break;
            org.openqa.selenium.WebElement btn = driver.findElement(addBtn);
            ((JavascriptExecutor)driver).executeScript("arguments[0].click();", btn);
            Thread.sleep(800);
        }
        String badge = "";
        List<org.openqa.selenium.WebElement> badges = driver.findElements(By.className("shopping_cart_badge"));
        if (!badges.isEmpty()) badge = badges.get(0).getText();
        System.out.println("Badge: '" + badge + "'");
        Assertions.assertEquals("1", badge, "Debe tener 1 producto en carrito");

        driver.navigate().to("https://www.saucedemo.com/cart.html");
        Thread.sleep(1000);
        System.out.println("Cart URL: " + driver.getCurrentUrl());
        List<org.openqa.selenium.WebElement> checkoutBtns = driver.findElements(By.id("checkout"));
        if (checkoutBtns.isEmpty()) checkoutBtns = driver.findElements(By.cssSelector("[data-test='checkout']"));
        Assertions.assertFalse(checkoutBtns.isEmpty(), "Checkout debe existir");
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", checkoutBtns.get(0));
        Thread.sleep(1000);
        System.out.println("Checkout URL: " + driver.getCurrentUrl());
        Assertions.assertTrue(driver.getCurrentUrl().contains("checkout-step-one"), "Debe estar en checkout-step-one: " + driver.getCurrentUrl());

        pages.InventoryPage invLogout = new pages.InventoryPage(driver);
        invLogout.logout();
        Thread.sleep(1200);
        System.out.println("After logout URL: " + driver.getCurrentUrl());
        boolean onLogin = driver.getCurrentUrl().contains("index.html") || !driver.findElements(By.id("login-button")).isEmpty() || !driver.findElements(By.cssSelector("[data-test='login-button']")).isEmpty();
        Assertions.assertTrue(onLogin, "Debe estar en login tras logout");

        List<String> errors = new ArrayList<>();
        driver.navigate().back();
        String e1 = waitForEpicError();
        System.out.println("Back1: " + e1 + " | url:" + driver.getCurrentUrl());
        Assertions.assertFalse(e1.isEmpty(), "Back1 debe mostrar error");
        errors.add(e1);
        driver.navigate().back();
        String e2 = waitForEpicError();
        System.out.println("Back2: " + e2 + " | url:" + driver.getCurrentUrl());
        Assertions.assertFalse(e2.isEmpty(), "Back2 debe mostrar error");
        errors.add(e2);
        driver.navigate().back();
        String e3 = waitForEpicError();
        System.out.println("Back3: " + e3 + " | url:" + driver.getCurrentUrl());
        Assertions.assertFalse(e3.isEmpty(), "Back3 debe mostrar error");
        errors.add(e3);

        String all = String.join(" | ", errors);
        System.out.println("All: " + all);
        Assertions.assertTrue(all.contains("/cart.html"), "Debe aparecer /cart.html: " + all);
        Assertions.assertTrue(all.contains("/checkout-step-one.html"), "Debe aparecer /checkout-step-one.html: " + all);
        Assertions.assertTrue(all.contains("/inventory.html"), "Debe aparecer /inventory.html: " + all);
        Assertions.assertTrue(errors.stream().anyMatch(s -> s.contains("/cart.html") && s.contains("Epic sadface")), "Cart error exact");
        Assertions.assertTrue(errors.stream().anyMatch(s -> s.contains("/checkout-step-one.html") && s.contains("Epic sadface")), "Checkout error");
        Assertions.assertTrue(errors.stream().anyMatch(s -> s.contains("/inventory.html") && s.contains("Epic sadface")), "Inventory error");
    }
}
