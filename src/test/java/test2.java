import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;


public class test2 {
    private WebDriver driverFirefox;
    private WebDriver driverChrome;
    private WebDriver driverIE;

    @BeforeTest
    public void start(){
        driverFirefox  = new FirefoxDriver();
        driverChrome = new ChromeDriver();
        driverIE = new InternetExplorerDriver();
    }

    @Test
    public void MyFirstTestFirefox(){
        driverFirefox.get("http://localhost:4433/litecart/admin/");
        driverFirefox.findElement(By.name("username")).sendKeys("admin");
        driverFirefox.findElement(By.name("password")).sendKeys("admin");
        driverFirefox.findElement(By.name("login")).click();
    }

    @Test
    public void MyFirstTestChrome(){
        driverChrome.get("http://localhost:4433/litecart/admin/");
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.name("login")).click();
    }

    @Test
    public void MyFirstTestIE(){
        driverIE.get("http://localhost:4433/litecart/admin/");
        driverIE.findElement(By.name("username")).sendKeys("admin");
        driverIE.findElement(By.name("password")).sendKeys("admin");
        driverIE.findElement(By.name("login")).click();
    }

    @AfterTest
    public void stop(){
        driverFirefox.quit();
        driverChrome.quit();
        driverIE.quit();
        driverFirefox = null;
        driverChrome = null;
        driverIE = null;
    }
}
