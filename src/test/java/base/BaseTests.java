package base;
import com.google.common.io.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import pages.LoginPage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
public class BaseTests {
    private WebDriver driver;
    private String browser;
    public  String url;
    public String validUsername;
    public String validPassword;
    public String invalidUsername;
    public String invalidPassword;
    protected LoginPage loginPage;
   @BeforeClass
   public void setup(){
       initiateSelectedBrowser();
       //Instantiate and pass the initiated driver to the LoginPage class
       loginPage = new LoginPage(driver);
       driver.get(url);
   }

    /**
     * Records a test failure by taking a screenshot of the current state of
     * the browser window and saving it to the "test-output/screenshots" directory
     * @param result The result of the test Method
     */
   @AfterMethod
   public void captureFailure(ITestResult result){
       if (ITestResult.FAILURE == result.getStatus()){
           // Take a screenshot of the current state of the browser window.
           var camera = (TakesScreenshot)driver;
           File screenshot = camera.getScreenshotAs(OutputType.FILE);
           // Move the screenshot to the "test-output/screenshots" directory.
           try{
               Files.move(screenshot, new File("test-output/screenshots/" + result.getName() +".png"));
           }catch (IOException e){
               e.printStackTrace();
           }
       }
   }

    /**
     * This ends and exits the browser session after all tests within a class
     * has been executed.
     */
    @AfterClass
    public void tearDown(){
        driver.quit();
    }

    /**
     * This method helps instantiate the desired Web browser based on the specified value
     * in the browser parameter in the config.properties file located in this path "resources/config.properties".
     */
    private void initiateSelectedBrowser(){
       readPropertiesConfigurations();
       if (browser.equalsIgnoreCase("chrome")){
           WebDriverManager.chromedriver().setup();
           driver = new ChromeDriver();
       } else if (browser.equalsIgnoreCase("firefox")){
           WebDriverManager.firefoxdriver().setup();
           driver = new FirefoxDriver();
       } else if (browser.equalsIgnoreCase("edge")){
           WebDriverManager.edgedriver().setup();
           driver = new EdgeDriver();
       } else {
           throw new IllegalArgumentException("Invalid browser type: "+ browser);
       }
   }

    /**
     * This method is used to retrieve details specified in config.properties file.git
     * Set browser value to either chrome,firefox or edge to run the test on the desired browser.
     */
   private void readPropertiesConfigurations() {
       Properties props = new Properties();
       FileInputStream file;
       try {
           file = new FileInputStream("src/test/resources/config.properties");
           props.load(file);
           //retrieves and specifies the browser specified in the config.properties file
           browser= props.getProperty("browser");
           url = props.getProperty("url");
           validUsername= props.getProperty("validUsername");
           validPassword = props.getProperty("validPassword");
           invalidUsername = props.getProperty("invalidUsername");
           invalidPassword= props.getProperty("invalidPassword");
       } catch (IOException e) {
           throw new RuntimeException(e);
       }
   }
    }

