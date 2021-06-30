package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.CartPage;


public class CartReadyForCheckingStep extends BaseStep {

    public CartReadyForCheckingStep(WebDriver driver) {
        super(driver);

        CartPage.createCartPage(driver,false).clickCheckoutButton();
    }


}
