package qaappium.extensions;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;

import java.util.HashMap;
import java.util.WeakHashMap;

public class ScreenActions {

    static AppiumDriver driver;

    public ScreenActions(AppiumDriver driver)
    {
        this.driver = driver;
    }

    public static void swipe(AppiumDriver driver ,WebElement element)
    {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", "down");
        scrollObject.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: scroll", scrollObject);
    }
}
