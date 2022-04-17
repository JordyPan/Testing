package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesforceHome {

    public SalesforceHome(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"globalnavbar-header-container\"]/div[2]/div/div[6]/div/div/div[1]/a")
    private WebElement LoginButton;

    public WebElement getLoginButton()
    {
        return LoginButton;
    }

}
