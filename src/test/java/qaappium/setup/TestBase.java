package qaappium.setup;

import core.commonExtensions.Requirements;
import core.projectdata.ProjectData;
import core.setup.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import org.testng.annotations.*;
import core.services.logger.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class TestBase extends Base
{
    protected DesiredCapabilities capabilities;
    protected AppiumDriver driver;

    private String getDeviceId(){ //To get the device Id which is unique for all
        String deviceId=null;
        String lineIndex;
        String cmdreturn = "";
        //making object of Runtime
        Runtime run = Runtime.getRuntime();
        try
        {
            Process pr = run.exec("cmd.exe /c adb devices");
            pr.waitFor();
            //To read the adb devices command's output from cmd
            BufferedReader cmdout = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            ArrayList<String> cmdline = new ArrayList<String>();
            while ((cmdreturn=cmdout.readLine())!=null) {
                cmdline.add(cmdreturn);
            }
            lineIndex = cmdline.get(1);
            deviceId = lineIndex.substring(lineIndex.indexOf(""));
            deviceId = deviceId.substring(0,deviceId.indexOf("device"));
            Log.info("device Id has found successfully as "+deviceId);
        }
        catch(IOException ioException){
            Log.error("Exception in getting UDID of device, make sure device is attached properly \n"+ioException.getMessage());
        }
        catch (InterruptedException intExcep) {
            Log.error("Exception in getting UDID of device, make sure device is attached properly \n"+intExcep.getMessage());
        }
        catch (Exception exp)
        {
            Log.error("Exception in getting UDID of device, make sure device is attached properly \n"+exp.getMessage());
        }
        return deviceId.trim();
    }

    private void setCapabilities(){ //setting all the capabilities for android

        File app = new File(ProjectData.ebayApkFile);

        Log.info("Initializing and setting capabilities");
        //making object of DesiredCapabilities
        capabilities = new DesiredCapabilities();
        //setting capabilities
        capabilities.setCapability("udid", getDeviceId());
        capabilities.setCapability("deviceName", "Nexus 9");
        capabilities.setCapability("platformVersion", "7.1.1");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage","com.ebay.mobile");
        capabilities.setCapability("appActivity","com.ebay.mobile.activities.MainActivity");
        capabilities.setCapability("app",app.getAbsolutePath());
        capabilities.setCapability("newCommandTimeOut","40");
        capabilities.setCapability("autoWebviewTimeout","6000");
    }

    //To start appium server with the help of cmd using appium command directly
    private void startAppiumServer()
    {
        Log.info("Trying to start appium server");
       // Runtime runTime = Runtime.getRuntime();
        try{
            runtime.exec(new String[]{"cmd.exe","/c start appium -p 4724"});
            Thread.sleep(20000);
            Log.info("appium server started successfully");
        }catch(IOException ioException){
            Log.error(ioException.getMessage());
        }catch (InterruptedException intExcep) {
            Log.error(intExcep.getMessage());;
        }
    }

    //To create a new appium session
    private void setAndroidDriver()
    {
        Log.info("Initializing driver as Android driver");
        try{
            setCapabilities();
            driver = new AndroidDriver<WebElement>(new URL("http://0.0.0.0:4724/wd/hub"), capabilities);
            Thread.sleep(1000);
            // driver.resetApp();//Clear cache and app data
            driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
            Log.info("driver has created successfully");
        }
        catch(IOException ioExcep){
            Log.error(ioExcep.getMessage());
        }
        catch(InterruptedException interrutedExcep){
            Log.error(interrutedExcep.getMessage());
        }
        catch (UnreachableBrowserException unreachableBrowserExcep) {
            Log.error(unreachableBrowserExcep.getMessage());
        }
    }

    //To start the appium server and session before any test
    @BeforeSuite
    public void setup(){

        startAppiumServer();//starts appium server
    }

    @BeforeMethod
    public void launchApplication()
    {
        Log.info("Launching Application on Android device");
        setAndroidDriver();//starts appium driver session

        Requirements.waitInSeconds(8); // as page takes few seconds to load after launch
    }

    //To make expire appium session and close the application
    @AfterMethod
    public void tearDown() {
        Log.info("Killing driver and closing application");
        driver.quit();
    }

    @AfterSuite
    public void killServer()
    {
        try {
            Log.info("Killing Appium server");
            Scanner outputScanner = new Scanner(runtime.exec(new String[]{"cmd.exe", "/c taskkill /F /IM node.exe"}).getInputStream()).useDelimiter("\\A");
            String outputResponse = outputScanner.hasNext() ? outputScanner.next() : "";

            if(outputResponse == null || outputResponse == "")
                Log.error("Output Response is null");

            Log.info("Server Killing Response : "+outputResponse);
            if(outputResponse.contains("SUCCESS"))
            {
                Log.info("Appium Server Killed successfully");
            }
            else if(outputResponse.contains("FAILURE") || outputResponse.contains("ERROR"))
            {
                Log.error("Error/Failure killing Appium server with response "+outputResponse);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
