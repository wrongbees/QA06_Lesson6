package pages;

import baseEntities.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.FindBys;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class ProductsPage extends BasePage {

    private final static String endpoint = "inventory.html";

    @FindBy(className = "title")
    public WebElement title_label;

    // элементы верхнего левого меню
    @FindBy(id = "react-burger-menu-btn")
    public WebElement burger_menu;

    @FindBy(id = "logout_sidebar_link")
    public WebElement log_out;

    @FindBy(id = "reset_sidebar_link")
    public WebElement reset_field;

    // кнопка корзины
    @FindBy(className = "shopping_cart_link")
    public WebElement shopping_Cart_Button;

    @FindBy(className = "shopping_cart_badge")
    public WebElement shopping_Cart_Button_Message;

    // элементы окна сортировки товара.
    @FindBy(className = "product_sort_container")
    public WebElement product_Sort_Container;

    @FindBy(className = "product_sort_container>[value = 'az']")
    public WebElement sort_by_name_AZ;

    @FindBy(css = ".product_sort_container>[value = 'za']")
    public WebElement sort_by_name_ZA;

    @FindBy(css = ".product_sort_container>[value = 'lohi']")
    public WebElement sort_by_price_LoHi;

    @FindBy(css = ".product_sort_container>[value = 'hilo']")
    public WebElement sort_by_price_HiLo;

    // элементы  окон товара

    @FindBy(className = "inventory_item_name")
    public List<WebElement> inventory_item_name_list;

    @FindBy(className = "inventory_item_price")
    public List<WebElement> inventory_item_prise_list;

    @FindBy(className = "btn_inventory")
    public List<WebElement> inventory_item_add_button_list;

    @FindBy(className = "btn_secondary")
    public List<WebElement> inventory_item_remove_button_list;


    @FindBys({
            @FindBy(className = "inventory_item_name")
    })
    public List<WebElement> productList;

    public ProductsPage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);
    }

    protected void openPage() {
        driver.get(properties.getURL() + endpoint);
    }

    public boolean isPageOpened() {

        try {
            return title_label.isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

    public String getTitleText() {
        return title_label.getText();
    }

    // информация над корзиной
    public String getShoppingCartBadgeValue() {
        return shopping_Cart_Button_Message.getText();
    }

    // Возвращаем лист кнопок товара
    public List<WebElement> get_add_button_list() {
        return inventory_item_add_button_list;
    }

    // возвращаем лист имен товара
    public List<WebElement> getInventoryItemNamesList() {
        return inventory_item_name_list;
    }

    public List<WebElement> getInventoryItemPriceList() {
        return inventory_item_prise_list;
    }

    // возвращает кнопку элемента по имени
    public WebElement getAddToCartButton(String productName) {

        boolean isFound = false;
        for (WebElement element : productList) {
            String text = element.getText();

            if (text.equalsIgnoreCase(productName)) {
                isFound = !isFound;
                return getButton(element);
            }
        }
        if (!isFound) throw new NoSuchElementException("Выбрнный товар отсутствует в списке");
        return null;
    }

    private WebElement getButton(WebElement element) {
        return element.findElement(By.xpath("./ancestor::div[@class = 'inventory_item_description']//button"));
    }


    // кнопка add to cart по номеру
    public void clickInventory_item_add_button_by_number(int number) {
        inventory_item_add_button_list.get(number).click();
    }

    // кнопка remove to cart по номеру
    public void clickInventory_item_remove_button_by_number(int number) {
        inventory_item_remove_button_list.get(number).click();
    }

    //имя товара по номеру
    public String getInventory_item_name_by_number(int number) {
        return inventory_item_name_list.get(number).getText();
    }

    // цена товара по имени
    private WebElement getInventory_price_by_name(String name) {
        boolean isFound = false;
        for (WebElement element : productList) {
            String text = element.getText();

            if (text.equalsIgnoreCase(name)) {
                isFound = !isFound;
                return getPrice(element);
            }
        }
        if (!isFound) throw new NoSuchElementException("Выбрнный товар отсутствует в списке");
        return null;
    }

    private WebElement getPrice(WebElement element) {
        return element.findElement(By.xpath("./ancestor::div[@class = 'inventory_item_description']//div[@class = 'inventory_item_price']"));
    }

    public void clickLogout() throws InterruptedException {
        burger_menu.click();
        int time = 0;
        while (true || (time < 5)) {
            try {
                log_out.click();
                return;
            } catch (ElementNotInteractableException e) {
                Thread.sleep(1000);
                time++;
            }
        }
    }

    public void clickReset() throws InterruptedException {
        burger_menu.click();
        int time = 0;
        while (true || (time < 5)) {
            try {
                reset_field.click();
                return;
            } catch (ElementNotInteractableException e) {
                Thread.sleep(1000);
                time++;
            }
        }
    }

    public CartPage clickShoppingCartLink() throws InterruptedException {
        shopping_Cart_Button.click();

        return new CartPage(driver, false);
    }

    public void clickSortByName_az() {
        Select select = new Select(product_Sort_Container);
        select.selectByVisibleText(sort_by_name_AZ.getText());
    }

    public void clickSortByName_za() {
        Select select = new Select(product_Sort_Container);
        select.selectByVisibleText(sort_by_name_ZA.getText());
    }

    public void clickSortByPrice_hilo() {
        Select select = new Select(product_Sort_Container);
        select.selectByVisibleText(sort_by_price_HiLo.getText());
    }

    public void clickSortByPrice_lohi() {
        Select select = new Select(product_Sort_Container);
        select.selectByVisibleText(sort_by_price_LoHi.getText());
    }

    // добавление товара в корзину
    public void addToCart(String productName) {
        getAddToCartButton(productName).click();
    }

    public String getInventoryPrice(String productName) {
        return getInventory_price_by_name(productName).getText();
    }


}




