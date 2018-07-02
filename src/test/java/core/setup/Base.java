package core.setup;

import core.services.logger.Log;
import core.services.screenshot.Screenshot;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import core.projectdata.ProjectData;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    protected void setLogger()
    {
        DOMConfigurator.configure("src\\test\\resources\\logger.xml");
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

    @AfterMethod
    public void tracksFailedCases(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            Log.info("Test case passed");
        }
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            Screenshot.TakeScreenshot("Test Failed");
            Log.info("Test case failed");
        }
    }
}
