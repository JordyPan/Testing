package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.KeyValReader;
import io.cucumber.java.en.Given;

public class GreenCartSteps extends Base {

    public GreenCartSteps()
    {
        driver = BrowserInstanceManager.GetDriver();
    }

    @Given("User Lands on GreenCart Page")
    public void User_Lands_on_GreenCart_Page()
    {
        String url = greenCartProp.getProperty("appUrl");
        driver.get(url);
    }
}
