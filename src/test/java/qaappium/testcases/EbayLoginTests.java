package qaappium.testcases;

import core.commonExtensions.Requirements;
import core.services.logger.Log;
import org.testng.Assert;
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

        Requirements.waitForScreenToLoad(driver,ebaySignInPage.getSignInPageTitle(),6);

        ebaySignInPage.enterUsername(getValueFromTestData("Username"));

        ebaySignInPage.enterPassword(getValueFromTestData("Password"));

        ebaySignInPage.clickSignInButton();

        Requirements.waitInSeconds(5);

        ebayHomePage.clickHamburgerIcon();

        Assert.assertTrue(ebayHomePage.getSignedUser().contains(getValueFromTestData("User")),"Signed User" +
                " "+ebayHomePage.getSignedUser()+" doesn't match with User "+getValueFromTestData("User"));

        ebayHomePage.userSignOut();

        Log.endTestCase("Test to login into eBay app");
    }
}
