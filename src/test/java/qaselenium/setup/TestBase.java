package qaselenium.setup;


import core.projectdata.ProjectData;
import core.setup.Base;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import core.services.logger.Log;
import core.services.screenshot.Screenshot;

import java.util.concurrent.TimeUnit;

public class TestBase extends Base {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(@Optional("chrome") String browser) {

        initialiazeBrowser(browser, getValueFromTestData(browser+"Path")); /// Initiating Browser drivers

        Log.info("Hitting URL :"+getValueFromTestData("baseUrl"));
        driver.get(getValueFromTestData("baseUrl"));
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 60000);
    }

    public void initialiazeBrowser(String browser, String driverPath) {
        Log.info("Browser  Name = "+browser);

        switch (browser.toLowerCase())
        {
            case "firefox":
                Log.info("Setting System properties for firefox driver");
                System.setProperty("webdriver.gecko.driver", ProjectData.projectPath + driverPath);
                driver = new FirefoxDriver();
                break;
            case "chrome":
                Log.info("Setting System properties for chrome driver");
                System.setProperty("webdriver.chrome.driver", ProjectData.projectPath + driverPath);
                driver = new ChromeDriver();
                break;
            default:
                Log.info("Browser name "+browser+" incorrect, please check the browser name");
        }
        driver.manage().window().maximize();
        Log.info("Launching "+browser+" browser");
        Screenshot.TakeScreenshot(browser+" browser launched");
    }

    @AfterTest
    public void tearDown() {
        Log.info("Killing driver and closing browser");
        if (driver != null)
            driver.quit();
    }
}