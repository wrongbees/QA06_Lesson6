package baseEntities;

import core.ReadProperties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    protected final Logger logger = LogManager.getLogger(this);
    protected static final int WAIT_FOR_PAGE_LOADING_SEC = 15;
    protected WebDriver driver;
    protected ReadProperties properties;


    protected abstract void openPage();
    public abstract boolean isPageOpened() throws InterruptedException;

    public BasePage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        this.driver = driver;
        properties = ReadProperties.getInstance();
        PageFactory.initElements(this.driver, this);

        if (openPageByUrl) {
            openPage();
        }

        waitForOpen();
        logger.info("Страница "+this.getClass()+" успешно открылась....");
    }

    protected void waitForOpen() throws InterruptedException {
        int secondsCount = 0;
        boolean isPageOpenedIndicator = isPageOpened();

        while (!isPageOpenedIndicator && secondsCount < WAIT_FOR_PAGE_LOADING_SEC) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            secondsCount++;
            isPageOpenedIndicator = isPageOpened();
        }

        if (!isPageOpenedIndicator) {
            throw new AssertionError("Page was not opened.");
        }
    }
}
