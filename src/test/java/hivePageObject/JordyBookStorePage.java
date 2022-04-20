package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JordyBookStorePage {
    WebDriver driver;
    public JordyBookStorePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }

    @FindBy(xpath = "/html/body/div[4]/div[1]/section/div[1]/div[1]/one-appnav/div/div/div/span/span")
    private WebElement AppTitle;

    @FindBy(xpath = "//input[@type = 'text'][@name='Number_1']")
    private WebElement number1;

    @FindBy(xpath = "//input[@type = 'text'][@name='Number_2']")
    private WebElement number2;

    @FindBy(xpath = "//div[@data-component-id='flowruntime_interview2'] //button[@type='button']")
    private WebElement multiNextBtn;

    @FindBy(xpath = "//div[@data-component-id='flowruntime_interview2'] //span/p")
    private WebElement result;

    @FindBy(xpath = "//a[@href='/lightning/o/Book__c/home']")
    private WebElement BookTab;

    @FindBy(xpath = "//div[@title='New']/parent::a[@href='javascript:void(0);']")
    private WebElement NewBookBtn;

    @FindBy(xpath = "//input[@name='Name']")
    private WebElement NameBox;

    @FindBy(xpath = "//div[@class = 'slds-form-element__control'] //button")
    private WebElement TypeSelectBox;

    @FindBy(xpath = "//lightning-base-combobox-item/span //span[@class='slds-truncate']")
    private List<WebElement> SelectBoxOptions;

    @FindBy(xpath = "//input[@name='Publish_Date__c']")
    private WebElement calendarBook;

    @FindBy(xpath = "//button[@name='SaveEdit']")
    private WebElement SaveNewBookBtn;

    @FindBy(xpath = "//input[@title='Search Book and more']")
    private WebElement BookSearch;

    @FindBy(xpath = "//input[@title='Search...']")
    private WebElement HomeSearch;

    @FindBy(xpath = "//span[@class = 'slds-grid slds-grid--align-spread'] //a[@data-refid='recordId']")
    private List<WebElement> SearchLinks;

    @FindBy(xpath = "//li[@class = 'slds-dropdown-trigger slds-dropdown-trigger_click slds-button_last overflow']")
    private WebElement BookOptionDropdown;

    @FindBy(xpath = "//div[@class= 'slds-dropdown slds-dropdown_right'] //a[@name='Delete']")
    private WebElement deleteOption;

    @FindBy(xpath = "//button[@title = 'Delete']")
    private WebElement deleteBtn;

    @FindBy(xpath = "//p[@class='detail']")
    private WebElement errorcdoe;

    public WebElement getAppTitle()
    {
        return AppTitle;
    }

    public WebElement getNumber1()
    {
        return number1;
    }

    public WebElement getNumber2()
    {
        return number2;
    }

    public WebElement getMultiNextBtn()
    {
        return multiNextBtn;
    }

    public WebElement getResult()
    {
        return result;
    }

    public WebElement getBookTab()
    {
        return BookTab;
    }

    public WebElement getNewBookBtn()
    {
        return NewBookBtn;
    }

    public WebElement getNameBox()
    {
        return NameBox;
    }

    public WebElement getTypeSelectBox()
    {
        return TypeSelectBox;
    }

    public List<WebElement> getSelectBoxOptions()
    {
        return SelectBoxOptions;
    }

    public WebElement getSelectBoxOption(String name)
    {
        WebElement element=null;
        for(WebElement webElement : getSelectBoxOptions())
        {
            if(webElement.getText().equalsIgnoreCase(name))
            {
                webElement.click();
                element = webElement;
            }
        }
        return element;
    }

    public WebElement getCalendarBook()
    {
        return calendarBook;
    }

    public WebElement getSaveNewBookBtn()
    {
        return SaveNewBookBtn;
    }

    public WebElement getBookSearch()
    {
        return BookSearch;
    }

    public WebElement getHomeSearch()
    {
        return HomeSearch;
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

    public WebElement getBookOptionDropdown()
    {
        return BookOptionDropdown;
    }

    public WebElement getDeleteOption()
    {
        return deleteOption;
    }

    public WebElement getDeleteBtn()
    {
        return  deleteBtn;
    }

    public void getBookDetail(String book_id)
    {
        String url = "https://jordy-dev-ed.lightning.force.com/lightning/r/Book__c/"+ book_id+"/view";
        driver.get(url);
    }

    public String getErrorCode()
    {
        return errorcdoe.getText();
    }

}
