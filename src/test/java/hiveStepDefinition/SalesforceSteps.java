package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.ContextInj;
import hivePageObject.SalesforceHome;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SalesforceSteps extends Base {

    ContextInj testContext;
    SalesforceHome salesforceHome;

    public SalesforceSteps(ContextInj TestContext)
    {
        this.testContext = TestContext;
        salesforceHome = TestContext.pageObjectManager.GetSalesforceHome();
        driver = BrowserInstanceManager.GetDriver();
    }

    @Given("User Lands on Salesforce Main page")
    public void User_Lands_on_Salesforce_Main_page()
    {
        String app = salesProp.getProperty("appURL");
        driver.get(app);

    }

    @When("User Clicks on Login button")
    public void User_Clicks_on_Login_button()
    {
        WaitElement(salesforceHome.getLoginButton()).click();
    }

    @Then("sth happens")
    public void sth_happens()
    {

    }

}
