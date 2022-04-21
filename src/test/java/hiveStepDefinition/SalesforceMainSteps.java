package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.ContextInj;
import hiveCommon.KeyValReader;
import hivePageObject.SalesforceAppHome;
import hivePageObject.SalesforceHome;
import hivePageObject.SalesforceLogin;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

public class SalesforceMainSteps extends Base {

    ContextInj testContext;
    SalesforceHome salesforceHome;
    SalesforceLogin salesforceLogin;
    SalesforceAppHome salesforceAppHome;
    String app = salesProp.getProperty("appURL");

    public SalesforceMainSteps(ContextInj TestContext)
    {
        this.testContext = TestContext;
        salesforceHome = TestContext.pageObjectManager.GetSalesforceHome();
        salesforceLogin = TestContext.pageObjectManager.getSalesforceLogin();
        salesforceAppHome = TestContext.pageObjectManager.getSalesforceAppHome();
        driver = BrowserInstanceManager.GetDriver();
    }

    @Given("User logs into Salesforce APP")
    public void User_logs_into_Salesforce_APP()
    {
        Logger.debug("Logging in Salesforce-----------------------");
        driver.get(app);
        WaitElement(salesforceHome.getLoginButton()).click();
        String username = salesProp.getProperty("UserName");
        String pswd = salesProp.getProperty("Password");
        WaitElement(salesforceLogin.getUsernameBox()).sendKeys(username);
        WaitElement(salesforceLogin.getPasswordBox()).sendKeys(pswd);
        WaitElementClick(salesforceLogin.getLoginBtn()).click();
        WaitElement(salesforceAppHome.getHomeText(),50);
        Logger.info("Logged into salesforce-----------------------");

    }

    @Given("User Lands on Salesforce Main page")
    public void User_Lands_on_Salesforce_Main_page()
    {
        driver.get(app);
    }

    @When("User Clicks on Login button")
    public void User_Clicks_on_Login_button()
    {
        WaitElement(salesforceHome.getLoginButton()).click();
    }

    @When("User selects app {string}")
    public void User_selects_app(String app)
    {
        WaitElementClick(salesforceAppHome.getAppSelect()).click();
        WaitElement(salesforceAppHome.getSearchAppBox()).sendKeys(ChordEnter(app));
        testContext.variableInj.setVar("app",app);
    }

    @And ("User enters username and password")
    public void User_enters_username_and_password()
    {
        String username = salesProp.getProperty("UserName");
        String pswd = salesProp.getProperty("Password");

        WaitElement(salesforceLogin.getUsernameBox()).sendKeys(username);
        WaitElement(salesforceLogin.getPasswordBox()).sendKeys(pswd);
        WaitElementClick(salesforceLogin.getLoginBtn()).click();

    }

    @Then("user should be logged in")
    public void user_should_be_logged_in()
    {
        WaitElement(salesforceAppHome.getHomeText(),50);
        forceWait(5);
        Assert.assertEquals("Home",salesforceAppHome.getHomeText().getText());
    }

    @And("User is at {string} App")
    public void User_is_at_string_App(String app)
    {

        WaitElementClick(salesforceAppHome.getAppSelect()).click();
        WaitElement(salesforceAppHome.getSearchAppBox()).sendKeys(ChordEnter(app));
        testContext.variableInj.setVar("app",app);
        Logger.info("Hello im here at app -------------------");
    }

}
