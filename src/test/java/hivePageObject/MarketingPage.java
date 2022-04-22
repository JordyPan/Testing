package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class MarketingPage {

    public MarketingPage(WebDriver driver)
    {
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//a[@href='/lightning/o/Opportunity/home']")
    private WebElement OpportunityTab;

    @FindBy(xpath = "//a[@href='javascript:void(0);' and @title='New']")
    private WebElement newOpportunityBtn;

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement OpportunityNameField;

    @FindBy(xpath = "//input[@name='CloseDate']")
    private WebElement CloseDate;

    @FindBy(xpath = "//label[text()='Stage']/following-sibling::div //button")
    private WebElement StageSelect;

    @FindBy(xpath = "//lightning-base-combobox-item")
    private List<WebElement> StageOptions;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement SaveNewOppBtn;

    @FindBy(xpath = "//input[@title='Search Opportunities and more']")
    private WebElement OpportunitySearch;

    @FindBy(xpath = "//input[@title='Search...]")
    private WebElement Searchsearch;

    @FindBy(xpath = "//span[@class = 'slds-grid slds-grid--align-spread'] //a[@data-refid='recordId']")
    private List<WebElement> SearchLinks;

    public WebElement getOpportunityTab()
    {
        return OpportunityTab;
    }

    public WebElement getNewOpportunityBtn()
    {
        return newOpportunityBtn;
    }

    public WebElement getOpportunityNameField()
    {
        return OpportunityNameField;
    }

    public WebElement getCloseDate()
    {
        return CloseDate;
    }

    public WebElement getStageSelect()
    {
        return StageSelect;
    }

    public List<WebElement> getStageOptions()
    {
        return StageOptions;
    }

    public WebElement SelectStage(String StageName)
    {
        for(WebElement webElement : getStageOptions())
        {
            if(webElement.getAttribute("data-value").equalsIgnoreCase(StageName))
            {
                webElement.click();
                return webElement;
            }
        }
        return null;
    }

    public WebElement getSaveNewOppBtn()
    {
        return SaveNewOppBtn;
    }

    public WebElement getOpportunitySearch()
    {
        return OpportunitySearch;

    }

    public WebElement getSearchsearch()
    {
        return Searchsearch;
    }

    public List<WebElement> getSearchLinks()
    {
        return SearchLinks;
    }

    public WebElement getElementFromSearch(String Name)
    {
        for(WebElement webElement : getSearchLinks())
        {
            if (webElement.getText().equalsIgnoreCase(Name))
            {
                return webElement;
            }
        }
        return null;
    }
}
