package Managers;

import hiveCommon.KeyValReader;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.HashMap;

public class ChromeDriverManager implements BrowserManager {

    KeyValReader hiveProp = new KeyValReader("Downloads");

    @Override
    public WebDriver initializeDriver() {

        KeyValReader keyValReader = new KeyValReader("src/test/resources/Properties/Global.properties");
        String chromeDriverPath = keyValReader.getProperty("ChromeDriver");
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);
        HashMap<String,Object> prefs = setPrefs();
        ChromeOptions chromeOptions = SetChromeOptions(prefs);


        try {
            WebDriver driver = new ChromeDriver(chromeOptions);
            BrowserInstanceManager.setDriverMap(driver);
            return driver;
        } catch (Exception e) {

        }
        return null;
    }

    public ChromeOptions SetChromeOptions(HashMap prefs)
    {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setAcceptInsecureCerts(true);
        chromeOptions.addArguments("--disable-gpu");
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
        chromeOptions.setExperimentalOption("prefs",prefs);
        return chromeOptions;
    }

    public HashMap<String,Object> setPrefs()
    {
        String downloadDir = hiveProp.getProperty("downloadDir");
        HashMap<String,Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);
        prefs.put("profile.default_content_setting_values.notifications", 1);
        return prefs;


    }
}