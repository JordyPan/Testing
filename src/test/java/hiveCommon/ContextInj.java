package hiveCommon;

import Managers.BrowserInstanceManager;
import Managers.PageObjectManager;
import org.openqa.selenium.WebDriver;

import java.util.HashMap;

public class ContextInj {

    public WebDriver driver;
    public PageObjectManager pageObjectManager;
    public VariableInj variableInj;

    public ContextInj()
    {
        driver = BrowserInstanceManager.GetDriver();
        pageObjectManager = new PageObjectManager(driver);
        variableInj = new VariableInj();
    }


}
