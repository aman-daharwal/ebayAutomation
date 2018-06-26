package extensions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class UIScreenActions {

    private static WebDriver driver;

    private static Actions action;

    public UIScreenActions(WebDriver driver1)
    {
        driver = driver1;
        action = new Actions(driver);
    }

    public static void clickAndHold(WebElement element)
    {
        action.clickAndHold(element).build().perform();
    }

    public static void click(WebElement element)
    {
        element.click();
    }

    public static void mouseHoverOverElement(WebElement element)
    {
        action.moveToElement(element).build().perform();
    }
}
