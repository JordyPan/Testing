package Managers;

import hivePageObject.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PageObjectManager {

    WebDriver driver;
    SalesforceHome salesforceHome;
    SalesforceLogin salesforceLogin;
    SalesforceAppHome salesforceAppHome;

    JordyBookStorePage jordyBookStorePage;
    MarketingPage marketingPage;

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

    public SalesforceLogin getSalesforceLogin()
    {
        return salesforceLogin == null? salesforceLogin = new SalesforceLogin(driver) : salesforceLogin;
    }

    public SalesforceAppHome getSalesforceAppHome()
    {
        return salesforceAppHome == null? salesforceAppHome = new SalesforceAppHome(driver) : salesforceAppHome;
    }

    public JordyBookStorePage getJordyBookStorePage() {
        return jordyBookStorePage == null? jordyBookStorePage = new JordyBookStorePage(driver) : jordyBookStorePage;
    }

    public MarketingPage getMarketingPage()
    {
        return marketingPage == null? marketingPage = new MarketingPage(driver) : marketingPage;
    }
}
