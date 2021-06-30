package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;

public class BaseTest {
    protected  WebDriver driver;
    protected ReadProperties properties;

    @BeforeTest
    public void setupTest(){
        WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
        properties = ReadProperties.createReadProperties();
    }


    @BeforeMethod
    public void setupMethod(){
        driver = new BrowserService().getDriver();
    }

    @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }
}
