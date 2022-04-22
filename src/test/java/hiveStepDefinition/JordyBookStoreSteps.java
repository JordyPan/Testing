package hiveStepDefinition;

import Managers.BrowserInstanceManager;
import hiveCommon.Base;
import hiveCommon.ContextInj;
import hivePageObject.JordyBookStorePage;
import hivePageObject.SalesforceAppHome;
import hivePageObject.SalesforceHome;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class JordyBookStoreSteps extends Base {
    ContextInj testContext;
    JordyBookStorePage jordyBookStorePage;
    SalesforceAppHome salesforceAppHome;



    public JordyBookStoreSteps(ContextInj TestContext)
    {
        this.testContext = TestContext;
        jordyBookStorePage = testContext.pageObjectManager.getJordyBookStorePage();
        salesforceAppHome = TestContext.pageObjectManager.getSalesforceAppHome();
        driver = BrowserInstanceManager.GetDriver();
    }

    @Then("APP should be loaded")
    public void APP_should_be_loaded()
    {
        WaitElement(jordyBookStorePage.getAppTitle());
        String appName = (String) testContext.variableInj.getVar("app");
        Assert.assertEquals(appName,jordyBookStorePage.getAppTitle().getText());
    }

    @Then("User saves the new Book and checks whether new book created")
    public void User_saves_the_new_Book_and_checks_whether_new_book_created()
    {
        Logger.debug("Trying to save new book----------------------------------------------");
        String newBookName = (String) testContext.variableInj.getVar("newBookName");
        WaitElementClick(jordyBookStorePage.getSaveNewBookBtn()).click();
        Logger.info("Successfully saved the new book");
        forceWait(10);
        WaitElementClick(jordyBookStorePage.getBookSearch()).sendKeys(Keys.chord(newBookName,Keys.ENTER));
        WebElement search = WaitElement(jordyBookStorePage.getElementFromSearch(newBookName));
        Assert.assertEquals(newBookName,search.getText());
        Logger.info("Successfully validated new book");

    }


    @When("User enters {string} and {string} for Multiplication")
    public void User_enters_number1_and_number2_for_multiplication(String a, String b)
    {
        WaitElementClick(jordyBookStorePage.getNumber1()).sendKeys(a);
        WaitElementClick(jordyBookStorePage.getNumber2()).sendKeys(b);
        WaitElementClick(jordyBookStorePage.getMultiNextBtn()).click();
        String calc = WaitElement(jordyBookStorePage.getResult()).getText().split(" ")[3].split("\\.")[0].replace(",","");
        String expected = Integer.toString(Integer.parseInt(a) * Integer.parseInt(b));
        Assert.assertEquals(expected,calc);
    }

    @When("User clicks on book tab")
    public void User_clicks_on_book_tab()
    {
        Logger.debug("Trying to click on Books Tab-----------------------------");
        JavaScriptClick(jordyBookStorePage.getBookTab());
        JavaScriptClick(jordyBookStorePage.getNewBookBtn());
        Logger.info("Successfully clicked on Books tab------------------------------");

    }

    @And("User fills in name {string} and type {string} and Publish Date of today")
    public void User_fills_in_name_string_and_type_string_and_Publish_Date_of_today(String name, String type)
    {
        Logger.debug("Adding new book details ----------------------------------------");
        WaitElementClick(jordyBookStorePage.getNameBox()).sendKeys(name);
        WaitElementClick(jordyBookStorePage.getTypeSelectBox()).click();
        jordyBookStorePage.getSelectBoxOption(type);
        WaitElementClick(jordyBookStorePage.getCalendarBook()).sendKeys(TodaysDate);
        testContext.variableInj.setVar("newBookName",name);
        Logger.info("Successfully added new Book details------------------------------");
    }

    @When("User searches for book {string} and clicks on it")
    public void User_searches_for_book_string_and_clicks_on_it(String bookname)
    {
        Logger.debug("Searching for book-----------------------------------------");
        WaitElementClick(jordyBookStorePage.getHomeSearch()).sendKeys(Keys.chord(bookname,Keys.ENTER));
        WaitElement(jordyBookStorePage.getElementFromSearch(bookname)).click();
        String case_id = driver.getCurrentUrl().split("/")[4];
        testContext.variableInj.setVar("case_id",case_id);
        Logger.info("Found the Book-------------------------------------------------");
    }

    @And("deletes the book")
    public void deletes_the_book()
    {
        Logger.debug("Trying to delete a book------------------------------------------");
        forceWait(5);
        WaitElement(jordyBookStorePage.getBookOptionDropdown()).click();
        WaitElementClick(jordyBookStorePage.getDeleteOption()).click();
        WaitElementClick(jordyBookStorePage.getDeleteBtn()).click();
        jordyBookStorePage.getBookDetail((String) testContext.variableInj.getVar("case_id"));
        if(jordyBookStorePage.getErrorCode().contains("This record is no longer available"))
        {
            Assert.assertTrue(true);
        }else {
            Assert.assertTrue(false);
        }
        Logger.info("Succesfully Deleted Book-------------------------------------------");
    }

}
