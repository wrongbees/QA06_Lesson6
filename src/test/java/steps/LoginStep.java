package steps;

import baseEntities.BasePage;
import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;
import pages.ProductsPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }
    @Step("Логировние параметрами '{username}'  '{password}'")
    public BasePage login(String username, String password) throws InterruptedException {


        LoginPage loginPage =new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();

        try {
            return new ProductsPage(driver, false);
        }catch(AssertionError e){
            return loginPage;
        }
    }
    @Step("Логировние параметрами из config.properties.")
    public BasePage login() throws InterruptedException {
        ReadProperties properties = ReadProperties.createReadProperties();
        LoginPage loginPage =new LoginPage(driver, true);


        loginPage.setUsername(properties.getUsername());
        loginPage.setPassword(properties.getPassword());
        loginPage.clickLoginButton();

        try {
            return new ProductsPage(driver, false);
        }catch(AssertionError e){
            return loginPage;
        }
    }


}
