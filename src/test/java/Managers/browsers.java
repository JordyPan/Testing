package Managers;

import hiveCommon.Base;
import org.openqa.selenium.WebDriver;

public class browsers extends Base {


    public WebDriver setup(String browserType)
    {
        driver = setBrowserInstance(browserType);
        return driver;
    }

    public WebDriver setBrowserInstance(String browserType)
    {
        BrowserManager browserManager = null;
        browserType = browserType.toLowerCase();

        switch (browserType)
        {
            case "chrome":
                browserManager = new ChromeDriverManager();
                break;
            case "firefox":
                browserManager = new FireFoxDriverManager();
                break;
            case "edge":
                browserManager = new EdgeDriverManager();
                break;
        }

        browserManager.initializeDriver();
        driver = BrowserInstanceManager.GetDriver();
        BrowserInstanceManager.driverConfig(driver);
        return driver;
    }

}
