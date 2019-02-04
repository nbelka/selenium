import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.ArrayList;
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
    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    public void MyFirstTest(){
        driverChrome.get("http://localhost:4433/litecart/admin/");
        driverChrome.findElement(By.name("username")).sendKeys("admin");
        driverChrome.findElement(By.name("password")).sendKeys("admin");
        driverChrome.findElement(By.name("login")).click();
        driverChrome.get("http://localhost:4433/litecart/admin/?app=countries&doc=countries");
        int countOfCountryes = driverChrome.findElements(By.cssSelector("[class='row']")).size();
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
                driverChrome.get("http://localhost:4433/litecart/admin/?app=countries&doc=countries");
            }
        }
    }

    @Test
    public void geo_zones(){

        driverChrome.get("http://localhost:4433/litecart/admin/?app=geo_zones&doc=geo_zones");
        int countOfGeoZones = driverChrome.findElements(By.cssSelector("[class='row']")).size();
        for (int i = 2; i <= countOfGeoZones+1; i++){
            driverChrome.findElement(By.cssSelector(".row:nth-child(" + i + ") > td:nth-child(3)")).click();
            System.out.println(driverChrome.findElements(By.cssSelector("[name^=\"zones\"]")).size());
        }



    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }
}