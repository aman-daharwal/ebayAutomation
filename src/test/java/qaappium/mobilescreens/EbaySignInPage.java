package qaappium.mobilescreens;

import core.commonExtensions.Requirements;
import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class EbaySignInPage
{
    AppiumDriver driver;

    public EbaySignInPage(AppiumDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "com.ebay.mobile:id/title")
    WebElement signInPageTitle;

    @FindBy(xpath = "//*[contains(@resource-id,'username')]")
    WebElement usernameTextbox;

    @FindBy(xpath = "//*[contains(@resource-id,'password')]")
    WebElement passwordTextbox;

    public void enterUsername(String username)
    {
        Log.info("Entering username as "+username);
        usernameTextbox.sendKeys(username);
    }

    public void enterPassword(String password)
    {
        Log.info("Entering Password *********");
        passwordTextbox.sendKeys(password);
    }

    public String getSignInPageTitle()
    {
        Log.info("Waiting for page to load...");
        Assert.assertEquals(signInPageTitle.getText().trim(),"Sign in", "" +
                    "Sign In page title mismatch");
        return signInPageTitle.getText();
    }

    @FindBy(xpath = "//*[@text='SIGN IN']")
    WebElement signInButton;

    public void clickSignInButton()
    {
        Log.info("clicking Sign In button");

        if(signInButton.isEnabled())
            signInButton.click();
        else
            Log.error("Sign In button is not enabled, Unable to Sign In," +
                    "please enter username and password field first");
    }
}
