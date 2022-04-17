package hiveCommon;

import Managers.BrowserInstanceManager;
import Managers.PageObjectManager;
import org.openqa.selenium.WebDriver;

public class ContextInj {

    public WebDriver driver;
    public PageObjectManager pageObjectManager;

    public ContextInj()
    {
        driver = BrowserInstanceManager.GetDriver();
        pageObjectManager = new PageObjectManager(driver);

    }


}
