package com;

import com.PageObjects.HomePage;
import com.PageObjects.LoginPage;
import com.Resources.TestBase;

import java.io.IOException;
import java.sql.ResultSet;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.sql.*;

public class Test3 extends TestBase {
    public WebDriver driver;
    private ResultSet rs = null;
    @BeforeTest
    public void setup(ITestContext context) throws SQLException, IOException {
        String host = "127.0.0.1";
        String port = ":3306";
        String DbName = "/testDB";
        String username = "root";
        String psword = "Panhuanji1!";
        String Query = "SELECT * FROM employeeinfo WHERE FirstName = 'JORDY'";

        rs = ConnectMySQLDatabase(host,port,DbName,username,psword,Query);

        driver = initializeDriver();
        context.setAttribute("WebDriver",driver);
        logger.info("Test Case 3 -------------------------------------->");

    }

    @Test
    public void getDatabsedata(ITestContext context) throws SQLException, IOException {


        while(rs.next()) {
            driver.get(p.getProperty("appURL"));
            String user = rs.getString("FirstName");
            String password = rs.getString("LastName");

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

            lp.getUsernameField().sendKeys(user);
            lp.getUserPasswordField().sendKeys(password);
            lp.getLoginButton().click();

        }
    }

    @AfterTest
    public void closeDriver()
    {
        driver.close();
    }

}
