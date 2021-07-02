package tests;

import baseEntities.BaseTest;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.*;

import java.util.List;
import java.util.Map;
import java.util.Set;


public class SmokeTest extends BaseTest {
    @Test
    public void positiveLoginTest() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(), properties.getPassword());

        ProductsPage page = new ProductsPage(driver, false);
        Assert.assertEquals(page.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }

    @Test
    public void negativeLoginTests() {

        LoginStep loginStep = new LoginStep(driver);
        loginStep.login("standard", "secret");

        Assert.assertEquals(new LoginPage(driver, false).getErrorLabel().getText(),
                "Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void positiveTestForAddingAnItemToTheCart() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login();

        ProductsPage page = new ProductsPage(driver, false);

        for (int i = 0; i <= 5; i++) {
            page.clickInventory_item_add_button_by_number(i);
            Assert.assertEquals(Integer.parseInt(page.getShoppingCartBadgeValue()), i + 1);
        }

        for (int i = 0; i < 5; i++) {
            page.clickInventory_item_remove_button_by_number(0);

            Assert.assertEquals(Integer.parseInt(page.getShoppingCartBadgeValue()), 5 - i);
        }

        page.clickInventory_item_remove_button_by_number(0);
        boolean isDisplayed = true;
        try {
            page.getShoppingCartBadgeValue();
        } catch (NoSuchElementException e) {
            isDisplayed = false;
        }
        Assert.assertFalse(isDisplayed);
    }

    @Test
    public void positiveAcceptedUsernameTest() throws InterruptedException {
        String[] userName = {"standard_user", "problem_user", "performance_glitch_user", "locked_out_user"};

        LoginStep loginStep = new LoginStep(driver);
        LoginPage loginPage = new LoginPage(driver, true);

        for (String name : userName) {
            loginStep.login(name, properties.getPassword());

            try {
                loginPage.getLoginPageImage();

                System.out.println("User_name " + name + " не подходит.");
                throw new AssertionError();

            } catch (NoSuchElementException e) {

                ProductsPage products = new ProductsPage(driver, false);

                products.clickLogout();

            }
        }
    }

    @Test
    public void positivePaymentVerificationTest() {

        new LoginStep(driver).login();

        ProductsPage page = new ProductsPage(driver, false);

        String inventoryName = page.getInventory_item_name_by_number(2);
        new OrderStep(driver).orderOneProduct(inventoryName);

        page.clickShoppingCartLink();

        new CartReadyForCheckingStep(driver);

        new CheckoutPageStep(driver).checkoutContinue();

        new CheckoutOverviewPageFinishStep(driver);

        Assert.assertTrue(new CheckoutCompletePage(driver, false).getTitleLabel().isDisplayed());

    }

    @Test
    public void positiveSortingGoodsByName_ZATest() {
        new LoginStep(driver).login();

        ProductsPage page = new ProductsPage(driver, false);
        page.clickSortByName_za();

        List<WebElement> listItemName = page.getInventoryItemNamesList();
        for (int i = 0; i < listItemName.size() - 1; i++) {
            boolean result = listItemName.get(i).getText().compareTo(listItemName.get(i + 1).getText()) > 0;
            Assert.assertTrue(result, "После сортировки [Name (Z to A)] нужный порядок не достигнут");
        }

    }

    @Test
    public void positiveSortingGoodsByPrice_HiLoTest() {
        new LoginStep(driver).login();

        ProductsPage page = new ProductsPage(driver, false);
        page.clickSortByPrice_hilo();


        List<WebElement> listItemPrice = page.getInventoryItemPriceList();
        for (int i = 0; i < listItemPrice.size() - 1; i++) {

            double price_1 = Double.parseDouble(listItemPrice.get(i).getText().replace("$", ""));
            double price_2 = Double.parseDouble(listItemPrice.get(i + 1).getText().replace("$", ""));
            boolean result = (price_2 - price_1) <= 0;

            Assert.assertTrue(result, "После сортировки [Price (high to low)] нужный порядок не достигнут");
        }

    }

    @Test
    public void checkingTheItemInTheCartPositiveTest() {
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        new LoginStep(driver).login();

        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderOneProduct("Sauce Labs Bolt T-Shirt",
                "Sauce Labs Onesie", "Sauce Labs Backpack");
        Map<String, String> addedProducts = orderStep.getAddedProduct();// Мапа заказа

        new ProductsPage(driver, false).clickShoppingCartLink();

        CartPage cartPage = new CartPage(driver, false);
        Map<String, String> productInTheCart = cartPage.getProductInTheCart(); // Мапа из корзины


        Assert.assertEquals(productInTheCart.size(), addedProducts.size(),
                "Колличество выбранных продуктов не совпадает с добавленными в Cart");

        for (Map.Entry<String, String> product : addedProducts.entrySet()) {

            Assert.assertTrue(productInTheCart.containsKey(product.getKey()),
                    "В Cart не обнаружен продукт " + product.getKey());

           // System.out.println(productInTheCart.get(product.getKey()));

            Assert.assertEquals(productInTheCart.get(product.getKey()), product.getValue(),
                    "Не совпадает цена у продукта " + product.getKey());

        }
    }
    @Test
    public void checkingTheProductOnTheCheckoutOverviewPageTest(){
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        new LoginStep(driver).login();

        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderOneProduct("Sauce Labs Bolt T-Shirt",
                "Sauce Labs Onesie", "Sauce Labs Backpack");
        Map<String, String> addedProducts = orderStep.getAddedProduct();// Мапа заказа

        new ProductsPage(driver, false).clickShoppingCartLink();

        new CartReadyForCheckingStep(driver);

        new CheckoutPageStep(driver).checkoutContinue();

         CheckoutOverviewPage checkoutOverviewPage = new CheckoutOverviewPage(driver, false);
         Map<String,String> productsFromOverviewPage = checkoutOverviewPage.getProductInOverviewPage();

        Assert.assertEquals(productsFromOverviewPage.size(), addedProducts.size(),
                "Колличество выбранных продуктов не совпадает с добавленными в OverviewPage");

        for (Map.Entry<String, String> product : addedProducts.entrySet()) {

            Assert.assertTrue(productsFromOverviewPage.containsKey(product.getKey()),
                    "В OverviewPage не обнаружен продукт " + product.getKey());

            Assert.assertEquals(productsFromOverviewPage.get(product.getKey()), product.getValue(),
                    "Не совпадает цена у продукта " + product.getKey());

        }

        double expectedItemTotal = 0;
        for (Map.Entry<String, String> product : addedProducts.entrySet()) {
            expectedItemTotal += Double.parseDouble(product.getValue().replace("$",""));

        }

        double actualItemTotal = Double.parseDouble(checkoutOverviewPage.getSummarySubtotal());

        Assert.assertEquals(actualItemTotal,expectedItemTotal,"Item Total не совпадает с расчетной");


    }


}
