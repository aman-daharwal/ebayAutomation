package qaselenium.screens;

import core.services.logger.Log;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class EbayTilePage extends EbayPageCommons {

    WebDriver driver;

    public EbayTilePage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@class='collection-title']")
    WebElement pageTitle;

    public String getTilePageTitle()
    {
        Log.info("Page title is : "+pageTitle.getText());
        return pageTitle.getText();
    }

    @FindBy(xpath = "//*[contains(@class,'itemThumb')]")
    List<WebElement> numberOfTileItems;

    public int getNumberOfItems()
    {
        Log.info("Number of items in the page are "+numberOfTileItems.size());
        return numberOfTileItems.size();
    }
}
