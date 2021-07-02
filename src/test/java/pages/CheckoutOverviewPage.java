package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckoutOverviewPage extends BasePage {

    private final static By finish_button_by = By.id("finish");
    private final static By cancel_button_by = By.id("cancel");
    private final static By title_label_by = By.cssSelector(".label");
    private final static By product_name_by = By.cssSelector(".inventory_item_name");
    private final static By summary_subtotal_label = By.cssSelector(".summary_subtotal_label");
    private final static By summary_total_label = By.cssSelector(".summary_total_label");

    private final static String product_price_by_name =
            "//*[text()='replace']/ancestor::div[@class='cart_item_label']//div[@class='inventory_item_price']";


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

    public List<WebElement> getProductNameList() {
        return driver.findElements(product_name_by);
    }

    public void clickFinishButton() {
        getFinishButton().click();
    }

    public void clickCancelButton() {
        getCancelButton().click();
    }

    public String getSummarySubtotal() {
        return driver.findElement(summary_subtotal_label).getText().replace("Item total: $", "");
    }

    public String getSummaryTotalLabel() {
        return driver.findElement(summary_total_label).getText().replace("Total: $","");
    }


    public String getTitleText() {
        return getTitleLabel().getText();
    }

    private String getProductPriceByName(String name) {
        return driver.findElement(By.xpath(product_price_by_name
                .replace("replace", name))).getText();
    }

    public Map<String, String> getProductInOverviewPage() {

        Map<String, String> productInOverviewPage = new HashMap<>();
        List<WebElement> productList = getProductNameList();

        for (WebElement item : productList) {
            productInOverviewPage.put(item.getText(), getProductPriceByName(item.getText()));
        }
        return productInOverviewPage;
    }
}
