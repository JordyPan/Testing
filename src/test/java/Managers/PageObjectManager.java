package Managers;

import hivePageObject.GreenCartHome;
import hivePageObject.SalesforceHome;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectManager {

    WebDriver driver;
    SalesforceHome salesforceHome;
    GreenCartHome greenCartHome;

    public PageObjectManager(WebDriver driver)
    {
        this.driver = driver;
    }

    public SalesforceHome GetSalesforceHome()
    {
        return salesforceHome == null ? salesforceHome = new SalesforceHome(driver) : salesforceHome;
    }

    public GreenCartHome GetGreenCartHome()
    {
        return greenCartHome == null? greenCartHome = new GreenCartHome(driver) : greenCartHome;
    }
}
