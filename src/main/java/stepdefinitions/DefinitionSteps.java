package stepdefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import manager.PageFactoryManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static io.github.bonigarcia.wdm.WebDriverManager.chromedriver;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DefinitionSteps {
    private static final long DEFAULT_TIMEOUT = 30;
    WebDriver driver;
    HomePage homePage;
    SignInPage signInPage;
    SearchResultsPage searchResultsPage;
    ProductPage productPage;
    ShoppingCartPage shoppingCartPage;
    PageFactoryManager pageFactoryManager;
    private double savedPrice;

    @Before
    public void testsSetUp() {
        chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        pageFactoryManager = new PageFactoryManager(driver);
    }

    @After
    public void tearDown() {
        driver.quit();
    }

    @And("User opens {string} page")
    public void openPage(final String url) {
        homePage = pageFactoryManager.getHomePage();
        homePage.openHomePage(url);
        homePage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User checks header visibility")
    public void checkHeaderVisibility() {
        assertTrue(homePage.isHeaderVisible());
    }

    @And("User checks footer visibility")
    public void checkFooterVisibility() {
        assertTrue(homePage.isFooterVisible());
    }

    @And("User checks search field visibility")
    public void checkSearchFieldVisibility() {
        assertTrue(homePage.isSearchFieldVisible());
    }

    @And("User checks cart visibility")
    public void checkCartVisibility() {
        assertTrue(homePage.isCartIconVisible());
    }

    @And("User checks register link visibility")
    public void checkRegisterLinkVisibility() {
        assertTrue(homePage.isRegisterLinkVisible());
    }

    @And("User checks 'Sign in' link visibility")
    public void checkSignInLinkVisibility() {
        assertTrue(homePage.isSignInLinkVisible());
    }

    @And("User clicks 'Sign In' link")
    public void clickSignInLink() {
        homePage.clickSignInLink();
        signInPage = pageFactoryManager.getSignInPage();
        signInPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        signInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signInPage.getSignInFormTitle());
    }

    @And("User checks userId field visibility on sign in page")
    public void checkUserIdFieldVisibilityOnSignInPage() {
        assertTrue(signInPage.isUserIdFieldVisible());
        assertTrue(signInPage.isContinueButtonVisible());
    }

    @And("User opens shop by category popup")
    public void openShopByCategoryPopup() {
        homePage.clickShopByCategoryButton();
    }

    @And("User closes shop by category popup")
    public void closeShopByCategoryPopup() {
        homePage.clickShopByCategoryButton();
    }

    @And("User checks shop by category popup visibility")
    public void checkShopByCategoryPopupVisibility() {
        assertTrue(homePage.shopByCategoryPopupIsVisible());
    }

    @And("User points at shopping cart")
    public void navigateToShoppingCart() {
        homePage.navigateToCartIcon();
        homePage.waitVisibilityOfElement(DEFAULT_TIMEOUT, homePage.getShoppingCartPopup());
    }

    @And("User checks shopping cart popup visibility")
    public void checkShoppingCartPopupVisibility() {
        assertTrue(homePage.isShoppingCartPopupVisible());
    }

    @And("User checks that shopping cart popup header is {string}")
    public void userShoppingCartPopupHeader(final String expectedText) {
        assertEquals(expectedText, homePage.getSoppingCartPopupHeaderText());
    }

    @And("User makes search by keyword {string}")
    public void enterKeywordToSearchField(final String keyword) {
        homePage.enterTextIntoSearchField(keyword);
    }

    @And("User clicks search button")
    public void clickSearchButton() {
        homePage.clickSearchButton();
        searchResultsPage = pageFactoryManager.getSearchResultsPage();
        searchResultsPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User checks that amount of products in search page is {int}")
    public void checkAmountOfProductsInSearchPage(final int expectedAmount) {
        assertEquals(expectedAmount, searchResultsPage.getSearchResultsCount());
    }

    @And("User checks that every product description on the page contains {string}")
    public void checkThatSearchResultsContainsSearchWord(final String expectedText) {
        for (WebElement webElement : searchResultsPage.getSearchResultsList()) {
            assertTrue(webElement.getText().toLowerCase().contains(expectedText.toLowerCase()));
        }
    }

    @And("User clicks continue button")
    public void clickContinueButton() {
        signInPage.clickContinueButton();
    }

    @And("User inputs {string} in password field")
    public void inputPasswordIntoPasswordField(final String password) {
        signInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signInPage.getPasswordField());
        signInPage.inputPassword(password);
    }

    @And("User clicks 'Sign In' button")
    public void clickSignInButton() {
        signInPage.clickSignInButton();
    }

    @And("User checks his {string} appears in top left corner of the page")
    public void checkUserName(final String expectedName) {
        assertTrue(homePage.userGreetingText().contains(expectedName));
    }

    @And("User inputs {string} into user id field")
    public void inputDataIntoUserIdField(final String id) {
        signInPage.inputUserId(id);
    }

    @And("User checks error message 'Oops, that's not a match.' appears above user id field")
    public void checkWarningMessageForPhoneModel() {
        signInPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, signInPage.getErrorMessage());
        assertEquals("Oops, that's not a match." , signInPage.getErrorMessageText());
    }

    @And("User checks product name visibility")
    public void checksProductNameVisibility() {
        assertTrue(searchResultsPage.productTitleIsVisible());
    }

    @And("User clicks on product")
    public void clickSearchedProduct() {
        searchResultsPage.clickOnProduct();
        productPage = pageFactoryManager.getProductPage();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
    }

    @And("User clicks 'Add to Cart' button on product")
    public void clickAddToCart() {
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getAddToCartButton());
        productPage.clickAddToCartButton();
    }


    @And("User checks shopping cart visibility")
    public void checkCartPageVisibility() {
        shoppingCartPage = pageFactoryManager.getShoppingCartPage();
        shoppingCartPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        assertTrue(shoppingCartPage.isShoppingCardContentVisible());
    }

    @Then("User checks that cart header is {string}")
    public void checkCartHeader(final String expectedText) {
        assertEquals(expectedText, shoppingCartPage.getCartHeaderText());
    }

    @And("User checks 'go to checkout' button visibility")
    public void checkGoToCheckoutButtonVisibility() {
        assertTrue(shoppingCartPage.isGoToCheckoutButtonVisible());
    }

    @And("User select {string}")
    public void selectPhoneModel(String modelName) {
        productPage = pageFactoryManager.getProductPage();
        productPage.selectPhoneModel(modelName);
    }

    @And("User clicks on remove link")
    public void clickRemoveLink() {
        shoppingCartPage.clickRemoveLink();
    }

    @And("User checks cart title {string}")
    public void checkEmptyCartText(String expectedTitle) {
        shoppingCartPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, shoppingCartPage.getEmptyCartTitle());
        assertEquals(expectedTitle, shoppingCartPage.getEmptyCartTitle().getText());
    }

    @Then("User checks {string} for phone model selector")
    public void checkWarningMessageForPhoneModel(String msg) {
        assertTrue(shoppingCartPage.isWarningMessageDisplayed());
        assertEquals(msg, shoppingCartPage.getSelectPhoneModelWarningMessage().getText());
    }

    @And("User clicks on {string} checkbox")
    public void clickOnFilterCheckbox(String value) {
        searchResultsPage.clickFilterCheckbox(value);
    }

    @And("User inputs {string} into quantity field")
    public void inputValueIntoQuantityField(String value) {
        productPage = pageFactoryManager.getProductPage();
        productPage.waitForPageLoadComplete(DEFAULT_TIMEOUT);
        productPage.inputValueIntoQuantityField(value);
    }

    @Then("User checks {string} for quantity field")
    public void checkWarningMessageForQuantityField(String expectedMessage) {
        productPage.waitVisibilityOfElement(DEFAULT_TIMEOUT, productPage.getQuantityWarningMessage());
        assertTrue(productPage.isQuantityWarningMessageDisplayed());
        assertEquals(expectedMessage, productPage.getQuantityWarningMessage().getText());
    }

    @And("User checks subtotal cart amount for quantity of {int}")
    public void checkSubtotalCartAmount(int quantity) {
        double errorDelta = 0.001;
        assertEquals(getSavedPrice() * quantity, shoppingCartPage.getActualSubtotalPrice(), errorDelta);
    }


    @And("User remembers current product price as 'product price'")
    public void rememberProductPrice() {
        savePrice(productPage.getProductPrice());
    }

    private void savePrice(double productPrice) {
        savedPrice = productPrice;
    }

    private double getSavedPrice(){
        return savedPrice;
    }


}
