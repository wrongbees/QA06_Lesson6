package tests;

import baseEntities.BaseTest;
import io.qameta.allure.*;
import models.Credentials;
import models.Products;
import models.User;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginStep;
import steps.OrderStep;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * ************************** HOME WORK 16 **********************************
 * 1. Переделать все существующие тесты для сайта SauceDemo на
 * использование степов с использованием паттерна Value Objects
 * <p>
 * Модели используемые в паттерне Value Objects - models.Credentials и
 * models.User., models.Products,
 * .....У меня по lombok есть вопросы.........Я так понимаю, что аннотцию @Builder
 * мы применяем,
 * когда не требуется сложная иницилизация полей создоваемого объекта????
 * <p>
 * 2.Реализовать необходимые модели в виде классов с использованием Lombok
 * <p>
 * модель models.Credentials - используется для логировния на входе.
 * модель models.User - для регистрации при оплате.
 * модель models.Products - для выбора набора продуктов при покупке.
 * <p>
 * 3.Добавить Builder во все необходимые классы моделей
 * <p>
 * Добавлял и плакал.
 *
 * 4.Добавить логирование во всех страницах
 *
 * 5.Настроить вывод логов в файл
 *  Почему то файл создаётся с именем ${filename} и не там??????????????
 *
 * 6.Настроить выполнение тестов при уровне логирования Debug
 */
public class SmokeTest extends BaseTest {


    @Link(value = "www.saucedemo.com", url = "https://www.saucedemo.com/")
    @Description("Вход на https://www.saucedemo.com/")
    @Severity(SeverityLevel.BLOCKER)
    @Test(description = "Вход на https://www.saucedemo.com/")
    public void positiveLoginTest() throws InterruptedException {
        logger.fatal("Начало positiveLoginTest() ");

        Credentials credentials = Credentials.builder()
                .userName("standard_user")
                .password("secret_sauce")
                .build();

        ProductsPage page = (ProductsPage) new LoginStep(driver).login(credentials);

        Assert.assertEquals(page.getTitleText(), "PRODUCTS", "Страница Products не открылась");

        logger.fatal("Конец positiveLoginTest()");

    }

    @Link(name = "kontakty/", type = "my link")
    @Severity(SeverityLevel.MINOR)
    @Test

    public void negativeLoginTests() throws InterruptedException {
        logger.fatal("Начало negativeLoginTests()");

        Credentials credentials = Credentials.builder()
                .userName("standard")
                .password("secret")
                .build();


        LoginPage loginPage = (LoginPage) new LoginStep(driver).login(credentials);

        Assert.assertEquals(loginPage.getErrorLabel().getText(),
                "Epic sadface: Username and password do not match any user in this service");

        logger.fatal("Конец negativeLoginTests()");

    }

    @Severity(SeverityLevel.NORMAL)
    @Description("Тест на добавление продукта в корзину")
    @Test(description = "Тест на добавление продукта в корзину")
    public void positiveTestForAddingAnItemToTheCart() throws InterruptedException {

        logger.fatal("Начало positiveTestForAddingAnItemToTheCart()");

        Credentials credentials = Credentials.builder()
                .userName("standard_user")
                .password("secret_sauce")
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

        logger.fatal("Конец positiveTestForAddingAnItemToTheCart()");
    }

    @TmsLink("1")
    @Issue("11")
    @Test(description = "Проверка логирования различными userNames")
    public void positiveAcceptedUsernameTest() throws InterruptedException {
        logger.fatal("Начало positiveAcceptedUsernameTest()");

        String[] user = new String[]{"standard_user", "standard_user", "locked_out_user",
                "problem_user", "performance_glitch_user"};

        Credentials credentials = Credentials.builder()
                .userNameList(Arrays.asList(user))
                .password("secret_sauce")
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
        logger.fatal("Конец positiveAcceptedUsernameTest()");
    }

