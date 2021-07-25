package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;
import pages.LoginPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }
    @Step("Логировние параметрами '{username}'  '{password}'")
    public void login(String username, String password) throws InterruptedException {


        LoginPage loginPage =new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }
    @Step("Логировние параметрами из config.properties.")
    public void login() throws InterruptedException {
        ReadProperties properties = ReadProperties.createReadProperties();
        LoginPage loginPage =new LoginPage(driver, true);


        loginPage.setUsername(properties.getUsername());
        loginPage.setPassword(properties.getPassword());
        loginPage.clickLoginButton();
    }


}
