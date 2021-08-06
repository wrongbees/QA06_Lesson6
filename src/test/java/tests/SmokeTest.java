package tests;

import baseEntities.BaseTest;
import io.qameta.allure.*;
import models.Credentials;
import models.CredentialsBuilder;
import models.User;
import models.UserBuilder;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginStep;
import steps.OrderStep;

import java.util.List;
import java.util.Map;

/**
 * ************************** HOME WORK 15 **********************************
 * a. Page Factory
 * Паттерн Page Factory реализован в LoginPage, ProductPage.
 * <p>
 * b. Chain Of Invocations - на уровне страниц
 * После долгих размышлений было принято решение ( на мой взгляд правильное)-
 * не перегружать page методами со сложной логикой и реализовать только методы
 * возвращающие steps. В степах будут находится методы возвращающие pages.
 * <p>
 * c. Value Object
 * модель models.Credentials - используется для логировния на входе.
 * модель models.User - для регистрации при оплате.
 * <p>
 * d. Builder
 * models.CredentialsBuilder - класс используемый для сборки Credentials.
 * models.UserBuilder - класс используемый для сборки User.
 */
public class SmokeTest extends BaseTest {

    @Link(value = "www.saucedemo.com", url = "https://www.saucedemo.com/")
    @Description("Вход на https://www.saucedemo.com/")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Вход на https://www.saucedemo.com/")
    public void positiveLoginTest() throws InterruptedException {

        Credentials credentials = new CredentialsBuilder()
                .setUserName("standard_user")
                .setPassword("secret_sauce")
                .build();

        ProductsPage page = (ProductsPage) new LoginStep(driver).login(credentials);

        Assert.assertEquals(page.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }

    @Link(name = "kontakty/", type = "my link")
    @Severity(SeverityLevel.MINOR)
    @Test

    public void negativeLoginTests() throws InterruptedException {

        Credentials credentials = new CredentialsBuilder()
                .setUserName("standard")
                .setPassword("secret")
                .build();


        LoginPage loginPage = (LoginPage) new LoginStep(driver).login(credentials);

        Assert.assertEquals(loginPage.getErrorLabel().getText(),
                "Epic sadface: Username and password do not match any user in this service");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Тест на добавление продукта в корзину")
    @Test(description = "Тест на добавление продукта в корзину")
    public void positiveTestForAddingAnItemToTheCart() throws InterruptedException {
        Credentials credentials = new CredentialsBuilder()
                .setUserName("standard_user")
                .setPassword("secret_sauce")
                .build();

        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login(credentials);

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

    @TmsLink("1")
    @Issue("11")
    @Test(description = "Проверка логирования различными userNames")
    public void positiveAcceptedUsernameTest() throws InterruptedException {

        Credentials credentials = new CredentialsBuilder()
                .setUserNameList("standard_user", "problem_user")
                .setUserNameList("performance_glitch_user", "locked_out_user")
                .setPassword("secret_sauce")
                .build();

        LoginStep loginStep = new LoginStep(driver);
        LoginPage loginPage = new LoginPage(driver, true);

        for (String name : credentials.getUserNameList()) {
            loginStep.login(name, credentials.getPassword());

            try {
                loginPage.loginButton.isDisplayed();

                System.out.println("User_name " + name + " не подходит.");
                throw new AssertionError();

            } catch (NoSuchElementException e) {

                ProductsPage products = new ProductsPage(driver, false);

                products.clickLogout();

            }
        }
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка функции оплты")
    @Test(description = "Проверка функции оплты")
    public void positivePaymentVerificationTest() throws InterruptedException {

        Credentials credentials = new CredentialsBuilder()
                .setUserName("standard_user")
                .setPassword("secret_sauce")
                .build();


        User user = new UserBuilder()
                .setFirstName("Vladimir")
                .setLastName("Vinnik")
                .setZip("12345")
                .build();

        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login(credentials);

        String inventoryName = page.getInventory_item_name_by_number(2);

        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderProducts(inventoryName);

        CheckoutCompletePage checkoutCompletePage = page
                .clickShoppingCartLink()                //CartPage
                .clickCheckoutButton()                  //CheckoutCartPage
                .goToCheckoutPageStep()                 //CheckoutPageStep
                .checkoutContinue(user)                     //CheckoutOverviewPage
                .goToCheckoutOverviewPageFinishStep()   //CheckoutOverviewPageFinishStep
                .clickFinishButton();                   //CheckoutCompletePage


        Assert.assertTrue(checkoutCompletePage.getTitleLabel().isDisplayed());

    }

    @Feature("SortingGoodsByName_ZA")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка на сортировку продуктов по названию")
    @Test(description = "Проверка на сортировку продуктов по названию")
    public void positiveSortingGoodsByName_ZATest() throws InterruptedException {
        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login();

        page.clickSortByName_za();

        List<WebElement> listItemName = page.getInventoryItemNamesList();
        for (int i = 0; i < listItemName.size() - 1; i++) {
            boolean result = listItemName.get(i).getText().compareTo(listItemName.get(i + 1).getText()) > 0;
            Assert.assertTrue(result, "После сортировки [Name (Z to A)] нужный порядок не достигнут");
        }

    }

    @Feature("SortingGoodsByPrice_HiLo")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка на сортировку продуктов по цене")

    @Test(description = "Проверка на сортировку продуктов по цене")
    public void positiveSortingGoodsByPrice_HiLoTest() throws InterruptedException {
        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login();

        page.clickSortByPrice_hilo();


        List<WebElement> listItemPrice = page.getInventoryItemPriceList();
        for (int i = 0; i < listItemPrice.size() - 1; i++) {

            double price_1 = Double.parseDouble(listItemPrice.get(i).getText().replace("$", ""));
            double price_2 = Double.parseDouble(listItemPrice.get(i + 1).getText().replace("$", ""));
            boolean result = (price_2 - price_1) <= 0;

            Assert.assertTrue(result, "После сортировки [Price (high to low)] нужный порядок не достигнут");
        }

    }

    @Severity(SeverityLevel.MINOR)
    @Description("Проверка соответствия выбираемых продуктов, продуктам, отображаемым в корзине")
    @Test(description = "Проверка соответствия выбираемых продуктов, продуктам, отображаемым в корзине")
    public void checkingTheItemInTheCartPositiveTest() throws InterruptedException {
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login();

        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderProducts("Sauce Labs Bolt T-Shirt",
                "Sauce Labs Onesie", "Sauce Labs Backpack");
        Map<String, String> addedProducts = orderStep.getAddedProduct();// Мапа заказа

        // ProductsPage productsPage = new ProductsPage(driver, false);
        page.clickShoppingCartLink();

        CartPage cartPage = new CartPage(driver, false);
        Map<String, String> productInTheCart = cartPage.getProductInTheCart(); // Мапа из корзины


        Assert.assertEquals(productInTheCart.size(), addedProducts.size(),
                "Колличество выбранных продуктов не совпадает с добавленными в Cart");

        for (Map.Entry<String, String> product : addedProducts.entrySet()) {

            Assert.assertTrue(productInTheCart.containsKey(product.getKey()),
                    "В Cart не обнаружен продукт " + product.getKey());


            Assert.assertEquals(productInTheCart.get(product.getKey()), product.getValue(),
                    "Не совпадает цена у продукта " + product.getKey());

        }
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Проверка правильного отображения информации перед оплатой")
    @Test(description = "Проверка правильного отображения информации перед оплатой")
    public void checkingTheProductOnTheCheckoutOverviewPageTest() throws InterruptedException {
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        ProductsPage productsPage = (ProductsPage) new LoginStep(driver).login();


        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderProducts("Sauce Labs Bike Light",
                "Sauce Labs Fleece Jacket");
        Map<String, String> addedProducts = orderStep.getAddedProduct();// Мапа заказа

        CheckoutOverviewPage checkoutOverviewPage = productsPage
                .clickShoppingCartLink()
                .clickCheckoutButton()
                .goToCheckoutPageStep()
                .checkoutContinue();

        Map<String, String> productsFromOverviewPage = checkoutOverviewPage.getProductInOverviewPage();

        Assert.assertEquals(productsFromOverviewPage.size(), addedProducts.size(),
                "Колличество выбранных продуктов не совпадает с добавленными в OverviewPage");

        for (Map.Entry<String, String> product : addedProducts.entrySet()) {

            Assert.assertTrue(productsFromOverviewPage.containsKey(product.getKey()),
                    "В OverviewPage не обнаружен продукт " + product.getKey());

            Assert.assertEquals(productsFromOverviewPage.get(product.getKey()), product.getValue(),
                    "Не совпадает цена у продукта " + product.getKey());

        }

        float expectedItemTotal = 0;
        for (Map.Entry<String, String> product : addedProducts.entrySet()) {
            expectedItemTotal += Float.parseFloat(product.getValue().replace("$", ""));

        }

        float actualItemTotal = Float.parseFloat(checkoutOverviewPage.getSummarySubtotal());

        Assert.assertEquals(actualItemTotal, expectedItemTotal, "Item Total не совпадает с расчетной");

        float tax = Float.parseFloat(checkoutOverviewPage.getTaxLabel());
        float actualTotal = Float.parseFloat(checkoutOverviewPage.getTotalLabel());
        float expectedTotal = expectedItemTotal + tax;

        Assert.assertEquals(actualTotal, expectedTotal, "Сумма к оплате Total рассчитана неверно");


    }


}
