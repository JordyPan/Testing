package com.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.IOException;

public class Listener extends TestBase implements ITestListener {

    static ExtentTest test;
    WebDriver driver = null;
    ExtentReports extent = getReportObject();
    ThreadLocal<ExtentTest> ThreadExtent = new ThreadLocal<ExtentTest>();

    @Override
    public void onTestStart(ITestResult result) {

        test = extent.createTest(result.getMethod().getMethodName());
        ThreadExtent.set(test);
    }


    @Override
    public void onTestSuccess(ITestResult result) {
        String PassMethod = result.getMethod().getMethodName();
        ITestContext context = result.getTestContext();
        driver = (WebDriver) context.getAttribute("WebDriver");


        try {
            String path = takescreenshotOnPass(PassMethod, driver);
            ThreadExtent.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadExtent.get().log(Status.PASS,"Test Passed");

    }



    @Override
    public void onTestFailure(ITestResult result) {
        String failMethod = result.getMethod().getMethodName();

        WebDriver driver = null;
        try {
            driver = (WebDriver)result.getTestClass().getRealClass().getDeclaredField("driver").get(result.getInstance());
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (Exception e)
        {

        }

        try {
            String path = takescreenshotOnFail(failMethod, driver);
            ThreadExtent.get().addScreenCaptureFromPath(path,result.getMethod().getMethodName());
        } catch (IOException e) {
            e.printStackTrace();
        }

        ThreadExtent.get().fail(result.getThrowable());
    }


    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
