package login;
import base.BaseTests;
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
public class LoginTests extends BaseTests {

    @Test(priority = 1, alwaysRun = true)
    public void testInvalidUsernameAndPasswordLoginAttempt(){
        loginPage.acceptCookie();
        commonInvalidLoginSteps(invalidUsername,invalidPassword);
        assertTrue(loginPage.invalidLoginAlertText.contentEquals("User or Password is not valid"),
                "Invalid Username and Password Login Attempt Test not successful");
    }
    @Test(priority = 2,alwaysRun = true)
    public void testValidUsernameAndInvalidPasswordLoginAttempt(){
        commonInvalidLoginSteps(validUsername,invalidPassword);
        assertTrue(loginPage.invalidLoginAlertText.contentEquals("User or Password is not valid"),
                "Valid Username and Invalid Password Login Attempt Test not successful");
    }
    @Test(priority = 3, alwaysRun = true)
    public void testInvalidUsernameAndValidPasswordLoginAttempt(){
        commonInvalidLoginSteps(invalidUsername,validPassword);
        assertTrue(loginPage.invalidLoginAlertText.contentEquals("User or Password is not valid"),
                "Invalid Username and valid Password Login Attempt Test not successful");
    }
    @Test (priority = 4, alwaysRun = true)
    public void testEmptyUsernameAndPasswordLoginAttempt(){
        loginPage.clickLogin();
        loginPage.invalidLoginAttemptMessage();
        loginPage.acceptAlertMessage();
        assertTrue(loginPage.invalidLoginAlertText.contentEquals("User or Password is not valid"),
                "Empty Username and Password Login Attempt Test not successful");
}
    @Test(priority = 5,alwaysRun = true)
    public void testSuccessfulLogin(){
        loginPage.inputUsername(validUsername);
        loginPage.inputPassword(validPassword);
        var managersHomePage= loginPage.clickLogin();
        assertTrue(managersHomePage.getManagersHomePageTitle().contentEquals("Guru99 Bank Manager HomePage"),
                "Login not Successful");
    }

    public void commonInvalidLoginSteps(String userName, String password){
        loginPage.inputUsername(userName);
        loginPage.inputPassword(password);
        loginPage.clickLogin();
        loginPage.invalidLoginAttemptMessage();
        loginPage.acceptAlertMessage();
    }
}
