import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class test7 {
    private WebDriver driverChrome;

    private String StartPage = "http://localhost:4433/litecart";

    private By StartNameText = By.cssSelector("#box-campaigns .name");
    private By FinishNameText = By.cssSelector("#box-product .title");
    private By FirstCampaignsItem = By.cssSelector("#box-campaigns .link");
    private By CampaignsCampaignPrice = By.cssSelector("#box-campaigns .campaign-price");
    private By CampaignsRegularPrice = By.cssSelector("#box-campaigns .regular-price");
    private By ProductCampaignPrice = By.cssSelector("#box-product .campaign-price");
    private By ProductRegularPrice = By.cssSelector("#box-product .regular-price");
    private By SalePrice = By.cssSelector(".campaign-price");
    private By RegularPrice = By.cssSelector(".regular-price");

    @BeforeTest
    public void start(){
        driverChrome = new ChromeDriver();
    }

    @Test
    void productNameTest(){
        driverChrome.get(StartPage);
        String nameStart = driverChrome.findElement(StartNameText).getText();
        driverChrome.findElement(FirstCampaignsItem).click();
        String finish = driverChrome.findElement(FinishNameText).getText();
        Assert.assertTrue(finish.equals(nameStart));
    }

    @Test
    void productPrice(){
        driverChrome.get(StartPage);
        String salePrice = driverChrome.findElement(CampaignsCampaignPrice).getText();
        String regularPrice = driverChrome.findElement(CampaignsRegularPrice).getText();
        driverChrome.findElement(FirstCampaignsItem).click();
        String salePricefinish = driverChrome.findElement(ProductCampaignPrice).getText();
        String regularPricefinish = driverChrome.findElement(ProductRegularPrice).getText();
        Assert.assertTrue(salePrice.equals(salePricefinish));
        Assert.assertTrue(regularPrice.equals(regularPricefinish));
    }


    @Test
    void crossedGrayPrice(){
        driverChrome.get(StartPage);
        String[] textcolor = driverChrome.findElement(CampaignsRegularPrice).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        String fontPrice = driverChrome.findElement(CampaignsRegularPrice).getCssValue("text-decoration-line");
        Assert.assertTrue(textcolor[1].equals(textcolor[2]) && textcolor[2].equals(textcolor[3]));
        Assert.assertTrue(fontPrice.equals("line-through"));
    }

    @Test
    void salePrice(){
        driverChrome.get(StartPage);
        String[] textcolor = driverChrome.findElement(CampaignsCampaignPrice).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        Assert.assertTrue(textcolor[2].equals("0") && textcolor[3].equals("0"));
        driverChrome.findElement(FirstCampaignsItem).click();
        textcolor = driverChrome.findElement(SalePrice).getCssValue("color").replaceAll("[^-?0-9]+", " ").split(" ");
        Assert.assertTrue(textcolor[2].equals("0") && textcolor[3].equals("0"));
    }

    @Test
    void sizePrice(){
        driverChrome.get(StartPage);
        String salePriceSize = driverChrome.findElement(CampaignsCampaignPrice).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        String regularPriceSize = driverChrome.findElement(CampaignsRegularPrice).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        Assert.assertTrue(Float.parseFloat(salePriceSize) > Float.parseFloat(regularPriceSize));
        driverChrome.findElement(FirstCampaignsItem).click();
        salePriceSize = driverChrome.findElement(SalePrice).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        regularPriceSize = driverChrome.findElement(RegularPrice).getCssValue("font-size").replaceAll("[^-?.0-9]+","");
        Assert.assertTrue(Float.parseFloat(salePriceSize) > Float.parseFloat(regularPriceSize));
    }

    @AfterTest
    public void stop(){
        driverChrome.quit();
        driverChrome = null;
    }
}
