package hivePageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class JordyBookStorePage {

    public JordyBookStorePage(WebDriver driver)
    {
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
    private WebElement MainSearch;


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

    public WebElement getMainSearch()
    {
        return MainSearch;
    }


}
