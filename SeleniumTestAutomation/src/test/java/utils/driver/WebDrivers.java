package utils.driver;

import org.openqa.selenium.WebDriver;

public class WebDrivers {
    public static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    public static DriverFactory driverFactory = DriverFactory.getInstance();

    public WebDriver createAndGetDriver() {
        if (driver.get() == null) {
            driver.set(driverFactory.getDriver());
        }
        return driver.get();
    }

    public static void clearSession() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
