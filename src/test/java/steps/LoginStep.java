package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import org.openqa.selenium.WebDriver;
import pages.LoginPage;

public class LoginStep extends BaseStep {

    public LoginStep(WebDriver driver) {
        super(driver);
    }

    public void login(String username, String password){


        LoginPage loginPage =new LoginPage(driver, true);
        loginPage.setUsername(username);
        loginPage.setPassword(password);
        loginPage.clickLoginButton();
    }

    public void login(){
        ReadProperties properties = ReadProperties.createReadProperties();
        LoginPage loginPage =new LoginPage(driver, true);


        loginPage.setUsername(properties.getUsername());
        loginPage.setPassword(properties.getPassword());
        loginPage.clickLoginButton();
    }


}
