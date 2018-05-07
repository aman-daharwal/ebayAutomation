package commons;


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

        initialiazeBrowser(browser, getValueFromTestData(browser+"Path")); /// Initiating Browser drivers

        driver.manage().window().maximize();
        driver.get(getValueFromTestData("baseUrl"));
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 60000);
    }

    public String getValueFromTestData(String key) {
        File testDatafile = new File(ProjectData.testDataFile);
        testDatafile.mkdirs();

        System.out.println("Test Data filepath = "+testDatafile.getAbsolutePath());

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
        System.out.println("Browser  Name = "+browser);
        if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", ProjectData.projectPath + driverPath);
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ProjectData.projectPath + driverPath);
            driver = new ChromeDriver();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}