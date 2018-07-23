package qaappium.mobilescreens;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayMobilePageCommons extends EbaySideBar{

    AppiumDriver driver;

    protected EbayMobilePageCommons(AppiumDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "com.ebay.mobile:id/home")
    WebElement hamburgerIcon;
    //// using WebElement instead of MobileElement because of below issues
    //// https://github.com/appium/java-client/issues/770
    ////https://github.com/appium/java-client/issues/919

    @FindBy(id = "com.ebay.mobile:id/title")
    WebElement pageTitle;

    public void clickHamburgerIcon()
    {
        Log.info("clicking Home page hamberger Icon");
        hamburgerIcon.click();
    }

    public String getPageTitle()
    {
        Log.info("Currently on Page : "+pageTitle.getText());
        return pageTitle.getText();
    }
}
