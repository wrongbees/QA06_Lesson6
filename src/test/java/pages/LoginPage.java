package pages;

import baseEntities.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{


    //Селекторы
    @FindBy(id = "user-name")
    public WebElement userNameInput;
   // private final static By username_Input_By = By.id("user-name");
   @FindBy(id = "password")
   public WebElement passwordInput;
   // private final static By password_Input_By = By.id("password");
   @FindBy(id = "login-button")
   public WebElement loginButton;
    //private final static By login_Button_By = By.id("login-button");
    private final static By error_Label_By = By.tagName("h3");
    private final static By login_image_By = By.cssSelector(".bot_column");




    // Конструкторы
    public LoginPage(WebDriver driver, boolean openPageByUrl) throws InterruptedException {
        super(driver, openPageByUrl);}


    protected void openPage() {
        driver.get(properties.getURL());
    }

    public boolean isPageOpened() {
        try {
            return getLoginPageImage().isDisplayed();
        } catch (NoSuchElementException ex){
            return  false;
        }
    }

//"https://www.saucedemo.com/"
    // Getters
   // public WebElement getUsernameInput(){return driver.findElement(username_Input_By); }
   // public WebElement getPasswordInput(){return driver.findElement(password_Input_By); }
    //public WebElement getLoginButton(){return driver.findElement(login_Button_By); }
    public WebElement getErrorLabel(){ return driver.findElement(error_Label_By);}
    public WebElement getLoginPageImage(){ return  driver.findElement(login_image_By);}

    // Атомарные методы по работе с элементами
    public void setUsername(String text){
       userNameInput.sendKeys(text);
    }
    public void setPassword(String text){
        passwordInput.sendKeys(text);
    }
    public void clickLoginButton(){ loginButton.click(); }


}
