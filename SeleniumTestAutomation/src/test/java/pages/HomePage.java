package pages;

import actions.Action;
import org.openqa.selenium.WebDriver;

public class HomePage extends Action {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void click(String key) {
        clickElement(findElement(key));
    }

    public void verifyTitle(String title) {
        verifyExpectedTitle(title);
    }

    public void verifyUrl(String url) {
        verifyExpectedUrl(url);
    }

}
