package testcases;

import com.sun.org.glassfish.gmbal.Description;
import commons.TestBase;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.EbayHomePage;
import services.logger.Log;

public class EbayItemCategoryTests extends TestBase {

    EbayHomePage homePage;

    @BeforeTest
    public void screenInitialization() {

        homePage = new EbayHomePage(driver);
    }

    @Test(description = "Test to select a option from Home and Living Category")
    public void selectOptionFromHomeAndLivingCategory()
    {
        Log.startTestCase("Select Option From Home And Living Category");

        homePage.clickShopbyCategoryButton();

        homePage.selectCategory("Home and Living");
    }
}
