package qaselenium.screens;

import qaselenium.extensions.Requirements;
import qaselenium.extensions.UIScreenActions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import core.services.logger.Log;

import java.util.Arrays;
import java.util.List;

public class EbayHomePage extends EbayPageCommons {

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

    private List<String> navigationBarElements = Arrays.asList("Deals","Mobile & Accessories","Laptops & Tablets","Fashion","Cameras","Televisions","Health & Beauty","All Electronics");

    public void mouseHoverNavigationBarElement(String elementName)
    {
        Log.info("Mouse hovering over element "+elementName);
        if(!navigationBarElements.contains(elementName))
        {
            Log.error("No such element available in navigation bar");
            return;
        }

        WebElement element = driver.findElement(By.xpath("//*[@role='listitem']/a[text()='"+elementName+"']"));

        new UIScreenActions(driver);
        UIScreenActions.mouseHoverOverElement(element);

        Requirements.waitInSeconds(2);
    }

    public void selectElementUnderNavigationBar(String elementName)
    {
        Log.info("Clicking on element "+elementName+" under naviagtion bar");
        WebElement element = driver.findElement(By.xpath("//*[@role='listitem']/a[text()='Mobile & Accessories']//following-sibling::div[@class='sub']/div[@class='sub-cats']//li/a[contains(@title,'"+elementName+"')]"));

        element.click();
    }

    public void clickTile(String tileName)
    {
        Log.info("Clicking tile "+tileName);
        WebElement tile = driver.findElement(By.xpath("//*[@class='description-left']//a[text()='"+tileName+"']"));
        tile.click();
    }

    public int getTileItems(String tileName)
    {
        WebElement numberOfItems = driver.findElement(By.xpath("//*[@class='description-left']//a[text()='"+tileName+"']//following::div/*[@class='big-hero-info']/ul/li[1]"));
        String[] num = numberOfItems.getText().split("\\s+");
        return Integer.valueOf(num[0]);
    }
}
