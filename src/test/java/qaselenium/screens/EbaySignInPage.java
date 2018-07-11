package qaselenium.screens;

import core.services.logger.Log;
import core.services.screenshot.Screenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbaySignInPage
{
    WebDriver driver;

    public EbaySignInPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id='userid']")
    WebElement usernameTextBox;

    @FindBy(xpath = "//*[@id='pass']")
    WebElement passwordTextBox;

    public void enterUsername(String username)
    {
        if(isSignInTabSelected())
            selectSignInTab();

        Log.info("Entering username as "+username);
        usernameTextBox.sendKeys(username);
    }

    public void enterPassword(String password)
    {
        Log.info("Entering password *********");
        passwordTextBox.sendKeys(password);
    }

    @FindBy(xpath = "//*[@id='sgnTab']")
    WebElement signInTab;

    public boolean isSignInTabSelected()
    {
        return signInTab.isSelected();
    }

    public void selectSignInTab()
    {
        Log.info("Selecting SignIn Tab");
        signInTab.click();
    }

    @FindBy(xpath = "//*[@id='sgnBt']")
    WebElement signInButton;

    public void clickSignInButton()
    {
        Log.info("Clicking Sign In button");
        Screenshot.TakeScreenshot("Before Clicking SignIn");
        signInButton.click();
    }
}
