import demo.pages.RegisterPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class RegisterTest extends BaseTest {

    RegisterPage registerPage;
    SoftAssert softAssert;
    @BeforeClass
    public void initTest(){
        registerPage = new RegisterPage(driver);
    }
    @Test
    public void register_a_dynamic_user_test(){
        softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkNavigationOnRegisterPage("/Register"),
                "Register page navigation is failed");
        registerPage.registerUser("","","",true);
        softAssert.assertAll();
    }

    @Test
    public void register_a_non_dynamic_user_test(){
        softAssert = new SoftAssert();
        softAssert.assertTrue(registerPage.checkNavigationOnRegisterPage("/Register"),
                "Register page navigation is failed");
        registerPage.registerUser("robert","jonson","robert@email.com",false);
        softAssert.assertAll();
    }
}
