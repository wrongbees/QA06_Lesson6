package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutOverviewPage extends BasePage {
    private final static By finish_button_by = By.id("finish");
    private final static By cancel_button_by = By.id("cancel");
    private final static By title_label_by = By.cssSelector(".label");

    public CheckoutOverviewPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get("https://www.saucedemo.com/checkout-step-two.html");
    }

    public boolean isPageOpened() {
        try {
           return getFinishButton().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getFinishButton() {
        return driver.findElement(finish_button_by);
    }

    public WebElement getCancelButton() {
        return driver.findElement(cancel_button_by);
    }

    public WebElement getTitleLabel() {
        return driver.findElement(title_label_by);
    }

    public void clickFinishButton() {
        getFinishButton().click();
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public String getTitleText() {
        return getTitleLabel().getText();
    }
}
