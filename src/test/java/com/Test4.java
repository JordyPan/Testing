package com;

import com.PageObjects.ConvertPage;
import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.sql.SQLException;

public class Test4 extends TestBase {

    public WebDriver driver;
    @BeforeTest
    public void setup(ITestContext context) throws IOException {
        driver = initializeDriver();
        context.setAttribute("WebDriver",driver);
        logger.info("Test Case 4 -------------------------------------->");
    }

    @Test
    public void uploadFile() throws IOException, InterruptedException {
        driver.get(p.getProperty("uploadURL"));
        ConvertPage cp = new ConvertPage(driver);
        waitElementClick(cp.getButton()).click();
        Thread.sleep(2000);
        Runtime.getRuntime().exec("src/main/java/com/Resources/fileupload.exe");
    }


    @AfterTest
    public void closedrvier()
    {

        driver.close();
    }


}
