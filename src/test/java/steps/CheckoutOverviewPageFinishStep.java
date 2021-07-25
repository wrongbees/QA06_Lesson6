package steps;

import baseEntities.BaseStep;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import pages.CheckoutOverviewPage;

public class CheckoutOverviewPageFinishStep extends BaseStep {

    public CheckoutOverviewPageFinishStep(WebDriver driver) {
        super(driver);

    }

    @Step ("Нажатие на clickFinishButton.")
    public void clickFinishButton() throws InterruptedException {
        new CheckoutOverviewPage(driver,false).clickFinishButton();
    }
}
