package com;

import com.PageObjects.HomePage;
import com.PageObjects.LoginPage;
import com.Resources.TestBase;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.NoSuchElementException;
import java.util.Properties;

public class Test1 extends TestBase {

    public WebDriver driver;

    @DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        Object[][] data = new Object[0][];
        try {
            logger.debug("Loading excel data -------------------->");
            data = getExcelData();
            logger.info("Got excel data---------------------->");
            
        }catch(Exception e)
        {
            logger.error("cant get excel data");
        }
        return data;
    }


    @BeforeTest
    public void Setup(ITestContext context) throws IOException {

        driver = initializeDriver();
        logger.info("Test Case 1 -------------------------------------->");
        context.setAttribute("WebDriver", driver);

    }


    @Test(dataProvider = "getData")
    public void LoginTest(String username, String password) throws IOException {

        driver.get(p.getProperty("appURL"));

        HomePage hp = new HomePage(driver);
        LoginPage lp = null;

        logger.debug("See if popup is appearing ---------->");
        try {
            if (waitElementVisable(driver, hp.getPopup()).isDisplayed()) {
                hp.ClickPopup();
                logger.info("Succesfully cliked on popup--------------------->");
                lp = hp.getloginButton();
            }
        }catch(Exception e)
        {
            logger.debug("No popup -------------> Proceeding to login---------->");
            lp = hp.getloginButton();
        }

        lp.getUsernameField().sendKeys(username);
        lp.getUserPasswordField().sendKeys(password);
        lp.getLoginButton().click();

    }


    @AfterTest
    public void closedrvier()
    {

        driver.close();
    }
}
