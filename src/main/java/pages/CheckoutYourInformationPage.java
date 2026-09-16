package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckoutYourInformationPage {
    WebDriver driver;

    @FindBy(id="first-name")
    WebElement firstNameTextBox;

    @FindBy(id="last-name")
    WebElement lastNameTextBox;

    @FindBy(id="postal-code")
    WebElement zipCodeTextBox;

    @FindBy(id="continue")
    WebElement continueButton;

    @FindBy(css = "h3[data-test='error']")
    WebElement errorMessage;

    public CheckoutYourInformationPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isErrorDisplayed(){
        try { return errorMessage.isDisplayed(); } catch(Exception e) { return false; }
    }

    public String getErrorText(){
        try { return errorMessage.getText(); } catch(Exception e) { return ""; }
    }

    public boolean isOnStepOne(){
        return driver.getCurrentUrl().contains("checkout-step-one.html");
    }

    public boolean isOnStepTwo(){
        return driver.getCurrentUrl().contains("checkout-step-two.html");
    }

    public String getFirstNameValue(){
        return firstNameTextBox.getAttribute("value");
    }

    public String getPostalCodeValue(){
        return zipCodeTextBox.getAttribute("value");
    }

    public void fillCheckoutForm(String firstName, String lastName, String postalCode){
        firstNameTextBox.clear();
        firstNameTextBox.sendKeys(firstName);
        lastNameTextBox.clear();
        lastNameTextBox.sendKeys(lastName);
        zipCodeTextBox.clear();
        zipCodeTextBox.sendKeys(postalCode);
    }

    public void clickOnContinueButton(){
        continueButton.click();
    }

}
