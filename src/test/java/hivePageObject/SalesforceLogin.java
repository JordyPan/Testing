package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesforceLogin {

    public SalesforceLogin(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"username\"]")
    private WebElement UsernameBox;

    @FindBy(xpath = "//*[@id=\"password\"]")
    private WebElement PasswordBox;

    @FindBy(xpath = "//*[@id=\"Login\"]")
    private WebElement loginBtn;

    public WebElement getUsernameBox()
    {
        return UsernameBox;
    }

    public WebElement getPasswordBox()
    {
        return PasswordBox;
    }

    public WebElement getLoginBtn() {
        return loginBtn;
    }
}
