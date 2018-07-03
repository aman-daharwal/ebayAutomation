package qaappium.mobilescreens;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    public void enterText() throws InterruptedException {
        searchBox.click();
        Thread.sleep(2000);
        globalSearchBox.sendKeys("iPhone");
        driver.getKeyboard().pressKey(Keys.ENTER);
    }

}
