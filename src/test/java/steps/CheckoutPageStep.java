package steps;

import baseEntities.BaseStep;
import core.ReadProperties;
import io.qameta.allure.Step;
import models.User;
import org.openqa.selenium.WebDriver;
import pages.CheckoutCartPage;
import pages.CheckoutOverviewPage;

public class CheckoutPageStep extends BaseStep {
    ReadProperties properties = ReadProperties.createReadProperties();
    CheckoutCartPage checkoutCartPage = new CheckoutCartPage(driver, false);

    public CheckoutPageStep(WebDriver driver) throws InterruptedException {
        super(driver);
    }


    @Step("Ввод информации о пользователе")
    public CheckoutOverviewPage checkoutContinue() throws InterruptedException {
        logger.error("Выполнение step "+this+" checkoutContinue()");
        User user = User.builder()
                .firstname(properties.getFirstName())
                .lastname(properties.getLastName())
                .zip(properties.getZip())
                .build();

        checkoutCartPage.setUserParameters(user);
        checkoutCartPage.clickContinue();

        return new CheckoutOverviewPage(driver, false);


    }

    public CheckoutOverviewPage checkoutContinue(User user) throws InterruptedException {
        logger.error("Выполнение step "+this+" checkoutContinue(User user");
        checkoutCartPage.setUserParameters(user);
        checkoutCartPage.clickContinue();
        return new CheckoutOverviewPage(driver, false);
    }

}
