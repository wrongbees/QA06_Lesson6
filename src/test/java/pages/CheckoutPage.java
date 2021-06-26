package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckoutPage extends BasePage {

    private final static By button_continue = By.id("continue");
    private final static By firstName_by = By.id("first-name");
    private final static By lastName_by = By.id("last-name");
    private final static By postalCode_by = By.id("postal-code");

    public CheckoutPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    public boolean isPageOpened() {
        try {
           return getContinueButton().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getContinueButton() {return driver.findElement(button_continue);}
    public WebElement getFirstName() {return driver.findElement(firstName_by);}
    public WebElement getLastName() {return driver.findElement(lastName_by);}
    public WebElement getPostalCode() {return driver.findElement(postalCode_by);}

    public void clickContinue() {getContinueButton().click();}
    public void setFirstName(String name) {getFirstName().sendKeys(name);}
    public void setLastName(String name) {getLastName().sendKeys(name);}
    public void setPostalCode(String code) {getPostalCode().sendKeys(code);}

}
