package demo.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;

public class GenericUtils {

    public GenericUtils(){}

    public static void enterText(WebElement element, String textToEnter){
        element.sendKeys(textToEnter);
    }

    public static void click(WebElement element){
        element.click();
    }

    public static void scrollToElement(RemoteWebDriver driver, WebElement element){
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }
}
