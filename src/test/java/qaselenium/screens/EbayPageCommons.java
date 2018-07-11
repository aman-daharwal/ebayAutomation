package qaselenium.screens;

import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.services.logger.Log;
import core.services.screenshot.Screenshot;

import java.util.Arrays;
import java.util.List;

public class EbayPageCommons {

    WebDriver driver;

    protected EbayPageCommons(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected List<String> categories = Arrays.asList("Televisions & Home Entertainment", "Cameras & Optics", "Data Storage",
            "Home and Living", "Laptops & Computer Peripherals", "Fashion","Mobile Phones and Accessories",
            "Beauty, Health & Grocery", "Other");

    @FindBy(xpath = "//*[@id='gh-ac']")
    private WebElement searchBox;

    @FindBy(id = "gh-btn")
    private WebElement searchButton;

    @FindBy(xpath = "//*[@class='bcrs']/span/b")
    private WebElement searchTitle;

    @FindBy(xpath = "//*[@id='gh-shop-a']")
    private WebElement categoryButton;

    public void enterTextInSearchBox(String text) {
        Log.info("Entering text '" + text + "' inside Search box");
        searchBox.sendKeys(text);
    }

    public void clickSearchButton() {
        Log.debug("Clicking search button");
        searchButton.click();
    }

    public String getSearchedText() {
        String searchedTitled = searchTitle.getText();
        Log.info("Item searched in ebay search box is '" + searchedTitled + "'");
        Screenshot.TakeScreenshot("Item Searched " + searchedTitled);
        return searchedTitled.trim();
    }

    public void clickShopbyCategoryButton() {
        Log.debug("Clicking shop by category button");
        categoryButton.click();
    }

    public void selectCategory(String category)
    {
        Log.info("Clicking category "+category);
        if (!Boolean.valueOf(categoryButton.getAttribute("aria-expanded")))
            clickShopbyCategoryButton();

        short row  = getCategoryTableRow(category);
        if(row==0)
        {
            Log.error("No Such category available");
        }

        WebElement categoryElement = driver.findElement(By.xpath("//*[@id='gh-sbc']//td["+row+"]/h3/a[@title='" + category.trim() + "']"));

        categoryElement.click();
    }

    private short getCategoryTableRow(String category) {
        for (String item : categories)
        {
            if (category.equals(item))
            {
                short index = (short) categories.indexOf(item);
                if (index <= 2) {
                    return 1;
                } else if (index > 2 & index <= 5) {
                    return 2;
                } else if (index > 5) {
                    return 3;
                }
            }
        }
        Log.error(category+" is not available inside Shop by Category section.");
        return 0;
    }

    @FindBy(xpath = "//*[@id='gh-ug']/a")
    WebElement signInButton;

    public void eBaySignIn()
    {
        Log.info("Clicking home page signing button");
        signInButton.click();
    }

    @FindBy(xpath = "//*[@id='gh-ug']/b[2]")
    WebElement accountArrow;

    @FindBy(xpath = "//*[@id='gh-uo']/a")
    WebElement signOutButton;

    public void eBaySignOut()
    {
        Log.info("Signing out from ebay");
        accountArrow.click();

        signOutButton.click();
        Screenshot.TakeScreenshot("Signed Out");
    }
}
