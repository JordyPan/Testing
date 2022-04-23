package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.ContextInj;
import hivePageObject.MarketingPage;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.List;

public class MarketingSteps extends Base {

    MarketingPage marketingPage;

    ContextInj contextInj;
    public MarketingSteps(ContextInj testcontext)
    {
        this.contextInj = testcontext;
        marketingPage = contextInj.pageObjectManager.getMarketingPage();
        driver = BrowserInstanceManager.GetDriver();
    }

    @When("User clicks on Opportunities Tab")
    public void User_clicks_on_Opportunities_Tab()
    {
        Logger.debug("Going to opportunities tab-----------------------");
        JavaScriptClick(marketingPage.getOpportunityTab());
        Logger.info("Successfully clicked on opportunities tab");
    }

    @And("User creates a new Opportunity")
    public void User_creates_a_new_Opportunity(DataTable dataTable)
    {
        JavaScriptClick(marketingPage.getNewOpportunityBtn());
        List<List<String>> data = dataTable.asLists();

        String opportunityName = data.get(0).get(0);
        String Stage = data.get(0).get(1);

        Logger.debug("Trying to send information--------------------");
        WaitElementClick(marketingPage.getOpportunityNameField()).sendKeys(opportunityName);
        WaitElementClick(marketingPage.getCloseDate()).sendKeys(TodaysDate);
        WaitElementClick(marketingPage.getStageSelect()).click();
        marketingPage.SelectStage(Stage);
        Logger.info("New Opportunity Information filled----------------");
        Logger.debug("Clicking on save ---------------------------------");
        WaitElementClick(marketingPage.getSaveNewOppBtn()).click();
        Logger.info("Successfully clicked on save -----------------------");
        contextInj.variableInj.setVar("OpportunityName",opportunityName);
    }

    @Then("New Opportunity should be created")
    public void New_Opportunity_should_be_created()
    {
        forceWait(5);
        Logger.debug("Clicking on Search---------------------");
        String Opportunityname = (String) contextInj.variableInj.getVar("OpportunityName");
        WaitElementClick(marketingPage.getOpportunitySearch()).sendKeys(ChordEnter(Opportunityname));
        WebElement search = WaitElement(marketingPage.getElementFromSearch(Opportunityname));
        Assert.assertEquals(Opportunityname,search.getText());
        Logger.info("Successfully validated new Opportunity");
    }

}
