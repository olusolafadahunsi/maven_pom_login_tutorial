package pages;

import org.openqa.selenium.WebDriver;

public class ManagersPage {
    private final WebDriver driver;

    public ManagersPage(WebDriver driver){
        this.driver= driver;
    }
    public String getManagersHomePageTitle(){
        return driver.getTitle();
    }
}
