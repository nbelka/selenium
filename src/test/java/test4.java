import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.util.List;


public class test4 {
    private WebDriver driverFirefox;
    private WebDriver driverChrome;
    private WebDriver driverIE;

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    public void test(){
        driverChrome.get("http://localhost:4433/litecart/admin/");
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.name("login")).click();
        WebElement menu = driverChrome.findElement(By.xpath("//*[@id=\"box-apps-menu\"]"));
        List<WebElement> menuItems = menu.findElements(By.cssSelector("li"));
        for (int i = 1; i <= menuItems.size(); i++){
            driverChrome.findElement(By.cssSelector("#app-:nth-child(" + i + ")")).click();
            Assert.assertTrue(driverChrome.findElements(By.cssSelector("#content h1")).size() > 0);
            int lenOfTempMenu = driverChrome.findElement(By.cssSelector("#app-:nth-child(" + i + ")")).findElements(By.cssSelector("[id^='doc-']")).size();
            if (lenOfTempMenu > 0){
                for (int j = 1; j <= lenOfTempMenu; j++){
                    driverChrome.findElement(By.cssSelector("[class = 'docs'] :nth-child(" + j + ")")).click();
                    Assert.assertTrue(driverChrome.findElements(By.cssSelector("#content h1")).size() > 0);
                }
            }
        }
    }

    @AfterTest
    public void stop()
    {
        driverChrome.quit();
        driverChrome = null;
    }
}