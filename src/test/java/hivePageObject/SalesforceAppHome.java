package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SalesforceAppHome {

    public SalesforceAppHome(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//span[@class = 'breadcrumbDetail uiOutputText']")
    private WebElement HomeText;

    @FindBy(xpath = "//div[@class = 'slds-icon-waffle']")
    private WebElement appSelect;

    @FindBy(xpath = "//input[@placeholder='Search apps and items...']")
    private WebElement searchAppBox;

    public WebElement getHomeText()
    {
        return HomeText;
    }

    public WebElement getAppSelect()
    {
        return appSelect;
    }

    public WebElement getSearchAppBox()
    {
        return searchAppBox;
    }

}
