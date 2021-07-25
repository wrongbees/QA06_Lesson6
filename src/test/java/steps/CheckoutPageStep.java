package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCartPage;

import java.util.Properties;

public class CheckoutPageStep extends BaseStep {

    public CheckoutPageStep(WebDriver driver) throws InterruptedException {
        super(driver);
    }

    ReadProperties properties = ReadProperties.createReadProperties();
    CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver,false);

@Step("Ввод информации о пользователе")
    public void checkoutContinue(){
        checkoutCartPage.setFirstName(properties.getFirstName());
        checkoutCartPage.setLastName(properties.getLastName());
        checkoutCartPage.setPostalCode(properties.getZip());
        checkoutCartPage.clickContinue();
    }

    public void checkoutContinue(String firstName, String LastName, String Zip){
        checkoutCartPage.setFirstName(firstName);
        checkoutCartPage.setLastName(LastName);
        checkoutCartPage.setPostalCode(Zip);
        checkoutCartPage.clickContinue();
    }

}
