
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.File;


public class test3 {
    private WebDriver driver;

    @BeforeTest
    public void start(){
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(FirefoxDriver.MARIONETTE, false);
        FirefoxBinary b = new FirefoxBinary(new File("C:\\Program Files\\Mozilla Firefox_45\\firefox.exe"));
        //driver = new FirefoxDriver( b , new FirefoxProfile(), capabilities);
    }

    @Test
    public void MyFirstTest(){
        driver.get("http://localhost:4433/litecart/admin/");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();

    }

    @AfterTest
    public void stop(){
        driver.quit();
        driver = null;
    }
}
