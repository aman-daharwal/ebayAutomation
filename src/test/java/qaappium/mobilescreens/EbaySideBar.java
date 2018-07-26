package qaappium.mobilescreens;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import qaappium.mobileEnums.EbaySideBarItems;

public class EbaySideBar{

    AppiumDriver driver;

    protected EbaySideBar(AppiumDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public void selectOption(EbaySideBarItems item)
    {
        Log.info("Clicking "+item.getValue());
        driver.findElement(By.xpath("//*[@text='"+item.getValue()+"']")).click();
    }

    @FindBy(id = "com.ebay.mobile:id/textview_sign_in_status")
    WebElement signedUser;

    public String getSignedUser()
    {
        Log.info("Signed User's Username :"+signedUser.getText());
        return signedUser.getText();
    }

    @FindBy(xpath = "//*[@text='Sign out']")
    WebElement signOutButton;

    public void userSignOut()
    {
        Log.info("Clicking sign out and signing out user");
        signedUser.click();

        if(signOutButton.isDisplayed())
            signOutButton.click();
        else
            Log.error("Sign Out button is not visible");
    }
}
