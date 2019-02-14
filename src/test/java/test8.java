import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class test8 {

    private WebDriver driverChrome;

    private String StartPage = "http://localhost:4433/litecart";

    private By RegisterLink = By.cssSelector("td > a");

    private By TaxID = By.cssSelector("tr:nth-child(1) > td:nth-child(1) > input");
    private By Company = By.cssSelector("tr:nth-child(1) > td:nth-child(2) > input");
    private By FirstName = By.cssSelector("tr:nth-child(2) > td:nth-child(1) > input");
    private By LastName = By.cssSelector("tr:nth-child(2) > td:nth-child(2) > input");
    private By Adress1 = By.cssSelector("tr:nth-child(3) > td:nth-child(1) > input");
    private By Adress2 = By.cssSelector("tr:nth-child(3) > td:nth-child(2) > input");
    private By Postcode = By.cssSelector("tr:nth-child(4) > td:nth-child(1) > input");
    private By City = By.cssSelector("tr:nth-child(4) > td:nth-child(2) > input");
    private By CountrySelector = By.cssSelector("#create-account > div > form > table > tbody > tr:nth-child(5) > td:nth-child(1) > select");
    private By Email = By.cssSelector("tr:nth-child(6) > td:nth-child(1) > input");
    private By Phone = By.cssSelector("tr:nth-child(6) > td:nth-child(2) > input");
    private By Password = By.cssSelector("tr:nth-child(8) > td:nth-child(1) > input");
    private By ConfirmPassword = By.cssSelector("tr:nth-child(8) > td:nth-child(2) > input");
    private By CreateAccountButton = By.cssSelector("td > button");
    private By Logout = By.cssSelector("#box-account li:nth-child(4) a");

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    public void registrations(){
        driverChrome.get(StartPage);
        driverChrome.findElement(RegisterLink).click();

        driverChrome.findElement(TaxID).sendKeys("666");
        driverChrome.findElement(Company).sendKeys("example-company");
        driverChrome.findElement(FirstName).sendKeys("punterName");
        driverChrome.findElement(LastName).sendKeys("LastName");
        driverChrome.findElement(Adress1).sendKeys("Adress1");
        driverChrome.findElement(Adress2).sendKeys("Adress2");
        driverChrome.findElement(Postcode).sendKeys("81766");
        driverChrome.findElement(City).sendKeys("City");
        (new Select(driverChrome.findElement(CountrySelector))).selectByVisibleText("United States");
        driverChrome.findElement(Email).sendKeys("ema111il11@email.com");
        driverChrome.findElement(Phone).sendKeys("+79999999999");
        driverChrome.findElement(Password).sendKeys("12345678901");
        driverChrome.findElement(ConfirmPassword).sendKeys("12345678901");
        driverChrome.findElement(CreateAccountButton).click();
        driverChrome.findElement(Logout).click();
    }

    @Test
    public void Login(){
        driverChrome.findElement(By.cssSelector("#box-account-login td [name=email")).sendKeys("ema111il11@email.com");
        driverChrome.findElement(By.cssSelector("#box-account-login td [name=password]")).sendKeys("12345678901");
        driverChrome.findElement(By.cssSelector("#box-account-login td [name=login]")).click();
        driverChrome.findElement(By.cssSelector("#box-account li:nth-child(4) a")).click();
    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }

}
