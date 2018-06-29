package qaappium.setup;

import core.setup.Base;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.UnreachableBrowserException;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import core.services.logger.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class TestBase extends Base
{
    DesiredCapabilities capabilities;
    protected AppiumDriver driver;

    private String getDeviceId(){ //To get the device Id which is unique for all
        String deviceId=null;
        String lineIndex;
        String cmdreturn = "";
        //making object of Runtime
        Runtime run = Runtime.getRuntime();
        try{
            Process pr = run.exec("cmd.exe /c adb devices");
            pr.waitFor();
            //To read the adb devices command's output from cmd
            BufferedReader cmdout = new BufferedReader(new InputStreamReader(pr.getInputStream()));
            ArrayList<String> cmdline = new ArrayList<String>();
            while ((cmdreturn=cmdout.readLine())!=null) {
                cmdline.add(cmdreturn);
            }
            lineIndex=cmdline.get(1);
            deviceId =lineIndex.substring(lineIndex.indexOf(""));
            deviceId = deviceId.substring(0,deviceId.indexOf("device"));
            Log.info("device Id has found successfully as "+deviceId);
        }catch(IOException ioException){
            Log.error(ioException.getMessage());
        } catch (InterruptedException intExcep) {
            Log.error(intExcep.getMessage());
        }
        return deviceId.trim();
    }

    private void setCapabilities(){ //setting all the capabilities for android
        //making object of DesiredCapabilities
        capabilities = new DesiredCapabilities();
        //setting capabilities
        capabilities.setCapability("udid",getDeviceId());
        capabilities.setCapability("deviceName", "LS-4005");
        capabilities.setCapability("device", "Android");
        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("appPackage","com.example.tanujapatel.myapplication");
        capabilities.setCapability("appActivity","com.example.tanujapatel.myapplication.LoginActivity");
        capabilities.setCapability("app","C:/mnaman/FoodZillaAutomation/app-debug.apk");
        capabilities.setCapability("newCommandTimeOut","40");
        capabilities.setCapability("autoWebviewTimeout","6000");
    }

    //To start appium server with the help of cmd using appium command directly
    private void startAppiumServer(){
        Runtime runTime = Runtime.getRuntime();
        try{
            runTime.exec(new String[]{"cmd.exe","/c start appium -p 4724"});
            Thread.sleep(20000);
            Log.info("appium server started successfully");
        }catch(IOException ioException){
            Log.error(ioException.getMessage());
        }catch (InterruptedException intExcep) {
            Log.error(intExcep.getMessage());;
        }
    }

    //To create a new appium session
    private void setAndroidDriver(){
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
            System.out.println(interrutedExcep.getMessage());
        }
        catch (UnreachableBrowserException unreachableBrowserExcep) {
            Log.error(unreachableBrowserExcep.getMessage());
        }
    }

    //To start the appium server and session before any test
    @BeforeTest
    public void setup(){
        setLogger();

        startAppiumServer();//starts appium server
        setAndroidDriver();//starts appium driver session
    }

    //To make expire appium session and close the application
    @AfterTest
    public void tearDown()
    {
        Log.info("Killing driver and closing application");
        driver.quit();
    }
}
