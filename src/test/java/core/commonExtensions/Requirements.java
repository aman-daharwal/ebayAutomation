package core.commonExtensions;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class Requirements {

    public static void waitInSeconds(int seconds) {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException ieException) {
            Log.error("Exception in waiting for " + seconds + " seconds \n" + ieException.getLocalizedMessage());
        }
    }

    public static void waitForScreenToLoad(AppiumDriver driver,String pageTitle, int seconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.visibilityOfElementLocated(getElement(pageTitle)));
    }

    public static By getElement(String elementText)
    {
        return By.xpath("//*[@text='"+elementText+"']");
    }
}

