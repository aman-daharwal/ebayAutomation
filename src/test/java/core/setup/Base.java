package core.setup;

import core.services.logger.Log;
import core.services.screenshot.Screenshot;
import org.apache.log4j.xml.DOMConfigurator;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import core.projectdata.ProjectData;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Base {

    protected Runtime runtime = Runtime.getRuntime();

    @BeforeSuite
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
    protected void tracksFailedCases(ITestResult result) throws IOException {

        if (result.getStatus() == ITestResult.SUCCESS) {
            Log.info("Test case passed");
        }
        if (result.getStatus() == ITestResult.FAILURE || result.getStatus() == ITestResult.SKIP) {
            Screenshot.TakeScreenshot("Test Failed");
            Log.info("Test case failed");
        }
    }

    @AfterSuite
    protected void releaseMemory()
    {
        long freeMemory = runtime.freeMemory();
        long totalMemory = runtime.totalMemory();

        Log.warn("Memory in Use : " +(totalMemory - freeMemory));
        Log.warn("Free memory available : "+freeMemory);

        Log.fatal("Running Garbage collector to release memory");
        // run the garbage collector, then check freeMemory
        runtime.gc();

        freeMemory = runtime.freeMemory();
        totalMemory = runtime.totalMemory();
        Log.warn("Memory in Use after running garbage collector : "+(totalMemory - freeMemory));
        Log.warn("Free memory after running garbage collector : "+freeMemory);
    }
}
