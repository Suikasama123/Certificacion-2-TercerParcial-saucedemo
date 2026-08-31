package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutPage {
    WebDriver driver;

    @FindBy(id = "first-name")
    WebElement firstNameInput;

    @FindBy(id = "last-name")
    WebElement lastNameInput;

    @FindBy(id = "postal-code")
    WebElement postalCodeInput;

    @FindBy(id = "continue")
    WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    @FindBy(className = "title")
    WebElement title;

    @FindBy(className = "summary_total_label")
    WebElement totalLabel;

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode) {
        firstNameInput.clear();
        firstNameInput.sendKeys(firstName);
        lastNameInput.clear();
        lastNameInput.sendKeys(lastName);
        postalCodeInput.clear();
        postalCodeInput.sendKeys(postalCode);
    }

    public void clickContinue() {
        continueButton.click();
    }

    public boolean isErrorDisplayed() {
        try { return errorMessage.isDisplayed(); } catch (Exception e) { return false; }
    }

    public String getErrorText() {
        try { return errorMessage.getText(); } catch (Exception e) { return ""; }
    }

    public String getTitleText() {
        return title.getText();
    }

    public boolean isOnStepOne() {
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }

    public boolean isOnStepTwo() {
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public String getFirstNameValue() {
        return firstNameInput.getAttribute("value");
    }

    public String getPostalCodeValue() {
        return postalCodeInput.getAttribute("value");
    }
}
