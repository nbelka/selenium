import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;

public class test9 {
    private WebDriver driverChrome;

    private String StartPage = "http://localhost:4433/litecart/admin";

    By Name = By.name("username");
    By Password = By.name("password");
    By Login = By.name("login");
    By Catalog = By.cssSelector("#app-:nth-child(2)");
    By AddNewProduct = By.cssSelector(".button:nth-child(2)");
    By StatusEnabled = By.cssSelector("label:nth-child(3) > input");
    By NameOfNewProduct = By.cssSelector("tr:nth-child(2) .input-wrapper:nth-child(3) > input");
    By CodeOfNewProduct = By.cssSelector("#tab-general > table > tbody > tr:nth-child(3) input");
    By CheckCategoriesOfNewProduct = By.cssSelector("tr:nth-child(4) tr:nth-child(2) input");
    By CheckGenderMaleOfNewProduct = By.cssSelector("tr:nth-child(7) tr:nth-child(3) input");
    By SetQuantityOfNewProduct = By.cssSelector("table:nth-child(1) > tbody > tr:nth-child(1) > td > input:nth-child(3)");
    By SetDateValidFromOfNewProduct = By.cssSelector("tr:nth-child(10) input");
    By SetDateValidToOfNewProduct = By.cssSelector("tr:nth-child(11) input");
    By InformationMenu = By.cssSelector(".index > li:nth-child(2) > a");
    By ManufacturerInformation = By.cssSelector("#tab-information > table > tbody > tr:nth-child(1) > td > select");
    By KeywordsInformation = By.cssSelector("#tab-information td > input");
    By ShortDescriptionInformation = By.cssSelector("tr:nth-child(4) .input-wrapper > input");
    By DescriptionInformation = By.cssSelector(".trumbowyg-editor");
    By HeadTitleInformation = By.cssSelector("tr:nth-child(6) input");
    By MetaDescription = By.cssSelector("tr:nth-child(7) .input-wrapper > input");
    By Prices = By.cssSelector(".index > li:nth-child(4) > a");
    By PurchasePrice = By.cssSelector("table:nth-child(2) input");
    By Currency = By.cssSelector("#tab-prices > table:nth-child(2) > tbody > tr > td > select");
    By PriceUSD = By.cssSelector("tr:nth-child(2) .input-wrapper:nth-child(1) > input");
    By PriceEUR = By.cssSelector("tr:nth-child(3) .input-wrapper > input");
    By SaveNewProduct = By.cssSelector(".button-set > button:nth-child(1)");
    By FileDuck = By.cssSelector("table:nth-child(3) input");
    By Search = By.cssSelector("form:nth-child(1) > input");

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();

    }

    @Test
    public void AddingItemToCart(){

        /*
            Этот тест проверяет возможность добавления нового товара
        */

        File image = new File("src/test/java/Duck.jpg");
        String path = image.getAbsolutePath();
        driverChrome.get(StartPage);
        driverChrome.findElement(Name).sendKeys("admin");
        driverChrome.findElement(Password).sendKeys("admin");
        driverChrome.findElement(Login).click();

        driverChrome.findElement(Catalog).click();
        driverChrome.findElement(AddNewProduct).click();

        driverChrome.findElement(StatusEnabled).click();
        driverChrome.findElement(NameOfNewProduct).sendKeys("New product10111");
        driverChrome.findElement(CodeOfNewProduct).sendKeys("77601101");
        driverChrome.findElement(CheckCategoriesOfNewProduct).click();
        driverChrome.findElement(CheckGenderMaleOfNewProduct).click();
        driverChrome.findElement(SetQuantityOfNewProduct).sendKeys("10");
        driverChrome.findElement(SetDateValidFromOfNewProduct).sendKeys("01.01.2000");
        driverChrome.findElement(SetDateValidToOfNewProduct).sendKeys("01.01.2020");
        driverChrome.findElement(FileDuck).sendKeys(path);

        driverChrome.findElement(InformationMenu).click();

        (new Select(driverChrome.findElement(ManufacturerInformation))).selectByVisibleText("ACME Corp.");
        driverChrome.findElement(KeywordsInformation).sendKeys("Торговля на новостях");
        driverChrome.findElement(ShortDescriptionInformation).sendKeys("ShortDescriptionInformation");
        driverChrome.findElement(DescriptionInformation).sendKeys("Полное описание");
        driverChrome.findElement(HeadTitleInformation).sendKeys("Head title");
        driverChrome.findElement(MetaDescription).sendKeys("Meta description");

        driverChrome.findElement(Prices).click();

        driverChrome.findElement(PurchasePrice).sendKeys("100");
        (new Select(driverChrome.findElement(Currency))).selectByVisibleText("Euros");
        driverChrome.findElement(PriceUSD).sendKeys("110");
        driverChrome.findElement(PriceEUR).sendKeys("108");

        driverChrome.findElement(SaveNewProduct).click();
    }

    @Test
    public void isProductExists(){
        /*
            Этот тест проверяет наличие среди продуктов товара, созданного в кейсе AddingItemToCart
        */
        driverChrome.findElement(Search).sendKeys("New product10111");
        Actions actions = new Actions(driverChrome);
        actions.sendKeys(Keys.ENTER).build().perform();
        Assert.assertTrue(Integer.parseInt(driverChrome.findElement(By.cssSelector("#content > form > table > tbody" +
                " > tr.footer > td")).getText().replaceAll("[^-?0-9]+", " ").split(" ")[1]) > 0);
        //.replaceAll("[^-?0-9]+", " ").split(" "););


    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }
}
