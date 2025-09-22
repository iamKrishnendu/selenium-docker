import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public RemoteWebDriver driver;
    private static final Logger s_logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String APPLICATION_URL = "https://demo.automationtesting.in/Register.html";
    private static final int pageLoadTimeout = 20;

    @Parameters("browser")
    @BeforeMethod(alwaysRun = true)
    public void config(String browser){
        try{
            System.out.println("Browser is "+browser);
            if(browser.equalsIgnoreCase("chrome")){
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-gpu");
                 chromeOptions.addArguments("--headless");
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), chromeOptions);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--headless");
                firefoxOptions.addArguments("--enable-javascript");
                driver = new RemoteWebDriver(new URL("http://localhost:4444"), firefoxOptions);
            }else if(browser.equalsIgnoreCase("chrome-local")){
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver();
            }else if(browser.equalsIgnoreCase("firefox-local")){
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }

            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(APPLICATION_URL);
            driver.manage().timeouts().pageLoadTimeout(pageLoadTimeout, TimeUnit.SECONDS);
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            s_logger.info("Application current url is {}", driver.getCurrentUrl());

        } catch (IOException ie){
            ie.printStackTrace();
        }

    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
    }
}
