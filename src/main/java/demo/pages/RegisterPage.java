package demo.pages;

import demo.utils.GenericUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static demo.utils.GenericUtils.*;

import java.util.UUID;

public class RegisterPage {

    RemoteWebDriver driver;

    public RegisterPage(RemoteWebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, 15), this);
    }

    @FindBy(css = "[placeholder='First Name']")
    private WebElement firstNameTextBox;
    @FindBy(css = "[placeholder='Last Name']")
    private WebElement lastNameTextBox;
    @FindBy(css = "[ng-model='Adress']")
    private WebElement addressTextArea;
    @FindBy(css = "[ng-model='EmailAdress']")
    private WebElement emailAddressText;

    @FindBy(css = "[ng-model='Phone']")
    private WebElement phoneText;

    @FindBy(name = "signup")
    private WebElement submitButton;

    public boolean checkNavigationOnRegisterPage(String pageEndpoint){
        return driver.getCurrentUrl().contains(pageEndpoint);
    }

    public void registerUser(String firstName, String lastName, String email, boolean optForDynamicData){
        if(optForDynamicData){
            firstName = String.format("firstname_%s", UUID.randomUUID());
            lastName = String.format("lastName_%s", UUID.randomUUID());
            email =  String.format("test_%s@email.com", UUID.randomUUID());
        }
        enterText(firstNameTextBox,firstName);
        enterText(lastNameTextBox, lastName);
        enterText(addressTextArea, "Bangalore");
        enterText(emailAddressText,email);
        enterText(phoneText, "9009349087");
        scrollToElement(driver, submitButton);
        click(submitButton);
        System.out.println("User "+firstName+" is registered successfully!");
    }
}
