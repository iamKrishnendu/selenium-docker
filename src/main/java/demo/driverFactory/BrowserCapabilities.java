package demo.driverFactory;

import demo.enums.BrowserType;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;


public final class BrowserCapabilities {

    private BrowserCapabilities(){}

    public static Capabilities getOptions(BrowserType browser) {

        switch (browser) {

            case CHROME:
                ChromeOptions chrome = new ChromeOptions();
                chrome.addArguments("--headless=new");
                chrome.addArguments("--no-sandbox");
                chrome.addArguments("--disable-dev-shm-usage");
                return chrome;

            case FIREFOX:
                FirefoxOptions firefox = new FirefoxOptions();
                firefox.addArguments("--headless=new");
                firefox.addArguments("--no-sandbox");
                firefox.addArguments("--disable-dev-shm-usage");
                return firefox;

            default:
                throw new IllegalStateException("Unexpected browser: " + browser);
        }
    }
}
