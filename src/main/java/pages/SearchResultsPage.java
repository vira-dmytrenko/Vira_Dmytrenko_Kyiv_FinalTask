package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import java.util.List;
import java.util.Set;

public class SearchResultsPage extends BasePage {
    @FindBy(xpath = "//div[@id='srp-river-results']//a[@class = 's-item__link']")
    private List<WebElement> searchResultsList;

    @FindBy(xpath = "//div[@id='srp-river-results']//h3[@class = 's-item__title']")
    private List<WebElement> searchResultsProductsListText;

    @FindBy(xpath = "//li[@class='s-item s-item__pl-on-bottom s-item--watch-at-corner s-item__before-answer']//a[@class='s-item__link']")
    private WebElement searchedProduct;

    @FindBy(xpath = "//div[@class='s-item__image']//a[@_sp='p2351460.m1686.l7400']")
    private WebElement searchedProductImage;

    @FindBy(xpath = "//li[@class='x-refine__main__list ']/div/h3[text() ='Brand']/../..//span[@class='cbx x-refine__multi-select-cbx']")
    private List<WebElement> listOfFiltersByBrand;

    public SearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public int getSearchResultsCount() {
        return searchResultsList.size();
    }

    public List<WebElement> getSearchResultsList() {
        return searchResultsProductsListText;
    }

    public void clickOnProduct() {
        Actions actions = new Actions(driver);
        actions.moveToElement(searchedProductImage)
                .doubleClick()
                .build().perform();

        // switch to next tabs
        String parent = driver.getWindowHandle();
        Set<String> s = driver.getWindowHandles();
        for (String child_window : s) {
            if (!parent.equals(child_window)) {
                driver.switchTo().window(child_window);
            }
        }
    }

    public void clickFilterCheckbox(String value) {
        for (WebElement webElement : listOfFiltersByBrand) {
            if (webElement.getText().contains(value)){
                webElement.click();
                break;
            }
        }
    }


    public boolean productTitleIsVisible() {
        return searchedProductImage.isDisplayed();
    }
}
