package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ShoppingCartPage extends BasePage{
    @FindBy(xpath = "//h1[@class='main-title']")
    private WebElement shoppingCartHeader;

    @FindBy(xpath = "//div[@id='mainContent']")
    private WebElement shoppingCartContent;

    @FindBy(xpath = "//button[@class='call-to-action btn btn--large btn--primary']")
    private WebElement goToCheckoutButton;

    @FindBy(xpath = "//button[@data-test-id='cart-remove-item']")
    private WebElement removeLink;

    @FindBy(xpath = "//div[@class='font-title-3']")
    private WebElement emptyCartTitle;

    @FindBy(xpath = "//div[@id='msku-sel-1-errMsg']")
    private WebElement selectPhoneModelWarningMessage;

    @FindBy(xpath = "//div[@class='val-col total-row']/span[@class='text-display-with-info']/span/span[contains(text(),'US $')]")
    private WebElement subtotalPrice;


    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }


    public boolean isShoppingCardContentVisible() {
        return shoppingCartContent.isDisplayed();
    }

    public String getCartHeaderText() {
        return shoppingCartHeader.getText();
    }

    public boolean isGoToCheckoutButtonVisible() {
        return goToCheckoutButton.isDisplayed();
    }

    public void clickRemoveLink(){
        removeLink.click();
    }

    public WebElement getEmptyCartTitle() {
        return emptyCartTitle;
    }

    public boolean isWarningMessageDisplayed() {
        return selectPhoneModelWarningMessage.isDisplayed();
    }

    public WebElement getSelectPhoneModelWarningMessage(){
        return selectPhoneModelWarningMessage;
    }

    public double getActualSubtotalPrice() {
        String totalPriceValue = subtotalPrice.getText();
        return Double.parseDouble(totalPriceValue.replaceAll("[^\\d.]", ""));
    }
}
