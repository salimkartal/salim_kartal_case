package actions;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.junit.rules.TestWatcher;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.helpers.Helper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class Action extends Helper {

    public WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(30));

    public Action(WebDriver driver) {
        super();
        this.webDriver = driver;
    }

    public void clickElement(WebElement element) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException e) {
            Assert.fail(e.getMessage());
        }
    }

    public WebElement findElement(String key) {
        long startTime = System.currentTimeMillis();
        while (System.currentTimeMillis() - startTime < 9000) {
            try {
                return wait.until(ExpectedConditions.presenceOfElementLocated(getBy(key)));
            } catch (StaleElementReferenceException e) {
                System.out.println(e);
            }
        }
        Assert.fail("Element '" + key + "' not found.");
        return null;
    }

    public List<WebElement> findElements(String key) {
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(getBy(key)));
    }

    public void scrollDown(int pixels) {
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0," + pixels + ")");
        waitForGivenTime(5);
    }

    public void hoverOverElement(WebElement element) {
        Actions actions = new Actions(webDriver);
        actions.moveToElement(element).perform();
    }

    public void verifyJobs(String key, String key1, String key2, String positionText, String departmentText, String locationText) {

        List<String> errors = new ArrayList<>();

        waitForGivenTime(5);
        List<WebElement> titleElements = findElements(key);
        List<WebElement> departmentElements = findElements(key1);
        List<WebElement> locationElements = findElements(key2);

        for (WebElement titleElement : titleElements) {
            try {
                if (!titleElement.getText().contains(positionText)) {
                    throw new AssertionError("Position does not contain: " + positionText);
                }
            } catch (AssertionError e) {
                errors.add("Position Error: " + e.getMessage());
            }
        }

        for (WebElement departmentElement : departmentElements) {
            try {
                if (!departmentElement.getText().contains(departmentText)) {
                    throw new AssertionError("Department does not contain: " + departmentText);
                }
            } catch (AssertionError e) {
                errors.add("Department Error: " + e.getMessage());
            }
        }

        for (WebElement locationElement : locationElements) {
            try {
                if (!locationElement.getText().contains(locationText)) {
                    throw new AssertionError("Location does not contain: " + locationText);
                }
            } catch (AssertionError e) {
                errors.add("Location Error: " + e.getMessage());
            }
        }

        if (!errors.isEmpty()) {
            for (String error : errors) {
                System.out.println(error);
            }
        } else {
            System.out.println("All job listings meet the criteria.");
        }
    }


    public void verifyExpectedUrl(String expectedUrl) {
        String actualUrl = webDriver.getCurrentUrl();
        Assert.assertEquals("URL does not match!", expectedUrl, actualUrl);
    }

    public void verifyExpectedTitle(String title) {
        Assert.assertTrue(webDriver.getTitle().contains(title));
    }


    public boolean isListPresent(String key) {
        List<WebElement> elements = webDriver.findElements(By.cssSelector(key));
        return !elements.isEmpty();
    }

    public boolean isTextBlockPresent(String text) {
        try {
            WebElement element = webDriver.findElement(By.xpath("//*[text()='" + text + "']"));
            return element.isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }

    public void verifyUrl(String url) {
        Assert.assertTrue(webDriver.getCurrentUrl().contains(url));
    }

    public void switchCaseCheck(String expectedTitle, String expectedUrl) {
        List<String> browserTabs = new ArrayList<>(webDriver.getWindowHandles());
        webDriver.switchTo().window(browserTabs.get(1));
        verifyExpectedTitle(expectedTitle);
        verifyUrl(expectedUrl);
        webDriver.switchTo().window(browserTabs.get(0));
    }


}
