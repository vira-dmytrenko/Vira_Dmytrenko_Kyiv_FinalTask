package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ProductPage extends BasePage {
    @FindBy(xpath = "//a[@id='isCartBtn_btn']")
    private WebElement addToCartButton;

    @FindBy(xpath = "//select[@name='PHONE MODEL']")
    private WebElement selectPhoneModel;

    @FindBy(xpath = "//input[@id='qtyTextBox']")
    private WebElement quantityField;

    @FindBy(xpath = "//div[@id='qtyErrMsg']")
    private WebElement inputQuantityWarningMassage;

    @FindBy(xpath = "//span[@id = 'prcIsum']")
    private WebElement productPrice;

    public ProductPage(WebDriver driver) {
        super(driver);
    }

    public void clickAddToCartButton() {
        addToCartButton.click();
    }

    public WebElement getAddToCartButton() {
        return addToCartButton;
    }

    public void selectPhoneModel(String modelName) {
        Select phoneModel = new Select(selectPhoneModel);
        phoneModel.selectByVisibleText(modelName);
    }

    public boolean isQuantityWarningMessageDisplayed() {
        return inputQuantityWarningMassage.isDisplayed();
    }

    public WebElement getQuantityWarningMessage() {
        return inputQuantityWarningMassage;
    }

    public void inputValueIntoQuantityField(String value) {
        quantityField.clear();
        quantityField.sendKeys(value);
    }

    public double getProductPrice(){
        return Double.parseDouble(productPrice.getAttribute("content"));
    }

}
