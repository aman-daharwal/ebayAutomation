package qaappium.testcases;

import core.services.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;
import qaappium.mobileEnums.EbaySideBarItems;
import qaappium.mobileEnums.HomePills;
import qaappium.mobilescreens.EbayCategoriesPage;
import qaappium.mobilescreens.EbayHomePage;
import qaappium.setup.TestBase;

public class EbayHomePageTests extends TestBase {

    EbayHomePage ebayHomePage;

    @BeforeMethod
    public void initializeScreens()
    {
        ebayHomePage = new EbayHomePage(driver);
    }

    @Test(description = "Select Categories from home pills and side bar")
    public void testCategoriesSection()
    {
        Log.startTestCase("Select Categories from home pills and side bar");

        ebayHomePage.selectHomePill(HomePills.Categories);

        Assert.assertTrue(ebayHomePage.getPageTitle().equalsIgnoreCase(HomePills.Categories.getValue()),"Page Title "+ebayHomePage.getPageTitle()
                        +" does not match with "+HomePills.Categories.getValue());

        ebayHomePage.clickHamburgerIcon();

        ebayHomePage.selectOption(EbaySideBarItems.Categories);

        Assert.assertEquals(ebayHomePage.getPageTitle(),EbaySideBarItems.Categories.getValue(),"Page Title "+ebayHomePage.getPageTitle()
                +" does not match with "+EbaySideBarItems.Categories.getValue());

        Log.endTestCase("Select Categories from home pills and side bar");
    }

    @Ignore //// Test is incomplete
    @Test(description = "Test to calculate number of tiles in Category Page")
    public void calculateTilesInCategoryPage()
    {
        EbayCategoriesPage ebayCategoriesPage = new EbayCategoriesPage(driver);

        ebayHomePage.selectHomePill(HomePills.Categories);

        ebayCategoriesPage.getNumberOfTiles();
    }
}
