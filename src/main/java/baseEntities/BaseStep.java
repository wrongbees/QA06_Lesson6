package baseEntities;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

public class BaseStep {

    protected final Logger logger = LogManager.getLogger(this);
    protected WebDriver driver;
    public BaseStep(WebDriver driver) {
        this.driver = driver;
    }
}
