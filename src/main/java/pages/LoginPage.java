package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id = "user-name")
    WebElement userNameInput;

    @FindBy(id = "password")
    WebElement passwordInput;

    @FindBy(id = "login-button")
    WebElement loginButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void login(String user, String pass) {
        userNameInput.sendKeys(user);
        passwordInput.sendKeys(pass);
        loginButton.click();
    }

    public String getErrorText() {
        try { return errorMessage.getText(); } catch (Exception e) { return ""; }
    }

    public boolean isErrorDisplayed() {
        try { return errorMessage.isDisplayed(); } catch (Exception e) { return false; }
    }
}
