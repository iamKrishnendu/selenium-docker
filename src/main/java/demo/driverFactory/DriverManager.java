package demo.driverFactory;

import demo.enums.BrowserType;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;


public class DriverManager {

    private static final Logger s_logger = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    private DriverManager(){}


    public static WebDriver getDriver(String browserOption, boolean isRemote) {

        if (driver.get() == null) {

            try{
                s_logger.info("Specified Browser : {}", browserOption);
                s_logger.info("check the Remote flag {}", isRemote);
                if(isRemote){
                    BrowserType browserType =
                            BrowserType.valueOf(browserOption.toUpperCase());

                    Capabilities capabilities =
                            BrowserCapabilities.getOptions(browserType);

                    String seleniumUrl = System.getProperty(
                            "selenium.url",
                            "http://selenium:4444/wd/hub"
                    );

                    s_logger.info("Starting REMOTE driver at {}", seleniumUrl);
                    driver.set(
                            new RemoteWebDriver(
                                    new URL(seleniumUrl),
                                    capabilities
                            )
                    );
                } else {
                    WebDriverManager.chromedriver().setup();
                    driver.set(
                            new ChromeDriver()
                    );
                }
            }catch (MalformedURLException e){
                s_logger.error("URL exception occurred!");
            }
        }
        s_logger.info("REMOTE DRIVER INSTANCE: {}", driver.get() instanceof RemoteWebDriver);
        return driver.get();
    }

    public static void unloadDriver(){
        driver.get().quit();
        driver.remove();
    }
}
