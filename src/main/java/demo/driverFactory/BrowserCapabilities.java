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
                chrome.addArguments("--disable-gpu");
                return chrome;

            case FIREFOX:
                FirefoxOptions firefox = new FirefoxOptions();
                firefox.addArguments("--disable-gpu", "--headless");
                return firefox;

            default:
                throw new IllegalStateException("Unexpected browser: " + browser);
        }
    }
}
