package qaappium.testcases;

import core.services.logger.Log;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import qaappium.mobileEnums.EbaySideBarItems;
import qaappium.mobileEnums.HomePills;
import qaappium.mobilescreens.EbayHomePage;
import qaappium.setup.TestBase;

public class SampleTests extends TestBase {

    EbayHomePage ebayHomePage;

    @BeforeTest
    public void initializeScreens()
    {
        ebayHomePage = new EbayHomePage(driver);
    }

    @Test
    public void sample1() throws InterruptedException {
        Log.startTestCase("Sample test");

        ebayHomePage.selectHomePill(HomePills.Categories);

        Assert.assertTrue(ebayHomePage.getPageTitle().equalsIgnoreCase(HomePills.Categories.getValue()),"Page Title "+ebayHomePage.getPageTitle()
                        +" does not match with "+HomePills.Categories.getValue());

        ebayHomePage.clickHamburgerIcon();

        ebayHomePage.selectOption(EbaySideBarItems.Categories);

        Assert.assertEquals(ebayHomePage.getPageTitle(),EbaySideBarItems.Categories.getValue(),"Page Title "+ebayHomePage.getPageTitle()
                +" does not match with "+EbaySideBarItems.Categories.getValue());
    }
}
