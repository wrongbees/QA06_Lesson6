package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Listener;

@Listeners(Listener.class)
public class BaseTest {

    public   WebDriver driver;
    protected ReadProperties properties;


    @BeforeTest
    public void setupTest(){
        //WebDriverManager.getInstance(DriverManagerType.CHROME).setup();

        properties = ReadProperties.createReadProperties();
        driver = new BrowserService().getDriver();
    }


//    @BeforeMethod
//    public void setupMethod(){
//        driver = new BrowserService().getDriver();
//    }

    @AfterTest
    // @AfterMethod
    public void tearDownMethod(){
        driver.quit();
    }


}
