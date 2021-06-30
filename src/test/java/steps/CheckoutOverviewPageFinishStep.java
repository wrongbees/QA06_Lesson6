package steps;

import baseEntities.BaseStep;
import org.openqa.selenium.WebDriver;
import pages.CheckoutOverviewPage;

public class CheckoutOverviewPageFinishStep extends BaseStep {

    public CheckoutOverviewPageFinishStep(WebDriver driver) {
        super(driver);

        new CheckoutOverviewPage(driver,false).clickFinishButton();
    }
}
