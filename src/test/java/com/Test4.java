package com;

import com.PageObjects.ConvertPage;
import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Properties;

public class Test4 extends TestBase {

    public WebDriver driver;
    ConvertPage cp = null;

    @BeforeTest
    public void setup(ITestContext context) throws IOException {
        driver = initializeDriver();
        context.setAttribute("WebDriver",driver);
        logger.info("Test Case 4 -------------------------------------->");
    }

    @Test
    public void uploadFile() throws IOException, InterruptedException {
        logger.debug("Uploading File -------------------------------------->");
        driver.get(p.getProperty("uploadURL"));
        cp = new ConvertPage(driver);
        waitElementClick(cp.getButton()).click();
        Thread.sleep(2000);
        Runtime.getRuntime().exec("src/main/java/com/Resources/fileupload.exe");
        Thread.sleep(5000);
        logger.info("Succesfully uploaded file");
    }

    @Test(dependsOnMethods = {"uploadFile"})
    public void downloadFile() throws IOException, InterruptedException {
        logger.debug("waiting for download buttong");
        cp = new ConvertPage(driver);
        driver.switchTo().defaultContent();
        waitElementVisable(driver,cp.getButton2()).click();
        waitElementClick(cp.getDownload()).click();
        logger.info("file downloaded");
        Thread.sleep(2000);
        File f = new File(getDownloadDirc() + "\\abc_abcdpdf_pdf_to_word.docx");
        Assert.assertTrue(f.exists());
        logger.info("File exists");
        f.delete();
        logger.info("File Deleted");
    }

    @AfterTest
    public void closeDriver()
    {

        driver.close();
    }



}
