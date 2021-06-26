package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.NoSuchElementException;

public class CartPage extends BasePage {

    private final static By button_checkout_by = By.id("checkout");
    private final static By button_continue_shopping_by = By.id("continue-shopping");

    private final static By cart_item_by = By.cssSelector(".cart_item");


    public CartPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    public boolean isPageOpened() {

        try {
           return getContinueShopping().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public WebElement getCheckout() {return driver.findElement(button_checkout_by);}
    public WebElement getCartItemByNumber(int number){return  driver.findElements(cart_item_by).get(number);}
    public WebElement getContinueShopping(){return driver.findElement(button_continue_shopping_by);}


    public void clickCheckoutButton(){getCheckout().click();}
    public void clickContinueShopping(){getContinueShopping().click();}


}
