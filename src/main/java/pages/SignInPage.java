package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPage extends BasePage{

    @FindBy(xpath = "//input[@id='userid']")
    private WebElement userIdField;

    @FindBy(xpath = "//button[@id='signin-continue-btn']")
    private WebElement continueButton;

    @FindBy(xpath = "//h1[@id='greeting-msg']")
    private WebElement signInFormTitle;

    @FindBy(xpath = "//input[@id='pass']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@id='sgnBt']")
    private WebElement signInButton;

    @FindBy(xpath = "//p[@id='errormsg']")
    private WebElement errorMessage;

    public SignInPage(WebDriver driver) {
        super(driver);
    }

    public boolean isUserIdFieldVisible() {
        return userIdField.isDisplayed();
    }

    public boolean isContinueButtonVisible() {
        return continueButton.isDisplayed();
    }

    public WebElement getSignInFormTitle() {
        return signInFormTitle;
    }

    public void inputUserId(String id) {
        userIdField.sendKeys(id);
    }

    public void clickContinueButton() {
        continueButton.click();
    }

    public void inputPassword(String pass) {
        passwordField.sendKeys(pass);
    }

    public void clickSignInButton() {
        signInButton.click();
    }

    public WebElement getPasswordField() {
        return passwordField;
    }

    public String getErrorMessageText() {
        return errorMessage.getText();
    }

    public WebElement getErrorMessage() {
        return errorMessage;
    }
}
