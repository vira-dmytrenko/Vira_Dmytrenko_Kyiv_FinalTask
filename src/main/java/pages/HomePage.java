package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    @FindBy(xpath = "//a[@id='gh-la']")
    private WebElement mainPageLogoLink;
    @FindBy(xpath = "//header[@id='gh']")
    private WebElement header;
    @FindBy(xpath = "//button[@id='gh-ug']")
    private WebElement userGreeting;
    @FindBy(xpath = "//footer[@id='glbfooter']")
    private WebElement footer;
    @FindBy(xpath = "//input[@class='gh-tb ui-autocomplete-input']")
    private WebElement searchField;
    @FindBy(xpath = "//li[@id='gh-minicart-hover']")
    private WebElement cartIcon;
    @FindBy(xpath = "//a[normalize-space()='register']")
    private WebElement registerLink;
    @FindBy(xpath = "//span[@id='gh-ug']//a[contains(text(),'Sign in')]")
    private WebElement signInLink;
    @FindBy(xpath = "//button[@id='gh-shop-a']")
    private WebElement shopByCategoryButton;
    @FindBy(xpath = "//div[@id='gh-shop']")
    private WebElement shopByCategoryPopup;
    @FindBy(xpath = "//div[@class='minicart']")
    private WebElement cartPopup;
    @FindBy(xpath = "//h2[@class='gh-minicart-header__title ']")
    private WebElement shoppingCartPopupHeader;
    @FindBy(xpath = "//input[@id='gh-btn']")
    private WebElement searchButton;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void openHomePage(String url) {
        driver.get(url);
    }

    public boolean isHeaderVisible() {
        return header.isDisplayed();
    }

    public boolean isFooterVisible() {
        return footer.isDisplayed();
    }

    public boolean isSearchFieldVisible() {
        return searchField.isDisplayed();
    }

    public boolean isCartIconVisible() {
        return cartIcon.isDisplayed();
    }

    public boolean isRegisterLinkVisible() {
        return registerLink.isDisplayed();
    }

    public boolean isSignInLinkVisible() {
        return signInLink.isDisplayed();
    }

    public void clickSignInLink() {
        signInLink.click();
    }

    public void clickShopByCategoryButton() {
        shopByCategoryButton.click();
    }

    public boolean shopByCategoryPopupIsVisible() {
        return shopByCategoryPopup.isDisplayed();
    }

    public void navigateToCartIcon() {
        Actions actions = new Actions(driver);
        actions.moveToElement(cartIcon).perform();
    }

    public boolean isShoppingCartPopupVisible() {
        return cartPopup.isDisplayed();
    }

    public String getSoppingCartPopupHeaderText() {
        return shoppingCartPopupHeader.getText();
    }

    public WebElement getShoppingCartPopup() {
        return cartPopup;
    }

    public void enterTextIntoSearchField(final String searchText) {
        searchField.clear();
        searchField.sendKeys(searchText);
    }

    public void clickSearchButton() {
        searchButton.click();
    }

    public String userGreetingText() {
        return userGreeting.getText();
    }

}
