package testcases;

import logger.Log;
import commons.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.EbayHomePage;

import static java.lang.Short.*;

public class EbayHomeScreenTests extends TestBase
{
    EbayHomePage homePage;

    @BeforeTest
    public void screenInitialization() {
       homePage = new EbayHomePage(driver);
    }

    @Test(description = "Test to search a keyword through Search box and to verify the functionality of search button")
    public void SearchKeywordInSearchBox()
    {
        Log.startTestCase("Search Keyword In SearchBox of ebay");

        homePage.enterTextInSearchBox("iPhone");

        homePage.clickSearchButton();

        Assert.assertTrue("iPhone".equals(homePage.getSearchedText()),"Searched text not matching");

        Log.endTestCase("Search Keyword In SearchBox of ebay");
    }

    @Test(description = "Test to calculate the number of tiles in eBay Home Page")
    public void CalculateTilesInHomePage()
    {
        Log.startTestCase("Calculate Tiles Inside HomePage");

        short valueFromHomePage = homePage.GetNumberOfTiles();

        short valueFromTestData = valueOf(getValueFromTestData("NumberOfTiles"));

        Assert.assertEquals(valueFromHomePage,valueFromTestData,"Number of tiles from page is "+valueFromHomePage+" " +
                                            ",which is not equal to "+valueFromTestData+" tiles as given in test data");

        Log.endTestCase("Calculate Tiles Inside HomePage");
    }
}