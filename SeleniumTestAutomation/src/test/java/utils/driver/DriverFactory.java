package utils.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import utils.helpers.PropertyManager;


public class DriverFactory {
    public static DriverFactory instance = null;
    public WebDriver driver;

    public DriverFactory() {
        initializeDriver();
    }

    public static DriverFactory getInstance() {
        if (instance == null) {
            instance = new DriverFactory();
        }
        return instance;
    }

    public void initializeDriver() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
        PropertyManager propertyManager = PropertyManager.propertyManager;
        String browserName = propertyManager.getProperty("env.properties", "browser");

        switch (browserName.toLowerCase()) {
            case "chrome":
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.addArguments("disable-notifications");
                System.setProperty("webdriver.chrome.driver",
                        System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver(chromeOptions);
                break;

            case "firefox":
                FirefoxOptions firefoxOptions = new FirefoxOptions();
                System.setProperty("webdriver.gecko.driver",
                        System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver(firefoxOptions);
                break;

            default:
                throw new RuntimeException("Lütfen 'chrome' veya 'firefox' şeklinde bir tarayıcı seçin.");
        }
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void navigateToDomain(int index) {
        PropertyManager propertyManager = PropertyManager.propertyManager;
        String[] domains = propertyManager.getDomains("env.properties");
        if (index >= 0 && index < domains.length) {
            driver.get(domains[index]);
        } else {
            throw new RuntimeException("Geçersiz domain indexi: " + index);
        }
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        instance = null;
    }
}
