package tests;

import baseEntities.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.ProductsPage;


public class SmokeTest extends BaseTest {
    @Test
    public void positiveLoginTest(){

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard_user");
        loginPage.setPassword("secret_sauce");
        loginPage.clickLoginButton();

        ProductsPage page = new ProductsPage(driver, false);
        Assert.assertEquals(page.getTitleText(), "PRODUCTS", "Страница Products не открылась");

    }
    @Test
    public void negativeLoginTests(){
        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername("standard");
        loginPage.setPassword("secret");
        loginPage.clickLoginButton();

        Assert.assertEquals(loginPage.getErrorLabel().getText(),
                "Epic sadface: Username and password do not match any user in this service");

    }
}
