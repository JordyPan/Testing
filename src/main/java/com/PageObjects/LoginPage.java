package com.PageObjects;

import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class LoginPage extends TestBase {
    private WebDriver driver;

    public LoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(id = "user_email")
    private WebElement usernameField;

    @FindBy(id = "user_password")
    private WebElement userPasswordField;

    @FindBy(xpath = "//*[@id='hero']/div/form/div[3]/input")
    private WebElement loginButton;

    public WebElement getUsernameField()
    {
        return usernameField;
    }

    public  WebElement getUserPasswordField()
    {
        return userPasswordField;
    }

    public  WebElement getLoginButton()
    {
        return loginButton;
    }


}
