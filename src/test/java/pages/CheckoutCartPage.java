package pages;

import baseEntities.BasePage;
import models.User;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import steps.CheckoutPageStep;

public class CheckoutCartPage extends BasePage {

    private final static String endpoint = "checkout-step-one.html";

    private final static By button_continue = By.id("continue");
    private final static By firstName_by = By.id("first-name");
    private final static By lastName_by = By.id("last-name");
    private final static By postalCode_by = By.id("postal-code");

    public CheckoutCartPage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get(properties.getURL() + endpoint);

    }

    public boolean isPageOpened() {
        try {
            return getContinueButton().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getContinueButton() {
        return driver.findElement(button_continue);
    }

    public WebElement getFirstName() {
        return driver.findElement(firstName_by);
    }

    public WebElement getLastName() {
        return driver.findElement(lastName_by);
    }

    public WebElement getPostalCode() {
        return driver.findElement(postalCode_by);
    }

    public void clickContinue() {
        getContinueButton().click();
    }

    public void setUserParameters(User user) {
        getFirstName().sendKeys(user.getFirstname());
        getLastName().sendKeys(user.getLastname());
        getPostalCode().sendKeys(user.getZip());
    }

    public CheckoutPageStep goToCheckoutPageStep() throws InterruptedException {
        return new CheckoutPageStep(driver);
    }

}
