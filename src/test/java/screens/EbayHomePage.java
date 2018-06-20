package screens;

import commons.TestBase;
import logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayHomePage {

    public WebDriver driver;

    public EbayHomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='gh-ac']")
    WebElement searchBox;

    @FindBy(id = "gh-btn")
    WebElement searchButton;

    public void enterTextInSearchBox(String text)
    {
        Log.info("Entering text '"+text+"' inside Search box");
        searchBox.sendKeys(text);
    }

    public void clickSearchButton() {
        Log.debug("Clicking search button");
        searchBox.click();
    }
}
