package qaselenium.testcases;

import qaselenium.screens.EbayHomePage;
import qaselenium.setup.TestBase;

import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import core.services.logger.Log;

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

        homePage.enterTextInSearchBox(getValueFromTestData("SearchText"));

        homePage.clickSearchButton();

        Assert.assertTrue(getValueFromTestData("SearchText").equals(homePage.getSearchedText()),"Searched text not matching");

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