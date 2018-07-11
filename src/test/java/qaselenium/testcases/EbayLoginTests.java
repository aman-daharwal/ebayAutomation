package qaselenium.testcases;

import core.services.logger.Log;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import qaselenium.screens.EbayHomePage;
import qaselenium.screens.EbaySignInPage;
import qaselenium.setup.TestBase;

public class EbayLoginTests extends TestBase
{
    EbayHomePage ebayHomePage;
    EbaySignInPage ebaySignInPage;

    @BeforeMethod
    public void initialization()
    {
        ebayHomePage = new EbayHomePage(driver);
        ebaySignInPage = new EbaySignInPage(driver);
    }

    @Test(description = "Test the functionality of login into ebay")
    public void LoginInToEbay()
    {
        Log.startTestCase("Login in to ebay");

        ebayHomePage.eBaySignIn();

        ebaySignInPage.enterUsername(getValueFromTestData("Username"));

        ebaySignInPage.enterPassword(getValueFromTestData("Password"));

        ebaySignInPage.clickSignInButton();

        ebayHomePage.eBaySignOut();

        Log.endTestCase("Login in to ebay");
    }
}
