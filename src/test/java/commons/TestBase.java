package commons;


import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static WebDriver driver;
    public static WebDriverWait wait;

    @Parameters({"browser"})
    @BeforeTest
    public void setup(String browser) {

        DOMConfigurator.configure("src\\test\\resources\\logger.xml");

        initialiazeBrowser(browser, getValueFromTestData(browser+"Path")); /// Initiating Browser drivers

        driver.manage().window().maximize();

        Log.info("Hitting URL :"+getValueFromTestData("baseUrl"));
        driver.get(getValueFromTestData("baseUrl"));
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 60000);
    }

    public String getValueFromTestData(String key) {
        Log.info("Getting Value from Test Data file for Key "+key);
        File testDatafile = new File(ProjectData.testDataFile);
        testDatafile.mkdirs();

        Log.debug("Test Data filepath = "+testDatafile.getAbsolutePath());

        FileInputStream fileInput = null;
        try {
            fileInput = new FileInputStream(testDatafile);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();

        // load properties file
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop.getProperty(key);
    }

    public void initialiazeBrowser(String browser, String driverPath) {
        Log.info("Browser  Name = "+browser);
        if (browser.equalsIgnoreCase("firefox")) {
            Log.info("Setting System properties for firefox driver");
            System.setProperty("webdriver.gecko.driver", ProjectData.projectPath + driverPath);
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            Log.info("Setting System properties for chrome driver");
            System.setProperty("webdriver.chrome.driver", ProjectData.projectPath + driverPath);
            driver = new ChromeDriver();
        }
        Log.info("Launching "+browser+" browser");
    }

    @AfterTest
    public void tearDown() {
        Log.info("Killing driver and closing browser");
        if (driver != null)
            driver.quit();
    }
}