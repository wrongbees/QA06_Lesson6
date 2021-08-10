package steps;

import baseEntities.BasePage;
import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import models.Credentials;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }

    @Step("Логировние параметрами '{username}'  '{password}'")
    public BasePage login(String username, String password) throws InterruptedException {
        logger.error("Выполнение step "+this+" login(String username, String password)");

        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        try {
            return new ProductsPage(driver, false);
        } catch (AssertionError e) {
            return loginPage;
        }
    }

    @Step("Логировние параметрами из config.properties.")
    public BasePage login() throws InterruptedException {
        logger.info("Выполнение step "+this+" login()");
        ReadProperties properties = ReadProperties.createReadProperties();
        LoginPage loginPage = new LoginPage(driver, true);


        loginPage.setUsername(properties.getUsername());
        loginPage.setPassword(properties.getPassword());
        loginPage.clickLoginButton();

        try {
            return new ProductsPage(driver, false);
        } catch (AssertionError e) {
            return loginPage;
        }
    }

    @Step("Логировние c помощью модели Credentials")
    public BasePage login(Credentials credentials) throws InterruptedException {
        logger.info("Выполнение step "+this+" login(Credentials credentials)");


        LoginPage loginPage = new LoginPage(driver, true);
        loginPage.setUsername(credentials.getUserName());
        loginPage.setPassword(credentials.getPassword());
        loginPage.clickLoginButton();

        try {
            return new ProductsPage(driver, false);
        } catch (AssertionError e) {
            return loginPage;
        }
    }


}
