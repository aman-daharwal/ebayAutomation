package screens;

import extensions.UIScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import services.logger.Log;
import services.screenshot.Screenshot;

import java.util.Arrays;
import java.util.List;

public class EbayHomePage extends EbayPageCommons{

    public WebDriver driver;

    public EbayHomePage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='big-heros']/div")
    private List<WebElement> tiles;

    public short GetNumberOfTiles() {
        short numberOfTiles = (short) tiles.size();
        Log.info("Number of Tiles inside Home Page : " + numberOfTiles);
        return numberOfTiles;
    }
}
