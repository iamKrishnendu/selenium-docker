import demo.driverFactory.DriverManager;
import demo.enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.*;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public WebDriver driver;
    private static final Logger s_logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String APPLICATION_URL = "https://demo.automationtesting.in/Register.html";
    private static final int pageLoadTimeout = 20;

    @Parameters({"browser", "isRemote"})
    @BeforeMethod(alwaysRun = true)
    public void config(
            @Optional("chrome") String browser,
            @Optional("true") String isRemote
    ) {
        try{

            driver = DriverManager.getDriver(browser, Boolean.parseBoolean(isRemote));
            driver.manage().window().maximize();
            driver.manage().deleteAllCookies();
            driver.get(APPLICATION_URL);
            driver.manage().timeouts().pageLoadTimeout(Duration.of(pageLoadTimeout, TimeUnit.SECONDS.toChronoUnit()));
            driver.manage().timeouts().implicitlyWait(Duration.of(pageLoadTimeout, TimeUnit.SECONDS.toChronoUnit()));
            s_logger.info("Application current url is {}", driver.getCurrentUrl());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @AfterMethod(alwaysRun = true)
    public void closeBrowser(){
        driver.quit();
        DriverManager.unloadDriver();
    }
}
