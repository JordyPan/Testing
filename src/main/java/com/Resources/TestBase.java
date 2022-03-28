package com.Resources;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.apache.commons.io.FileUtils;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class TestBase {
    private WebDriver driver;
    public static Logger logger = LogManager.getLogger("logA");
    public Properties p;
    public static ExtentReports extent;

    public WebDriver initializeDriver() throws IOException {

        p = new Properties();
        FileInputStream fi = new FileInputStream("src/main/java/com/Properties/GlobalParameters.properties");
        p.load(fi);

        //String browserName = p.getProperty("browser");
        String browserName = System.getProperty("browser");
        String ChromeDriver = System.getProperty("user.dir")+p.getProperty("ChromeDriver");
        String FireFoxDriver = System.getProperty("user.dir")+p.getProperty("FireFoxDriver");
        String EdgeDriver = System.getProperty("user.dir")+p.getProperty("EdgeDriver");
        String URL = p.getProperty("appURL");

        if(browserName.contains("chrome"))
        {
            try {

                ChromeOptions options = new ChromeOptions();
                if(browserName.contains("headless"))
                {
                    //Headless execution
                    //options.addArguments("--headless");
                    options.setHeadless(true);
                }


                //Skip security certification
                options.setAcceptInsecureCerts(true);

                //default download directory
                Map<String, Object> prefs = new HashMap<String, Object>();
                prefs.put("download.default_directory", "C:\\Chrome\\Downloads");
                options.setExperimentalOption("prefs",prefs);

                //block popups
                options.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));

                System.setProperty("webdriver.chrome.driver", ChromeDriver);
                driver = new ChromeDriver(options);


            }catch (Exception e){

            }
        }
        else if(browserName.equalsIgnoreCase("firefox"))
        {
            FirefoxOptions options = new FirefoxOptions();
            options.setHeadless(true);
            System.setProperty("webdriver.gecko.driver", FireFoxDriver);
            driver = new FirefoxDriver();
        }
        else if(browserName.equalsIgnoreCase("edge"))
        {
            System.setProperty("webdriver.edge.driver", EdgeDriver);
            driver = new EdgeDriver();
        }
        else if(browserName.equalsIgnoreCase("grid")) {
            DesiredCapabilities dc = new DesiredCapabilities();
            dc.setBrowserName("chrome");
            dc.setPlatform(Platform.WIN10);

            driver = new RemoteWebDriver(new URL("http://192.168.50.136:4444"), dc);
        }



        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();
        driver.manage().window().maximize();

        return driver;

    }


    public WebDriver getDriver()
    {
        return driver;
    }

    public WebElement waitElementClick(WebElement element){
        WebElement find;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        find = wait.until(ExpectedConditions.elementToBeClickable(element));
        return find;
    }

    public WebElement waitElementVisable(WebDriver driver, WebElement element){
        WebElement find;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        find = wait.until(ExpectedConditions.visibilityOf(element));
        return find;
    }

    public String takescreenshotOnFail(String Failname, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File output = ts.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyymmdd_hhmmss").format(Calendar.getInstance().getTime());
        String destination = System.getProperty("user.dir")+"\\Screenshots\\Fail\\"+timeStamp+"_"+Failname+".jpg";
        FileUtils.copyFile(output, new File(destination));
        return destination;
    }

    public String takescreenshotOnPass(String Passname, WebDriver driver) throws IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        File output = ts.getScreenshotAs(OutputType.FILE);
        String timeStamp = new SimpleDateFormat("yyyymmdd_hhmmss").format(Calendar.getInstance().getTime());
        String destination = System.getProperty("user.dir")+"\\Screenshots\\Pass\\"+timeStamp+"_"+Passname+".jpg";
        FileUtils.copyFile(output, new File(destination));
        return destination;
    }

    public static ExtentReports getReportObject()
    {

        String reportPath = System.getProperty("user.dir")+"\\Reports\\index.html";
        ExtentSparkReporter SP = new ExtentSparkReporter(reportPath);
        SP.config().setReportName("Jordy's report");
        SP.config().setDocumentTitle("Selenium Report");

        extent = new ExtentReports();
        extent.attachReporter(SP);
        extent.setSystemInfo("Tester","Jordy");
        return extent;
    }

    public static Object[][] getExcelData() throws IOException {
        FileInputStream FI = new FileInputStream(System.getProperty("user.dir")+"\\Test_Data.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(FI);
        Object[][] result = new Object[0][0];

        for(int i = 0; i<workbook.getNumberOfSheets();i++)
        {

            if(workbook.getSheetName(i).equalsIgnoreCase("Data"))
            {
                XSSFSheet sheet = workbook.getSheetAt(i);
                int RowCount = sheet.getPhysicalNumberOfRows();

                ArrayList<ArrayList> lista = new ArrayList<ArrayList>();

                Iterator<Row> Rit = sheet.iterator();
                Row FirstRow = Rit.next();
                Iterator<Cell> Cit = FirstRow.cellIterator();
                int usernameCol = 0;
                int passwordCol = 0;
                int count = 0;
                int arrayListIndex = 0;
                while(Cit.hasNext())
                {
                    Cell c1 = Cit.next();
                    if(c1.getStringCellValue().equalsIgnoreCase("Username"))
                    {
                        usernameCol = count;
                        passwordCol = usernameCol+1;
                    }
                    count++;
                }

                while(Rit.hasNext())
                {
                    Row r1 = Rit.next();
                    String username = r1.getCell(usernameCol).getStringCellValue();
                    String password = r1.getCell(passwordCol).getStringCellValue();

                    if(arrayListIndex<=RowCount)
                    {
                        lista.add(new ArrayList<>());
                        lista.get(arrayListIndex).add(username);
                        lista.get(arrayListIndex).add(password);
                    }
                    arrayListIndex++;
                }

                result = new Object[lista.size()][];
                for (int j = 0; j < lista.size(); j++) {
                    ArrayList<Object> row = lista.get(j);
                    result[j] = row.toArray(new Object[row.size()]);
                }

            }
        }
    return result;

    }

    //window handles

}
