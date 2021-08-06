package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CartPage;


public class CartReadyForCheckingStep extends BaseStep {

    public CartReadyForCheckingStep(WebDriver driver) {
        super(driver);


    }


    @Step("Нажимаем кнопку CheckoutButton")
    public CheckoutPageStep clickCheckoutButton() throws InterruptedException {
        new CartPage(driver, false).clickCheckoutButton();
        return new CheckoutPageStep(driver);
    }
}
