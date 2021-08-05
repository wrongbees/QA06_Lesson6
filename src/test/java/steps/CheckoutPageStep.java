package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import models.User;
import models.UserBuilder;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCartPage;

import java.util.Properties;

public class CheckoutPageStep extends BaseStep {
    ReadProperties properties = ReadProperties.createReadProperties();
    CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver,false);

    public CheckoutPageStep(WebDriver driver) throws InterruptedException {
        super(driver);
    }


@Step("Ввод информации о пользователе")
    public CheckoutOverviewPageFinishStep checkoutContinue(){
       User user = new UserBuilder()
                .setFirstName(properties.getFirstName())
                .setLastName(properties.getLastName())
                .setZip(properties.getZip())
                .build();

       checkoutCartPage.setUserParameters(user);
       checkoutCartPage.clickContinue();
       return new CheckoutOverviewPageFinishStep(driver);


    }

    public CheckoutOverviewPageFinishStep checkoutContinue(String firstName, String LastName, String Zip){
        User user = new UserBuilder()
                .setFirstName(firstName)
                .setLastName(LastName)
                .setZip(Zip)
                .build();
        checkoutCartPage.setUserParameters(user);
        checkoutCartPage.clickContinue();
        return new CheckoutOverviewPageFinishStep(driver);
    }

}
