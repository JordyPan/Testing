package hiveCommon;

import Managers.BrowserInstanceManager;
import io.cucumber.java.Scenario;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.sql.*;
import java.time.Duration;

public class Base {
    public WebDriver driver;
    protected KeyValReader salesProp = new KeyValReader("src/test/resources/Properties/Salesforce.properties");
    protected KeyValReader greenCartProp = new KeyValReader("src/test/resources/Properties/GreenCart.properties");
    protected KeyValReader sql = new KeyValReader("src/test/resources/Properties/SQL.properties");

    public static Logger Logger = LogManager.getLogger("base");

    public void forceWait(Integer seconds)
    {
        Integer mill = seconds*1000;
        try {
            Thread.sleep(mill);
        }catch (Exception e)
        {

        }
    }

    public WebElement WaitElement(WebElement element){
        WebElement find;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        find = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return find;
    }

    public WebElement WaitElement(WebElement element, Integer seconds){
        WebElement find;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
        find = webDriverWait.until(ExpectedConditions.visibilityOf(element));
        return find;
    }

    public WebElement WaitElementClick(WebElement element){
        WebElement find;
        WebDriverWait webDriverWait = new WebDriverWait(driver, Duration.ofSeconds(30));
        find = webDriverWait.until(ExpectedConditions.elementToBeClickable(element));
        return find;
    }

    public void JavaScriptClick(WebElement element)
    {
        WaitElementClick(element);
        ((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
    }

    public String ChordEnter(String a)
    {
        String chordKey = Keys.chord(a,Keys.ENTER);
        return chordKey;
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

    public ResultSet ExecuteMysqlQuery(String query) throws SQLException {

        String host = sql.getProperty("host");
        String port = sql.getProperty("port");
        String DbName = sql.getProperty("DbName");
        String username = sql.getProperty("username");
        String password = sql.getProperty("password");

        Connection con = DriverManager.getConnection("jdbc:mysql://"+ host + port + DbName, username,password);
        Statement state = con.createStatement();
        ResultSet result = state.executeQuery(query);

        return result;

    }
}
