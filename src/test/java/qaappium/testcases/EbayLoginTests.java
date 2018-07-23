package qaappium.testcases;

import core.services.logger.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qaappium.mobileEnums.EbaySideBarItems;
import qaappium.mobilescreens.EbayHomePage;
import qaappium.mobilescreens.EbaySideBar;
import qaappium.mobilescreens.EbaySignInPage;
import qaappium.setup.TestBase;

public class EbayLoginTests extends TestBase
{
    EbayHomePage ebayHomePage;
    EbaySignInPage ebaySignInPage;

    @BeforeMethod
    public void initialize()
    {
        ebayHomePage = new EbayHomePage(driver);
        ebaySignInPage = new EbaySignInPage(driver);
    }

    ///Incomplete
    @Test(description = "Test to login into eBay app")
    public void eBayLoginTest()
    {
        Log.startTestCase("Test to login into eBay app");

        ebayHomePage.clickHamburgerIcon();

        ebayHomePage.selectOption(EbaySideBarItems.SignIn);

        ebaySignInPage.waitForSignPageToLoad();

        ebaySignInPage.enterUsername(getValueFromTestData("Username"));

        ebaySignInPage.enterPassword(getValueFromTestData("Password"));

        Log.endTestCase("Test to login into eBay app");
    }


}
