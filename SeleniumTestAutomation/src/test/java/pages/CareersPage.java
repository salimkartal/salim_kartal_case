package pages;

import actions.Action;
import org.openqa.selenium.WebDriver;

public class CareersPage extends Action {

    public CareersPage(WebDriver driver) {
        super(driver);
    }

    public void verifyText(String text) {
        isTextBlockPresent(text);
    }

    public void verifyTitle(String title) {
        verifyExpectedTitle(title);
    }

}

