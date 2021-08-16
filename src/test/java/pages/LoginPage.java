package pages;

import baseEntities.BasePage;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {


    //Селекторы
    @FindBy(id = "user-name")
    public WebElement userNameInput;

    @FindBy(id = "password")
    public WebElement passwordInput;

    @FindBy(id = "login-button")
    public WebElement loginButton;

    @FindBy(tagName = "h3")
    public WebElement errorLabel;

    @FindBy(className = "bot_column")
    public WebElement login_image;


    // Конструкторы
    public LoginPage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);
    }


    protected void openPage() {
        driver.get(properties.getURL());
    }

    public boolean isPageOpened() {
        try {
            return getLoginPageImage().isDisplayed();
        } catch (NoSuchElementException ex) {
            return false;
        }
    }

//"https://www.saucedemo.com/"
    // Getters

    public WebElement getErrorLabel() {
        return errorLabel;
    }

    public WebElement getLoginPageImage() {
        return login_image;
    }

    // Атомарные методы по работе с элементами
    public void setUsername(String text) {
        userNameInput.sendKeys(text);
        logger.debug(String.format("Инициализация поля User Name значением : %s",text));
    }

    public void setPassword(String text) {
        passwordInput.sendKeys(text);
        logger.debug(String.format("Инициализация поля Password значением : %s",text));
    }

    public void clickLoginButton() {
        loginButton.click();
        logger.debug("Нажимаем кнопку 'login-button'");
    }


}
