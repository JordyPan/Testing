package Managers;

import io.cucumber.java.Scenario;
import org.openqa.selenium.WebDriver;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class BrowserInstanceManager {


    public static Map<Integer, WebDriver> DriverMap = new HashMap<>();
    public static Map<Integer, Scenario> ScenarioMap = new HashMap<>();

    public static synchronized WebDriver GetDriver()
    {
        return DriverMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized Scenario GetScenario()
    {
        return ScenarioMap.get((int) (long) (Thread.currentThread().getId()));
    }

    public static synchronized void setDriverMap(WebDriver driver)
    {
        DriverMap.put((int) (long) (Thread.currentThread().getId()),driver);
    }

    public static synchronized void setScenarioMap(Scenario scenario)
    {
        ScenarioMap.put((int) (long) (Thread.currentThread().getId()),scenario);
    }

    public static synchronized void driverConfig(WebDriver driver)
    {
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(80));
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
        driver.manage().timeouts().scriptTimeout(Duration.ofSeconds(20));
    }
}

