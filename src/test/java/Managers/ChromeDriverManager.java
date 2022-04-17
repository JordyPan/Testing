package Managers;

import hiveCommon.KeyValReader;
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
        HashMap<String,String> prefs = setPrefs();
        ChromeOptions chromeOptions = SetChromeOptions(prefs);

        try {
            WebDriver driver = new ChromeDriver();
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
        chromeOptions.setExperimentalOption("prefs",prefs);
        return chromeOptions;
    }

    public HashMap<String,String> setPrefs()
    {
        String downloadDir = hiveProp.getProperty("downloadDir");
        HashMap<String,String> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadDir);

        return prefs;


    }
}