package baseEntities;

import core.ReadProperties;
import org.openqa.selenium.WebDriver;

public abstract class BasePage {
    protected static final int WAIT_FOR_PAGE_LOADING_SEC = 15;
    protected WebDriver driver;
    protected ReadProperties properties;


    protected abstract void openPage();
    public abstract boolean isPageOpened() throws InterruptedException;

    public BasePage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        this.driver = driver;
        properties = ReadProperties.createReadProperties();

        if (openPageByUrl) {
            openPage();
        }

        waitForOpen();
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
