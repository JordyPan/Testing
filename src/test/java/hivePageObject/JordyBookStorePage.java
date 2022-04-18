package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class JordyBookStorePage {

    public JordyBookStorePage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/div/div/span/span")
    private WebElement AppTitle;

    public WebElement getAppTitle()
    {
        return AppTitle;
    }
}
