package qaselenium.screens;

import core.services.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class EbayReviewOrderPage {
    WebDriver driver;

    public EbayReviewOrderPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@class='xo-ww-ttl']")
    List<WebElement> itemsToPurchase;

    public List<String> getItemsSelectedToPurchase() {
        List<String> itemsNames = new ArrayList<String>();

        for (WebElement items : itemsToPurchase)
        {
            itemsNames.add(items.getText());
            Log.info("Item :"+items.getText()+" present in cart");
        }

        Assert.assertNotNull(itemsNames,"No Item detected inside list, Please check the Item added to order"+
                                                "\nIf it is, Please file a script issue against the script");

        return itemsNames;
    }

    public void removeItem(String itemName)
    {
        Log.info("Removing item "+itemName+" from Review Order Page");
        driver.findElement(By.xpath("//*[@title='"+itemName+"']/preceding::div[@class='xo-itm-remv']/a")).click();
    }
}