    @Severity(SeverityLevel.CRITICAL)
    @Description("Проверка функции оплты")
    @Test(description = "Проверка функции оплты")
    public void positivePaymentVerificationTest() throws InterruptedException {
        logger.fatal("Начало positivePaymentVerificationTest()");

        Credentials credentials = Credentials.builder()
                .userName("standard_user")
                .password("secret_sauce")
                .build();


        User user = User.builder()
                .firstname("Vladimir")
                .lastname("Vinnik")
                .zip("12345")
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

        logger.fatal("Конец positivePaymentVerificationTest()");
    }

    @Feature("SortingGoodsByName_ZA")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка на сортировку продуктов по названию")
    @Test(description = "Проверка на сортировку продуктов по названию")
    public void positiveSortingGoodsByName_ZATest() throws InterruptedException {
        logger.fatal("Начало positiveSortingGoodsByName_ZATest()");
        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login();

        page.clickSortByName_za();

        List<WebElement> listItemName = page.getInventoryItemNamesList();
        for (int i = 0; i < listItemName.size() - 1; i++) {
            boolean result = listItemName.get(i).getText().compareTo(listItemName.get(i + 1).getText()) > 0;
            Assert.assertTrue(result, "После сортировки [Name (Z to A)] нужный порядок не достигнут");
        }
        logger.fatal("Конец positiveSortingGoodsByName_ZATest()");
    }

    @Feature("SortingGoodsByPrice_HiLo")
    @Severity(SeverityLevel.MINOR)
    @Description("Проверка на сортировку продуктов по цене")

    @Test(description = "Проверка на сортировку продуктов по цене")
    public void positiveSortingGoodsByPrice_HiLoTest() throws InterruptedException {
        logger.fatal("Начало positiveSortingGoodsByPrice_HiLoTest()");
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
        logger.fatal("Конец positiveSortingGoodsByPrice_HiLoTest()");
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Проверка соответствия выбираемых продуктов, продуктам, отображаемым в корзине")
    @Test(description = "Проверка соответствия выбираемых продуктов, продуктам, отображаемым в корзине")
    public void checkingTheItemInTheCartPositiveTest() throws InterruptedException {
        logger.fatal("Начало checkingTheItemInTheCartPositiveTest()");
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        ProductsPage page = (ProductsPage) new LoginStep(driver)
                .login();

        String[] someProducts = new String[]{"Sauce Labs Bolt T-Shirt",
                "Sauce Labs Onesie", "Sauce Labs Backpack"};

        Products products = Products.builder()
                .productList(Arrays.asList(someProducts))
                .build();


        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderProducts(products);
        Map<String, String> addedProducts = orderStep.getAddedProduct();// Мапа заказа

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
        logger.fatal("Конец checkingTheItemInTheCartPositiveTest()");
    }

    @Severity(SeverityLevel.MINOR)
    @Description("Проверка правильного отображения информации перед оплатой")
    @Test(description = "Проверка правильного отображения информации перед оплатой")
    public void checkingTheProductOnTheCheckoutOverviewPageTest() throws InterruptedException {
        logger.fatal("Начало checkingTheProductOnTheCheckoutOverviewPageTest()");
        //      product Names
        //Sauce Labs Bolt T-Shirt
        //Sauce Labs Fleece Jacket
        //Sauce Labs Onesie
        //Sauce Labs Bike Light
        //Test.allTheThings() T-Shirt (Red)
        //Sauce Labs Backpack
        ProductsPage productsPage = (ProductsPage) new LoginStep(driver).login();

        String[] someProducts = new String[]{"Sauce Labs Bike Light",
                "Sauce Labs Fleece Jacket"};

        Products products = Products.builder()
                .productList(Arrays.asList(someProducts))
                .build();

        OrderStep orderStep = new OrderStep(driver);
        orderStep.orderProducts(products);

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

        logger.fatal("Конец checkingTheProductOnTheCheckoutOverviewPageTest()");
    }


}
