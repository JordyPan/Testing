package com.PageObjects;

import com.Resources.TestBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage extends TestBase {
    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//*[@id=\"homepage\"]/header/div[1]/div/nav/ul/li[4]/a")
    private WebElement loginButton;

    @FindBy(xpath = "//*[@id=\"homepage\"]/div[4]/div[2]/div/div/div/span/div/div[6]/div/div/button")
    private WebElement popup;

    @FindBy(xpath = "//*[@id=\"content\"]/div/div/h2")
    private WebElement featuretext;

    @FindBy(xpath = "//*[@id=\"homepage\"]/header/div[2]/div/nav")
    private WebElement navigationbar;

    public LoginPage getloginButton()
    {
        loginButton.click();
        LoginPage lp = new LoginPage(driver);
        return lp;
    }

    public  WebElement getPopup()
    {
        return popup;
    }

    public void ClickPopup()
    {
        //waitElementClick(Popup).click();
        popup.click();
    }

    public String getFeatureText()
    {
        return featuretext.getText();
    }

    public WebElement getNavBar()
    {
        return navigationbar;
    }

}
