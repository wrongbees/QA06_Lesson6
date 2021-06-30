package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage extends BasePage{
    private static LoginPage loginPage;

    //Селекторы
    private final static By username_Input_By = By.id("user-name");
    private final static By password_Input_By = By.id("password");
    private final static By login_Button_By = By.id("login-button");
    private final static By error_Label_By = By.tagName("h3");
    private final static By login_image_By = By.cssSelector(".bot_column");




    // Конструкторы
    private LoginPage(WebDriver driver, boolean openPageByUrl) {
        super(driver, openPageByUrl);}

    public static LoginPage createLoginPage(WebDriver driver, boolean openPageByUrl){
        if (loginPage == null){ return loginPage =new LoginPage(driver, openPageByUrl);}
        return loginPage;
    }


    protected void openPage() {
        driver.get(properties.getURL());
    }

    public boolean isPageOpened() {
        try {
            return getLoginButton().isDisplayed();
        } catch (NoSuchElementException ex){
            return  false;
        }
    }

//"https://www.saucedemo.com/"
    // Getters
    public WebElement getUsernameInput(){return driver.findElement(username_Input_By); }
    public WebElement getPasswordInput(){return driver.findElement(password_Input_By); }
    public WebElement getLoginButton(){return driver.findElement(login_Button_By); }
    public WebElement getErrorLabel(){ return driver.findElement(error_Label_By);}
    public WebElement getLoginPageImage(){ return  driver.findElement(login_Button_By);}

    // Атомарные методы по работе с элементами
    public void setUsername(String text){
        getUsernameInput().sendKeys(text);
    }
    public void setPassword(String text){
        getPasswordInput().sendKeys(text);
    }
    public void clickLoginButton(){ getLoginButton().click(); }


}
