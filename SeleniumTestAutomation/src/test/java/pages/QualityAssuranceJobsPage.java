package pages;

import actions.Action;
import org.openqa.selenium.WebDriver;

public class QualityAssuranceJobsPage extends Action {

    public QualityAssuranceJobsPage(WebDriver driver) {
        super(driver);
    }

    public void click(String key) {
        clickElement(findElement(key));
    }

    public void scrool(int pixel) {
        scrollDown(pixel);
    }

    public void verifyJobList(String key) {
        isListPresent(key);
    }

    public void verifyJob(String key, String key1, String key2, String positionText, String departmentText, String locationText) {
        verifyJobs(key, key1, key2, positionText, departmentText, locationText);
    }

    public void hoverOnElement(String key) {
        hoverOverElement(findElement(key));
    }

    public void verifyNewTab(String expectedTitle, String expectedUrl) {
        switchCaseCheck(expectedTitle, expectedUrl);
    }
}
