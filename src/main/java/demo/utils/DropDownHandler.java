package demo.utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DropDownHandler {



    // Empty constructor
    public DropDownHandler(){}

    public static void selectDropDown(WebElement element, String value){
        Select selectDropdown = new Select(element);
        selectDropdown.selectByValue(value);
    }
}
