package testcases;

import logger.Log;
import commons.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.EbayHomePage;

public class SampleTests extends TestBase
{
    EbayHomePage homePage;

    @BeforeTest
    public void initialization() {
       homePage = new EbayHomePage(driver);
    }

    @Test
    public void test1()
    {
        Log.startTestCase("Test 1");

        homePage.enterTextInSearchBox("iPhone");

        homePage.clickSearchButton();

        Log.endTestCase("End");
    }
}