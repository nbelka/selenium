import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.Assert;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.util.List;


public class test5 {
    private WebDriver driverChrome;

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    public void test(){
        driverChrome.get("http://localhost:4433/litecart");
        List<WebElement> listOfProducts = driverChrome.findElements(By.xpath("//li[contains(@class, 'product column')]"));
        for (WebElement i : listOfProducts){
            Assert.assertTrue(i.findElements(By.cssSelector("div.sticker")).size() > 0);
        }

    }

    @AfterTest
    public void stop()
    {
        driverChrome.quit();
        driverChrome = null;
    }
}
