import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;

public class test7 {
    private WebDriver driverChrome;
    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    void productNameTest(){
        driverChrome.get("http://localhost:4433/litecart");
        String nameStart = driverChrome.findElement(By.cssSelector("#box-campaigns .name")).getText();
        driverChrome.findElement(By.cssSelector("#box-campaigns .link")).click();
        String finish = driverChrome.findElement(By.cssSelector("#box-product .title")).getText();
        Assert.assertTrue(finish.equals(nameStart));
    }

    @Test
    void productPrice(){
        driverChrome.get("http://localhost:4433/litecart");
        String salePrice = driverChrome.findElement(By.cssSelector("#box-campaigns .campaign-price")).getText();
        String regularPrice = driverChrome.findElement(By.cssSelector("#box-campaigns .regular-price")).getText();
        driverChrome.findElement(By.cssSelector("#box-campaigns .link")).click();
        String salePricefinish = driverChrome.findElement(By.cssSelector("#box-product .campaign-price")).getText();
        String regularPricefinish = driverChrome.findElement(By.cssSelector("#box-product .regular-price")).getText();
        Assert.assertTrue(salePrice.equals(salePricefinish));
        Assert.assertTrue(regularPrice.equals(regularPricefinish));
    }


    @Test
    void crossedGrayPrice(){
        driverChrome.get("http://localhost:4433/litecart");
        String[] textcolor = driverChrome.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        String fontPrice = driverChrome.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("text-decoration-line");
        Assert.assertTrue(textcolor[1].equals(textcolor[2]) && textcolor[2].equals(textcolor[3]));
        Assert.assertTrue(fontPrice.equals("line-through"));
    }

    @Test
    void salePrice(){
        driverChrome.get("http://localhost:4433/litecart");
        String[] textcolor = driverChrome.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        Assert.assertTrue(textcolor[2].equals("0") && textcolor[3].equals("0"));
        driverChrome.findElement(By.cssSelector("#box-campaigns .link")).click();
        textcolor = driverChrome.findElement(By.cssSelector(".campaign-price")).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        Assert.assertTrue(textcolor[2].equals("0") && textcolor[3].equals("0"));
    }

    @Test
    void sizePrice(){
        driverChrome.get("http://localhost:4433/litecart");
        String salePriceSize = driverChrome.findElement(By.cssSelector("#box-campaigns .campaign-price")).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        String regularPriceSize = driverChrome.findElement(By.cssSelector("#box-campaigns .regular-price")).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        Assert.assertTrue(Float.parseFloat(salePriceSize) > Float.parseFloat(regularPriceSize));
        driverChrome.findElement(By.cssSelector("#box-campaigns .link")).click();
        salePriceSize = driverChrome.findElement(By.cssSelector(".campaign-price")).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        regularPriceSize = driverChrome.findElement(By.cssSelector(".regular-price")).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        Assert.assertTrue(Float.parseFloat(salePriceSize) > Float.parseFloat(regularPriceSize));
    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }
}
