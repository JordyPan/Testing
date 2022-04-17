package hiveCommon;

import Managers.BrowserInstanceManager;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    protected KeyValReader salesProp = new KeyValReader("src/test/resources/Properties/Salesforce.properties");
    protected KeyValReader greenCartProp = new KeyValReader("src/test/resources/Properties/GreenCart.properties");

    public WebElement WaitElement(WebElement element){
        WebElement find;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        find = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return find;
    }

    public WebElement WaitElementClick(WebElement element){
        WebElement find;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        find = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        return find;
    }

    public String getProp(String filename, String Propname)
    {
        KeyValReader keyValReader = new KeyValReader("src/test/resources/Properties/"+filename);
        String propvalue = keyValReader.getProperty(Propname);
        return propvalue;
    }

    public void takeScreenshot(Scenario scenario)
    {
        driver = BrowserInstanceManager.GetDriver();
        TakesScreenshot screenshot = ((TakesScreenshot)driver);
        File src = screenshot.getScreenshotAs(OutputType.FILE);
        try {
            byte[] byteArray = FileUtils.readFileToByteArray(src);
            scenario.attach(byteArray,"image/png","screenshot");
        }catch (Exception e)
        {

        }
    }
}
