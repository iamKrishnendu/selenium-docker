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
    private static final String LOCAL_HOST_HUB = "http://localhost:4444";

    private DriverManager(){}


    public static WebDriver getDriver(String browserOption, boolean isRemote) {

        if (driver.get() == null) {

            BrowserType browserType =
                    BrowserType.valueOf(browserOption.toUpperCase());

            Capabilities capabilities =
                    BrowserCapabilities.getOptions(browserType);
            try{
                if(isRemote){
                    driver.set(
                            new RemoteWebDriver(
                                    new URL(LOCAL_HOST_HUB),
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
        return driver.get();
    }

    public static void unloadDriver(){
        driver.remove();
    }
}
