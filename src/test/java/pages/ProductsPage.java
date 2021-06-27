package pages;

import baseEntities.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.Select;

import java.util.List;


public class ProductsPage extends BasePage {

    private final static By title_label_By = By.className("title");
    // элементы верхнего левого меню
    private final static By burger_menu_btn = By.id("react-burger-menu-btn");
    private final static By logout = By.id("logout_sidebar_link");
    // кнопка корзины
    private final static By shopping_cart_button = By.cssSelector(".shopping_cart_link");
    private final static By shopping_cart_badge_message = By.cssSelector(" .shopping_cart_badge");
    // элементы окна сортировки товара.
    private final static By product_sort_container = By.cssSelector(" .product_sort_container");
    private final static By sort_by_name_az = By.cssSelector(".product_sort_container>[value = 'az'] ");
    private final static By sort_by_name_za = By.cssSelector(".product_sort_container>[value = 'za'] ");
    private final static By sort_by_price_lohi = By.cssSelector(".product_sort_container>[value = 'lohi'] ");
    private final static By sort_by_price_hilo = By.cssSelector(".product_sort_container>[value = 'hilo'] ");
    // элементы  окон товара
    private final static By inventory_item_name = By.cssSelector(".inventory_item_name");
    private final static By inventory_item_price = By.cssSelector(".inventory_item_price");
    private final static By inventory_item_add_button = By.cssSelector(".btn_inventory");
    private final static By inventory_item_remove_button = By.cssSelector(".btn_secondary");

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

    public WebElement getShoppingCartBadgeMessage() {
        return driver.findElement(shopping_cart_badge_message);
    }

    // информация над корзиной
    public String getShoppingCartBadgeValue() {
        return getShoppingCartBadgeMessage().getText();
    }

    // поле выбора сортировки и его содержимое
    public WebElement getProductSortContainer() {
        return driver.findElement(product_sort_container);
    }

    public WebElement getSortByName_az() {
        return driver.findElement(sort_by_name_az);
    }

    public WebElement getSortByName_za() {
        return driver.findElement(sort_by_name_za);
    }

    public WebElement getSortByPrice_lohi() {
        return driver.findElement(sort_by_price_lohi);
    }

    public WebElement getSortByPrice_hilo() {
        return driver.findElement(sort_by_price_hilo);
    }

    public WebElement getShoppingCartLink() {
        return driver.findElement(shopping_cart_button);
    }

    public WebElement getBurger_menu_btn() {
        return driver.findElement(burger_menu_btn);
    }

    public WebElement getLogout() {
        return driver.findElement(logout);
    }

    // Возвращаем лист кнопок товара
    public List<WebElement> get_add_button_list() {
        return driver.findElements(inventory_item_add_button);
    }
    // возвращаем лист имен товара
    public List<WebElement> getInventoryItemNamesList() {
        return driver.findElements(inventory_item_name);
    }

    public List<WebElement> getInventoryItemPriceList() {
        return driver.findElements(inventory_item_price);
    }

    // кнопка add to cart по номеру
    public void clickInventory_item_add_button_by_number(int number) {
        driver.findElements(inventory_item_add_button).get(number).click();
    }

    // кнопка remove to cart по номеру
    public void clickInventory_item_remove_button_by_number(int number) {
        driver.findElements(inventory_item_remove_button).size();
        driver.findElements(inventory_item_remove_button).get(number).click();
    }

    //имя товара по номеру
    public WebElement getInventory_item_name_by_number(int number) {
        return driver.findElements(inventory_item_name).get(number);
    }

    // цена товара по элементу
    public WebElement getInventory_price_name_by_number(int number) {
        return driver.findElements(inventory_item_price).get(number);
    }

    public void clickLogout() throws InterruptedException {
        getBurger_menu_btn().click();
        int time = 0;
        while (true || (time < 5)) {
            try {
                getLogout().click();
                return;
            } catch (ElementNotInteractableException e) {
                Thread.sleep(1000);
                time++;
            }
        }
    }

    public void clickShoppingCartLink() {getShoppingCartLink().click();}

    public void clickSortByName_az(){
        Select select = new Select(getProductSortContainer());
        select.selectByVisibleText(getSortByName_az().getText());
    }

    public void clickSortByName_za(){
        Select select = new Select(getProductSortContainer());
        select.selectByVisibleText(getSortByName_za().getText());
    }

    public void clickSortByPrice_hilo(){
        Select select = new Select(getProductSortContainer());
        select.selectByVisibleText(getSortByPrice_hilo().getText());
    }

    public void clickSortByPrice_lohi(){
        Select select = new Select(getProductSortContainer());
        select.selectByVisibleText(getSortByPrice_lohi().getText());
    }


}




