package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.nio.channels.Selector;

public class ProductsPage extends BasePage {

    private final static By title_label_By = By.className("title");
    private final static By addToCart_Button_By = By.id("add-to-cart-sauce-labs-bolt-t-shirt");
    private final static By shopping_cart_badge_message = By.cssSelector(" .shopping_cart_badge");
    private final static By filterSelector_cart_badge_message = By.cssSelector(" .product_sort_container");

    public ProductsPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);
    }


    protected void openPage() {
        driver.get("https://www.saucedemo.com/inventory.html");
    }

    public boolean isPageOpened() {

        try {
            return getTitleLabel().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public WebElement getTitleLabel() {
        return driver.findElement(title_label_By);
    }

    public String getTitleText() {
        return getTitleLabel().getText();
    }

    public WebElement getaddToCartButton(){ return driver.findElement(addToCart_Button_By);}

    public WebElement getShoppingCartBadgeMessage(){ return driver.findElement(shopping_cart_badge_message);}

    public String getShoppingCartBadgeValue(){ return getShoppingCartBadgeMessage().getText();}
}




