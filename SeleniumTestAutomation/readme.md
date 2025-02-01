# Insider Selenium Test Automation

This project contains automated test scenarios for the Insider website. The project is developed using Selenium WebDriver and JUnit5.

## Project Structure
- **src/test/java**: Test code.
- **pom.xml**: Maven configuration file.

## Setup
### Requirements
- Java 8 or higher
- Maven 3.6.0 or higher

## Project Content

- verifyHomePageUrlAndRejectCookies: Verifies the home page URL, title, and rejects cookies.

- navigateToCareersPageAndVerifyContent: Navigates to the Careers page and verifies the content (Locations, Teams, Life at Insider).

- filterQualityAssuranceJobs: Filters the Quality Assurance jobs based on location (Istanbul) and verifies the job list.

- verifyFilteredJobs: Verifies the filtered jobs' details such as title, department, and location.

- viewRoleAndApplicationTab: Views the details of a specific job role and verifies the application tab in a new browser tab.
