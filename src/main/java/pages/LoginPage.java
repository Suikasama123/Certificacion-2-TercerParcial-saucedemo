package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
    WebDriver driver;

    @FindBy(id="user-name")
    WebElement userNameTextBox;

    @FindBy(id="password")
    WebElement passwordTextBox;

    @FindBy(id="login-button")
    WebElement loginButton;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void setUserNameTextBox(String userName){
        userNameTextBox.sendKeys(userName);
    }

    public void setPasswordTextBox(String password){
        passwordTextBox.sendKeys(password);
    }

    public void clickOnLoginButton(){
        loginButton.click();
    }
}
