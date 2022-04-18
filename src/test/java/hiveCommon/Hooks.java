package hiveCommon;

import Managers.BrowserInstanceManager;
import Managers.browsers;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;


public class Hooks {

    public static Scenario scenario;
    WebDriver driver;

    @Before
    public void OpenBrowser(Scenario scenario)
    {
        initiateBrowser(getBrowserType());
        driver = BrowserInstanceManager.GetDriver();
        driver.manage().deleteAllCookies();
        BrowserInstanceManager.setScenarioMap(scenario);
    }

    @After
    public void CloseBrowser()
    {
        Base b = new Base();
        scenario = BrowserInstanceManager.GetScenario();
        b.takeScreenshot(scenario);
        //BrowserInstanceManager.GetDriver().close();
        //BrowserInstanceManager.GetDriver().quit();
    }

    public void initiateBrowser(String browserType)
    {
        if(!(browserType ==null))
        {
            browsers browsers = new browsers();
            browsers.setup(browserType);

        }
    }

    public String getBrowserType()
    {
        KeyValReader keyValReader = new KeyValReader("src/test/resources/Properties/Global.properties");
        String Propbrowser = keyValReader.getProperty("browser");
        String Commandbrowser = System.getProperty("browser");

        String browser = Commandbrowser != null? Commandbrowser : Propbrowser;
        browser = browser.toLowerCase();

        switch (browser)
        {
            case "chrome":
                return "chrome";
            case "firefox":
                return "firefox";
            case "edge":
                return "edge";
        }

        return null;
    }

}
