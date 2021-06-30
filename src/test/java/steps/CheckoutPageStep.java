package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCartPage;

import java.util.Properties;

public class CheckoutPageStep extends BaseStep {

    public CheckoutPageStep(WebDriver driver) {
        super(driver);
    }

    ReadProperties properties = ReadProperties.createReadProperties();
    CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver,false);

    public void checkoutContinue(){
        checkoutCartPage.setFirstName(properties.getFirstName());
        checkoutCartPage.setLastName(properties.getLastName());
        checkoutCartPage.setPostalCode(properties.getZip());
        checkoutCartPage.clickContinue();
    }

    public void checkoutContinue(String firstNaame, String LastName, String Zip){
        checkoutCartPage.setFirstName(firstNaame);
        checkoutCartPage.setLastName(LastName);
        checkoutCartPage.setPostalCode(Zip);
        checkoutCartPage.clickContinue();
    }

}
