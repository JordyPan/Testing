package com.PageObjects;

import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ConvertPage extends TestBase {
    private WebDriver driver;
    public ConvertPage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "uploader_btn")
    private WebElement ConvertButton;

    public  WebElement getButton()
    {
        return ConvertButton;
    }
}
