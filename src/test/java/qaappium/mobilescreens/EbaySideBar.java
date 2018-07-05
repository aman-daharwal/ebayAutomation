package qaappium.mobilescreens;

import core.services.logger.Log;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
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
        driver.findElement(By.xpath("//*[text(),'"+item.getValue()+"']"));
    }
}
