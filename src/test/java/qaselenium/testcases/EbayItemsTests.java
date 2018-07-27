package qaselenium.testcases;

import core.services.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qaselenium.screens.*;
import qaselenium.setup.TestBase;

public class EbayItemsTests extends TestBase {

    EbayHomePage ebayHomePage;
    EbayTilePage ebayTilePage;
    EbayItemPage ebayItemPage;
    EbayReviewOrderPage ebayReviewOrderPage;
    EbaySignInPage ebaySignInPage;

    @BeforeTest
    public void screenInitialization()
    {
        ebayTilePage = new EbayTilePage(driver);
        ebayHomePage = new EbayHomePage(driver);
        ebayItemPage = new EbayItemPage(driver);
        ebayReviewOrderPage = new EbayReviewOrderPage(driver);
        ebaySignInPage = new EbaySignInPage(driver);
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

    @Test(description = "Test to add and remove a particular item to cart")
    public void addItemToCart()
    {
        Log.startTestCase("Test to add and remove item from cart");

        ebayHomePage.eBaySignIn();

        ebaySignInPage.enterUsername(getValueFromTestData("Username"));

        ebaySignInPage.enterPassword(getValueFromTestData("Password"));

        ebaySignInPage.clickSignInButton();

        ebayHomePage.enterTextInSearchBox(getValueFromTestData("SearchText"));

        ebayHomePage.clickSearchButton();

        Assert.assertEquals(ebayHomePage.getSearchedText().trim(),getValueFromTestData("SearchText"),"Searched Text "+getValueFromTestData("SearchText")+" doesn't matched with page " +
                "title "+ebayHomePage.getSearchedText());

        ebayHomePage.selectFromSearchedItem(getValueFromTestData("ItemToPurchase"));

        String itemName = ebayItemPage.getItemName();

        Assert.assertTrue(itemName.contains(getValueFromTestData("ItemToPurchase")), "Items Page title doesn't matched" +
                " with Title "+ebayItemPage.getItemName());

        ebayItemPage.clickBuyItNowButton();

        Assert.assertTrue(ebayReviewOrderPage.getItemsSelectedToPurchase().get(0).contains(getValueFromTestData("ItemToPurchase")), "Item in Review Order Page" +
                " doesn't match with ");

        ebayReviewOrderPage.removeItem(itemName);
    }
}
