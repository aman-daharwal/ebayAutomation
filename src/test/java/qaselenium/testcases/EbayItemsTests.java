package qaselenium.testcases;

import core.services.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qaselenium.screens.EbayHomePage;
import qaselenium.screens.EbayTilePage;
import qaselenium.setup.TestBase;

public class EbayItemsTests extends TestBase {

    EbayHomePage ebayHomePage;
    EbayTilePage ebayTilePage;

    @BeforeTest
    public void screenInitialization()
    {
        ebayTilePage = new EbayTilePage(driver);
        ebayHomePage = new EbayHomePage(driver);
    }

    @Test(description = "Test to verify the items number shown in home page is equal to number of items inside a particular selected tile page")
    public void validateTileItems()
    {
        Log.startTestCase("Validate number of items inside a tile");

        int numberOfItems = ebayHomePage.getTileItems(getValueFromTestData("TileName"));

        ebayHomePage.clickTile(getValueFromTestData("TileName"));

        Assert.assertEquals(getValueFromTestData("TileName").trim(),ebayTilePage.getTilePageTitle().trim(),"Tile selected is "
                +getValueFromTestData("TileName")+" which is not matching with "+ebayTilePage.getTilePageTitle());

        Assert.assertEquals(numberOfItems, ebayTilePage.getNumberOfItems(),"Number of Items displayed in home page is "+numberOfItems
                                                    +" which is not equal to number of items in title page which is "+ebayTilePage.getNumberOfItems());

        Log.endTestCase("Validate number of items inside a tile");
    }
}
