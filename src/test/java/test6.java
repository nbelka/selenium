import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import java.util.*;

public class test6 {
    private WebDriver driverChrome;
    ArrayList<String> arrlist;
    ArrayList<String> arrlistSrted;

    String Admin_Page_URL = "http://localhost:4433/litecart/admin/";
    String Countries_Page_URL = "http://localhost:4433/litecart/admin/?app=countries&doc=countries";
    String Zones_Page_URL = "http://localhost:4433/litecart/admin/?app=geo_zones&doc=geo_zones";
    String Admin_Username = "admin";
    String Password_Username = "admin";

    By Countries = By.cssSelector("[class='row']");
    By Zones = By.cssSelector("td:nth-child(3) > select");
    By Name = By.name("username");
    By Password = By.name("password");
    By Login = By.name("login");

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    public void MyFirstTest(){
        driverChrome.get(Admin_Page_URL);

        driverChrome.findElement(Name).sendKeys(Admin_Username);
        driverChrome.findElement(Password).sendKeys(Password_Username);
        driverChrome.findElement(Login).click();

        driverChrome.get(Countries_Page_URL);

        int countOfCountryes = driverChrome.findElements(Countries).size();

        Assert.assertTrue(countOfCountryes > 1);
        if (countOfCountryes > 1){
                arrlist = new ArrayList<String>(5);
        }

        for (int i = 2; i <= countOfCountryes+1; i++){
            arrlist.add(driverChrome.findElement(By.cssSelector(".row:nth-child(" + i + ") > td:nth-child(5)")).getText());
        }

        arrlistSrted  = new ArrayList<String>(arrlist);
        Collections.sort(arrlistSrted);
        Assert.assertTrue((arrlist.equals(arrlistSrted)));
        for (int i = 2; i <= countOfCountryes+1; i++){
            int CountTimezonesOfCountry = Integer.parseInt(driverChrome.findElement(By.cssSelector(".row:nth-child(" + i + ") > td:nth-child(6)")).getText());
            if (CountTimezonesOfCountry > 1){
                driverChrome.findElement(By.cssSelector(".row:nth-child(" + i + ") > td:nth-child(5)")).click();
                ArrayList<String> timeZonesOfCountry = new ArrayList<String>(CountTimezonesOfCountry);
                for (int j = 2; j <= CountTimezonesOfCountry+1; j++){
                    timeZonesOfCountry.add(driverChrome.findElement(By.cssSelector(".row:nth-child(" + j + ") > td:nth-child(5)")).getText());
                }
                ArrayList<String> timeZonesOfCountrySorted = new ArrayList<String>(timeZonesOfCountry);
                Collections.sort(timeZonesOfCountrySorted);
                Assert.assertTrue((timeZonesOfCountry.equals(timeZonesOfCountrySorted)));
                driverChrome.get(Countries_Page_URL);
            }
        }
    }

    @Test
    public void geo_zones(){
        driverChrome.get(Admin_Page_URL);
        driverChrome.get(Zones_Page_URL);

        int countOfGeoZones = driverChrome.findElements(Countries).size();
        Assert.assertTrue(countOfGeoZones > 0);

        for (int i = 2; i <= countOfGeoZones+1; i++){
            driverChrome.findElement(By.cssSelector(".row:nth-child(" + i + ") > td:nth-child(3) > a")).click();
            int zones = driverChrome.findElements(Zones).size();
            ArrayList<String> ListOfZones = new ArrayList<String>(zones);
            for (int j = 2; j <= zones; j++){
                ListOfZones.add(new Select(driverChrome.findElement(By.cssSelector("tr:nth-child(" + j + ") > td:nth-child(3) > select"))).getFirstSelectedOption().getText());
            }
            ArrayList<String> ListOfZonesSorted = new ArrayList<String>(ListOfZones);
            Collections.sort(ListOfZonesSorted);
            Assert.assertTrue((ListOfZones.equals(ListOfZonesSorted)));
            driverChrome.get(Zones_Page_URL);
        }
    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }
}
