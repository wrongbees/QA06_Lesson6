package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import models.Products;
import org.openqa.selenium.WebDriver;
import pages.ProductsPage;

import java.util.HashMap;
import java.util.Map;

public class OrderStep extends BaseStep {
    private Map<String, String> productMap = new HashMap<>();

    public OrderStep(WebDriver driver) {
        super(driver);
    }

    @Step("Формируем заказ")
    public void orderProducts(String... productNames) throws InterruptedException {
        logger.error("Выполнение step "+this+" orderProducts()");
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.clickReset();

        for (String name : productNames) {

            productsPage.addToCart(name);
            productMap.put(name, productsPage.getInventoryPrice(name));

        }

    }
    @Step("Формируем заказ")
    public void orderProducts(Products products) throws InterruptedException {
        logger.error("Выполнение step "+this+" orderProducts()  с параметром Products");
        ProductsPage productsPage = new ProductsPage(driver, true);
        productsPage.clickReset();

        for (String name : products.getProductList()) {

            productsPage.addToCart(name);
            productMap.put(name, productsPage.getInventoryPrice(name));

        }

    }

    @Step("Возвращаем Map из заказанных продуктов")
    public Map<String, String> getAddedProduct() {
        logger.error("Выполнение step "+this+" getAddedProduct()");
        return productMap;
    }


}
