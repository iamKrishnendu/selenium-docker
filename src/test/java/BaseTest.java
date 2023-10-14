import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class BaseTest {

    public RemoteWebDriver driver;
    private static final Logger s_logger = LoggerFactory.getLogger(BaseTest.class);
    private static final String APPLICATION_URL = "https://demo.automationtesting.in/Register.html";
    private static final int pageLoadTimeout = 20;

    @Parameters("browser")
    @BeforeSuite(alwaysRun = true)
    public void config(String browser){
        try{

            if(browser.equalsIgnoreCase("chrome")){
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("--no-sandbox");
                chromeOptions.addArguments("--disable-gpu");
                 chromeOptions.addArguments("--headless");
                s_logger.info("Initializing RemoteWebDriver at http://localhost:4444");
                driver = new RemoteWebDriver(new URL("http://192.168.0.2:4444/wd/hub"), chromeOptions);
            } else if (browser.equalsIgnoreCase("firefox")) {
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                firefoxOptions.addArguments("--no-sandbox");
                firefoxOptions.addArguments("--disable-gpu");
                firefoxOptions.addArguments("--headless");
                s_logger.info("Initializing RemoteWebDriver at http://localhost:4444");
                driver = new RemoteWebDriver(new URL("http://192.168.0.2:5555/wd/hub"), firefoxOptions);
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

    @AfterTest(alwaysRun = true)
    public void closeBrowser(){
        driver.close();
        driver.quit();
    }
}
