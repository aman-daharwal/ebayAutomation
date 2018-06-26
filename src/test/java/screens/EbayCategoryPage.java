package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class EbayCategoryPage extends EbayPageCommons{

    WebDriver driver;

    public EbayCategoryPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//*[@id='mainTitle']")
    WebElement categoryTitle;

    public String getCategoryTitle() {
        return categoryTitle.getText();
    }

    @FindBy(xpath = "//*[@class='cat-st']")
    WebElement subCategoryTitle;

    public String getSubCategoryTitle()
    {
        return subCategoryTitle.getText();
    }
}
