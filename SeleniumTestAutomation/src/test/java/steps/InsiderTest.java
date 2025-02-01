package steps;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.openqa.selenium.WebDriver;
import pages.CareersPage;
import pages.HomePage;
import pages.QualityAssuranceJobsPage;
import utils.driver.DriverFactory;


@TestMethodOrder(OrderAnnotation.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class InsiderTest {

    private static HomePage homePage;
    private static CareersPage careersPage;
    private static QualityAssuranceJobsPage qualityAssuranceJobsPage;
    private static DriverFactory driverFactory;
    private static WebDriver driver;

    @BeforeAll
    public static void setUp() {
        driverFactory = DriverFactory.getInstance();
        driver = driverFactory.getDriver();
        driverFactory.navigateToDomain(0);
        homePage = new HomePage(driver);
        careersPage = new CareersPage(driver);
        qualityAssuranceJobsPage = new QualityAssuranceJobsPage(driver);
    }

    @AfterAll
    public static void tearDown() {
        qualityAssuranceJobsPage.tearDown();
    }

    @AfterEach
    public void takeScreenshotOnFailure(TestInfo testInfo) {
        if (testInfo.getTestMethod().isPresent() && testInfo.getTags().contains("failed")) {
            homePage.takeScreenshot(testInfo.getDisplayName());
        }
    }

    @Test
    @Order(1)
    public void verifyHomePageUrlAndRejectCookies() {
        homePage.verifyUrl("https://useinsider.com/");
        homePage.verifyTitle("Insider");
        homePage.click("CookieReject");
    }

    @Test
    @Order(2)
    public void navigateToCareersPageAndVerifyContent() {
        homePage.click("CompanyMenuLocator");
        homePage.click("CareersLocator");
        careersPage.verifyTitle("Careers");
        careersPage.verifyText("Locations");
        careersPage.verifyText("Teams");
        careersPage.verifyText("Life at Insider");
    }

    @Test
    @Order(3)
    public void filterQualityAssuranceJobs() {
        driverFactory.navigateToDomain(1);
        qualityAssuranceJobsPage.click("QAJobs");
        qualityAssuranceJobsPage.scrool(400);
        qualityAssuranceJobsPage.click("FilterLocation");
        qualityAssuranceJobsPage.click("Istanbul");
        qualityAssuranceJobsPage.verifyJobList("PositionList");
    }

    @Test
    @Order(4)
    public void verifyFilteredJobs() {
        qualityAssuranceJobsPage.verifyJob("PositionListTitles", "PositionListDepartments",
                "PositionListLocations", "Software", "Quality Assurance", "Istanbul, Turkey");

    }

    @Test
    @Order(5)
    public void viewRoleAndApplicationTab() {
        qualityAssuranceJobsPage.scrool(300);
        qualityAssuranceJobsPage.hoverOnElement("Hover");
        qualityAssuranceJobsPage.click("ViewRole");
        qualityAssuranceJobsPage.verifyNewTab("Quality", "lever");
    }
}
