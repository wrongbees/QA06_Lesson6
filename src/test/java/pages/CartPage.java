package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

public class CartPage extends BasePage {
    private static CartPage cartPage;

    private final static By button_checkout_by = By.id("checkout");
    private final static By button_continue_shopping_by = By.id("continue-shopping");

    private final static By cart_item_by = By.cssSelector(".cart_item");
    private final static By product_name_by = By.cssSelector(".inventory_item_name");

    private final static String product_price_by_name =
            "//*[text()='replace']/ancestor::div[@class = 'cart_item_label']//div[@class ='inventory_item_price']";


    private CartPage(WebDriver driver, boolean openPageByUrl) {
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

    public static CartPage createCartPage(WebDriver driver, boolean openPageByUrl) {
        if (cartPage == null) {
            return new CartPage(driver, openPageByUrl);
        }
        return cartPage;
    }

    public WebElement getCheckout() {
        return driver.findElement(button_checkout_by);
    }

    public WebElement getCartItemByNumber(int number) {
        return driver.findElements(cart_item_by).get(number);
    }

    public WebElement getContinueShopping() {
        return driver.findElement(button_continue_shopping_by);
    }

    public List<WebElement> getProductNameList() {
        return driver.findElements(product_name_by);
    }


    public void clickCheckoutButton() {
        getCheckout().click();
    }

    public void clickContinueShopping() {
        getContinueShopping().click();
    }

    public String getProductPriceByName(String name) {
        return driver.findElement(By.xpath(product_price_by_name.replace("replace", name))).getText();
    }

    public Map<String, String> getProductInTheCart() {

        Map<String, String> productsInTheCart = new HashMap<>();
        List<WebElement> productList = getProductNameList();

        for (WebElement item : productList) {
            productsInTheCart.put(item.getText(), getProductPriceByName(item.getText()));
        }
        return productsInTheCart;
    }
}
