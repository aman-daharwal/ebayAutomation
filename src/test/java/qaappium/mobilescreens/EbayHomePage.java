package qaappium.mobilescreens;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaappium.mobileEnums.HomePills;

public class EbayHomePage
{
    AppiumDriver driver;

    public EbayHomePage(AppiumDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "com.ebay.mobile:id/search_box")
    WebElement searchBox;

    @FindBy(id = "com.ebay.mobile:id/search_src_text")
    WebElement globalSearchBox;

    public void searchText(String text)
    {
        Log.info("Searching Text : "+text);
        searchBox.click();

        searchInGlobalSearchBox(text);

        driver.getKeyboard().sendKeys(Keys.ENTER);
    }

    private void searchInGlobalSearchBox(String text)
    {
        globalSearchBox.sendKeys(text);
    }

    public void selectHomePill(HomePills pills)
    {
        Log.info("Clicking on Home pill "+pills.getValue());
        driver.findElement(By.xpath("//*[@text='"+pills.getValue()+"']")).click();
    }
}
