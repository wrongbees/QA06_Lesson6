package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

public class CartPage extends BasePage {

    private final static String endpoint = "cart.html";

    private final static By button_checkout_by = By.id("checkout");
    private final static By button_continue_shopping_by = By.id("continue-shopping");

    private final static By cart_item_by = By.cssSelector(".cart_item");

    @FindBy(css = ".inventory_item_name")
    public List<WebElement> productNameList;


    private final static String product_price_by_name =
            "//*[text()='replace']/ancestor::div[@class = 'cart_item_label']//div[@class ='inventory_item_price']";


    public CartPage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);

    }

    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    public boolean isPageOpened() {

        try {
            return getContinueShopping().isDisplayed();

        } catch (NoSuchElementException e) {
            return false;
        }
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


    public CheckoutCartPage clickCheckoutButton() throws InterruptedException {
        logger.debug("Нажатие кнопки button_checkout_by");
        getCheckout().click();

        return new CheckoutCartPage(driver, false);
    }

    public void clickContinueShopping() {
        getContinueShopping().click();
    }

    public String getProductPriceByName(String name) {
        return driver.findElement(By.xpath(product_price_by_name.replace("replace", name))).getText();
    }

    public Map<String, String> getProductInTheCart() {

        logger.debug("Инициализация Map - продуктов");
        Map<String, String> productsInTheCart = new HashMap<>();
        List<WebElement> productList = productNameList;

        for (WebElement item : productList) {
            productsInTheCart.put(item.getText(), getProductPriceByName(item.getText()));
        }
        return productsInTheCart;
    }


}
