package core;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.config.DriverManagerType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class BrowserService {
    private ReadProperties properties = new ReadProperties();
    private DriverManagerType driverManagerType;
    private WebDriver driver = null;

  public BrowserService() {
      switch (properties.getBrowser().toLowerCase()) {
          case  "chrome" :
              WebDriverManager.getInstance(DriverManagerType.CHROME).setup();
              driver = new ChromeDriver();
              break;
          case  "firefox" :
              WebDriverManager.getInstance(DriverManagerType.FIREFOX).setup();
              driver = new FirefoxDriver();
              break;
          case  "ie" :
              WebDriverManager.getInstance(DriverManagerType.IEXPLORER).setup();
              driver = new InternetExplorerDriver();
              break;
          default:
              throw new AssertionError("Данный браузер не поддерживается");
      }
  }

    public WebDriver getDriver() {
        return driver;
    }
}
