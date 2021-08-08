package baseEntities;

import core.BrowserService;
import core.ReadProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;
import utils.Listener;

@Listeners(Listener.class)
public class BaseTest {

    protected final Logger logger = LogManager.getLogger(this);
    public   WebDriver driver;
    protected ReadProperties properties;


    @BeforeTest
    public void setupTest(){

        properties = ReadProperties.createReadProperties();
        driver = new BrowserService().getDriver();
    }




    @AfterTest

    public void tearDownMethod(){
        driver.quit();
    }


}
