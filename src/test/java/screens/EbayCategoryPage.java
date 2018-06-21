package screens;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class EbayCategoryPage extends EbayPageCommons{

    WebDriver driver;

    public EbayCategoryPage(WebDriver driver)
    {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}
