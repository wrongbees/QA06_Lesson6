package tests;

import baseEntities.BaseTest;
import net.bytebuddy.implementation.bytecode.Throw;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import steps.LoginStep;

import java.util.List;


public class SmokeTest extends BaseTest {
    @Test
    public void positiveLoginTest() {
        LoginStep loginStep = new LoginStep(driver);
        loginStep.login(properties.getUsername(),properties.getPassword());

        ProductsPage page = new ProductsPage(driver, false);
        Assert.assertEquals(page.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }

    @Test
    public void negativeLoginTests() {

        LoginStep loginStep = new LoginStep(driver);
        loginStep.login("standard","secret");



        Assert.assertEquals(new LoginPage(driver,false).getErrorLabel().getText(),
                "Epic sadface: Username and password do not match any user in this service");

    }

    @Test
    public void positiveTestForAddingAnItemToTheCart() {

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage page = new ProductsPage(driver, false);

        for (int i = 0; i <= 5; i++) {
            page.clickInventory_item_add_button_by_number(i);
            Assert.assertEquals(Integer.parseInt(page.getShoppingCartBadgeValue()), i + 1);
        }

        for (int i = 0; i < 5; i++) {
            page.clickInventory_item_remove_button_by_number(0);
            System.out.println(page.getShoppingCartBadgeValue());
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

        LoginPage loginPage = new LoginPage(driver, true);

        for (String s : userName) {
            loginPage.setUsername(s);
            loginPage.setPassword("secret_sauce");
            loginPage.clickLoginButton();

            try {
                loginPage.getLoginPageImage();

                System.out.println("User_name " + s + " не подходит.");
                throw new AssertionError();


            } catch (NoSuchElementException e) {

                ProductsPage products = new ProductsPage(driver, false);

                products.clickLogout();

            }

        }

    }

    @Test
    public void positivePaymentVerificationTest() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage page = new ProductsPage(driver, false);
        for (int i = 0; i <= 5; i++) {
            page.clickInventory_item_add_button_by_number(i);
            Assert.assertEquals(Integer.parseInt(page.getShoppingCartBadgeValue()), i + 1);
        }

        page.clickShoppingCartLink();

        CartPage cartPage = new CartPage(driver, false);
        cartPage.clickCheckoutButton();

        CheckoutPage checkoutPage = new CheckoutPage(driver, false);
        checkoutPage.setFirstName("Katy");
        checkoutPage.setLastName("Second");
        checkoutPage.setPostalCode("12345");
        checkoutPage.clickContinue();

        CheckoutOverviewPage checkoutOverview = new CheckoutOverviewPage(driver, false);
        checkoutOverview.clickFinishButton();

        CheckoutCompletePage checkoutCompletePage = new CheckoutCompletePage(driver, false);

    }

    @Test
    public void positiveSortingGoodsByName_ZATest() {
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

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
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage page = new ProductsPage(driver, false);
        page.clickSortByPrice_hilo();


        List<WebElement> listItemPrice = page.getInventoryItemPriceList();
        for (int i = 0; i < listItemPrice.size() - 1; i++) {

            double price_1 = Double.parseDouble(listItemPrice.get(i).getText().replace("$",""));
            double price_2 = Double.parseDouble(listItemPrice.get(i+1).getText().replace("$",""));
            boolean result = (price_2 - price_1) <= 0;

            Assert.assertTrue(result, "После сортировки [Price (high to low)] нужный порядок не достигнут");
        }

    }

}
