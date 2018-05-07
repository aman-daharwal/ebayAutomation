package testcases;

import commons.TestBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTests extends TestBase
{
    @Test
    public void test1()
    {
        //WebElement element = driver.findElement(By.xpath("//input[@name='q']"));
        WebElement element = driver.findElement(By.xpath("//*[@id='gh-ac']"));

        element.sendKeys("iphone");

        WebElement elemnet2 = driver.findElement(By.xpath("//*[@id='gh-btn']"));
        elemnet2.submit();
    }
}