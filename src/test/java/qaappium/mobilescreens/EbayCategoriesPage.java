package qaappium.mobilescreens;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;
import qaappium.extensions.ScreenActions;

import java.util.List;

public class EbayCategoriesPage extends EbayMobilePageCommons {

    AppiumDriver driver;

    public EbayCategoriesPage(AppiumDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id="com.ebay.mobile:id/parent")
    List<WebElement> numberOfTiles;

    public int getNumberOfTiles()
    {
        Log.info("Number of Tiles inside Categories Page are "+numberOfTiles.size());
        ScreenActions.swipe(driver, numberOfTiles.get(numberOfTiles.size() - 1));
        return numberOfTiles.size();
    }
}
