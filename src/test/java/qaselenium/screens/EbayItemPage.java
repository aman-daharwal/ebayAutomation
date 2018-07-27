package qaselenium.screens;

import core.services.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayItemPage extends EbayPageCommons
{
    WebDriver driver;

    public EbayItemPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='itemTitle']")
    WebElement itemName;

    public String getItemName()
    {
        Log.info("Item Page Name is "+itemName.getText());
        return itemName.getText();
    }

    @FindBy(id = "binBtn_btn")
    WebElement buyItNowButton;

    public void clickBuyItNowButton()
    {
        Log.info("Clicking Buy It Now Button");
        buyItNowButton.click();
    }
}
