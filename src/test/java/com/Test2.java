package com;

import com.PageObjects.HomePage;
import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Properties;

public class Test2 extends TestBase {
    public WebDriver driver;
    HomePage hp;

    @BeforeTest
    public void homeTest(ITestContext context) throws IOException {

        driver = initializeDriver();
        context.setAttribute("WebDriver",driver);
        logger.info("Test Case 2 -------------------------------------->");
        driver.get(p.getProperty("appURL"));
    }


    @Test
    public void getHomePageItems()
    {

        hp = new HomePage(driver);

        logger.debug("Waiting for popup to appear and click ---------->");
        waitElementClick(hp.getPopup());
        hp.ClickPopup();
        logger.info("Succesfully cliked on popup--------------------->");

        logger.debug("Validating home page items --------------------->");

        Assert.assertEquals(hp.getFeatureText(),"FEATURED COURSES");
        Assert.assertTrue(hp.getNavBar().isDisplayed());

        logger.info("All items are present and correct--------------------> Test Passed");
        // adding comment for git
    }

    @AfterTest
    public void closedrvier()
    {

        driver.close();
    }
}
