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

        Properties properties = getTestDataPropertiesFile();
        initialiazeBrowser(browser, properties); /// Initiating Browser drivers

        driver.manage().window().maximize();
        driver.get(properties.getProperty("baseUrl"));
        driver.manage().timeouts().implicitlyWait(10000, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().pageLoadTimeout(50000, TimeUnit.MILLISECONDS);
        wait = new WebDriverWait(driver, 60000);
    }

    public Properties getTestDataPropertiesFile() {
        File testDatafile = new File(new File("").getAbsolutePath() + "//resources//TestData//TestData.properties");

        testDatafile.mkdirs();

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
        return prop;
    }

    public void initialiazeBrowser(String browser, Properties prop) {
        if (browser.equalsIgnoreCase("firefox")) {

            System.setProperty("webdriver.gecko.driver", ProjectData.testDataFile + prop.getProperty("firefoxPath"));

            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", ProjectData.testDataFile + prop.getProperty("chromePath"));
            driver = new ChromeDriver();
        }
    }

    @AfterTest
    public void tearDown() {
        if (driver != null)
            driver.quit();
    }
}