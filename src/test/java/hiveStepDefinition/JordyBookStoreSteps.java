package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.ContextInj;
import hivePageObject.JordyBookStorePage;
import io.cucumber.java.en.Then;
import org.junit.Assert;

public class JordyBookStoreSteps extends Base {
    ContextInj testContext;
    JordyBookStorePage jordyBookStorePage;

    public JordyBookStoreSteps(ContextInj TestContext)
    {
        this.testContext = TestContext;
        jordyBookStorePage = testContext.pageObjectManager.getJordyBookStorePage();
        driver = BrowserInstanceManager.GetDriver();
    }

    @Then("APP should be loaded")
    public void Then_APP_should_be_loaded()
    {
        WaitElement(jordyBookStorePage.getAppTitle());
        String appName = (String) testContext.variableInj.getVar("app");
        Assert.assertEquals(appName,jordyBookStorePage.getAppTitle().getText());
    }

}
