package testcases;

import com.sun.org.glassfish.gmbal.Description;
import commons.TestBase;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import screens.EbayCategoryPage;
import screens.EbayHomePage;
import services.logger.Log;

public class EbayItemCategoryTests extends TestBase {

    EbayHomePage homePage;
    EbayCategoryPage categoryPage;

    @BeforeTest
    public void screenInitialization() {

        homePage = new EbayHomePage(driver);
        categoryPage = new EbayCategoryPage(driver);
    }

    @Test(description = "Test to select a option from Home and Living Category")
    public void selectOptionFromHomeAndLivingCategory()
    {
        Log.startTestCase("Select Option From Home And Living Category");

        homePage.clickShopbyCategoryButton();

        homePage.selectCategory(getValueFromTestData("Category"));

        Assert.assertEquals(categoryPage.getCategoryTitle().trim(),getValueFromTestData("Category").trim(),"Category select mismatch, getting "
                +categoryPage.getCategoryTitle()+" ,instead of "+getValueFromTestData("Category"));
    }
}
