/**
 * Test automation framework designed by Olusola Fadahunsi
 * This is a basic framework to help illustrate how to design POM framework using SeleniumWebDriverManager (eliminates
 * the need to download multiple drivers into the project), Maven (to manage required resources) and TestNG (as the
 * assertion tool for our test scenarios)
 * This is a basic introduction, simulating login test scenario only.I also assume that the target audience has
 * some basic understanding of Java Programming, Some understanding of system design and architecture principles such as
 * DRY (Do not Repeat Yourself)
 */
package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final By userid = By.name("uid");
    private final By passwordId = By.name("password");
    private final By loginButton = By.name("btnLogin");

    public String invalidLoginAlertText;
    public LoginPage(WebDriver driver){
        this.driver= driver;
    }

    public void invalidLoginAttemptMessage(){
        invalidLoginAlertText =driver.switchTo().alert().getText();
    }

    public void acceptAlertMessage(){
        driver.switchTo().alert().accept();
    }

    /**
     *
     * @return ManagersPage is returned upon clicking login
     */
    public ManagersPage clickLogin(){
        driver.findElement(loginButton).click();
        return new ManagersPage(driver);
    }

    public void inputUsername(String username){
        driver.findElement(userid).clear();
        driver.findElement(userid).sendKeys(username);
    }
    public void inputPassword(String password){
        driver.findElement(passwordId).clear();
        driver.findElement(passwordId).sendKeys(password);
    }

    public void acceptCookie(){
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        driver.switchTo().frame("gdpr-consent-notice");
        driver.findElement(By.id("save")).click();
        driver.switchTo().parentFrame();
    }


}
