package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GreenCartHome {

    public GreenCartHome(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }



}
