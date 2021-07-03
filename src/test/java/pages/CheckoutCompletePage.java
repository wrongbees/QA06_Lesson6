package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutCompletePage extends BasePage {

    private final static By title_label_by = By.cssSelector(".title");
    private final static By back_home_button_by = By.id("back-to-products");

    public CheckoutCompletePage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get("https://www.saucedemo.com/checkout-complete.html");
    }

    public boolean isPageOpened() {
        try {
            return getBackHomeButton().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getTitleLabel() {
        return driver.findElement(title_label_by);
    }

    public WebElement getBackHomeButton() {
        return driver.findElement(back_home_button_by);
    }


    public void clickBackHome() {getBackHomeButton().click();}

}
