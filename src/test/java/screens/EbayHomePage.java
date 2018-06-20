package screens;

import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import services.logger.Log;
import services.screenshot.Screenshot;

import java.util.List;

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

    @FindBy(xpath = "//*[@class='bcrs']/span/b")
    WebElement searchTitle;

    @FindBy(xpath = "//*[@class='big-heros']/div")
    List<WebElement> tiles;

    public void enterTextInSearchBox(String text)
    {
        Log.info("Entering text '"+text+"' inside Search box");
        searchBox.sendKeys(text);
    }

    public void clickSearchButton() {
        Log.debug("Clicking search button");
        searchButton.click();
    }

    public String getSearchedText()
    {
        String searchedTitled = searchTitle.getText();
        Log.info("Item searched in ebay search box is '"+searchedTitled+"'");
        Screenshot.TakeScreenshot("Item Searched "+searchedTitled);
        return searchedTitled.trim();
    }

    public short GetNumberOfTiles()
    {
        short numberOfTiles = (short)tiles.size();
        Log.info("Number of Tiles inside Home Page : "+numberOfTiles);
        return numberOfTiles;
    }
}
