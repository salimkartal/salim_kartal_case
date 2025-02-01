package utils.helpers;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.driver.WebDrivers;
import utils.helpers.elementHelper.ElementMap;
import utils.helpers.elementHelper.ElementResponse;
import utils.helpers.elementHelper.Elements;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.concurrent.TimeUnit;


public class Helper {

    public WebDriver webDriver;
    WebDrivers webDrivers = new WebDrivers();
    private static final String SCREENSHOT_DIR = "screenshot/";
    private static int screenshotCounter = 1;

    public Helper() {
        webDriver = webDrivers.createAndGetDriver();
        webDriver.manage().window().maximize();
        webDriver.manage().deleteAllCookies();
        webDriver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
    }

    public void takeScreenshot(String testName) {
        String screenshotPath = SCREENSHOT_DIR + testName + "_screenshot_" + screenshotCounter + ".png";
        File screenshot = ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(screenshot, new File(screenshotPath));
        } catch (IOException e) {
            System.err.println("Failed to save screenshot: " + e.getMessage());
        }
        screenshotCounter++;
    }

    public void waitForGivenTime(int seconds) {
        long milliseconds = (seconds * 1000L);
        try {
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            Assert.fail(e.getMessage());
        }
    }

    public By getBy(String key) {
        ElementResponse elementInfo = ElementMap.INSTANCE.findElementInfoByKey(key);
        return Elements.getElementInfoToBy(elementInfo);
    }

    public void tearDown() {
        webDriver.quit();
        WebDrivers.clearSession();
    }
}
