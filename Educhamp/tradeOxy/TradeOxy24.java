package tradeOxy;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TradeOxy24 
{
	WebDriver driver;
	WebDriverWait wait;
	
	@BeforeClass
	 public void launch()
	 {
	 System.setProperty("WebDriver.gecko.driver", "C:\\Users\\user\\eclipse-workspace\\Project_SeleniumNov2024\\geckodriver.exe");
      driver = new FirefoxDriver();
          wait = new WebDriverWait(driver, Duration.ofSeconds(10));
     driver.get("https://tradeoxy.com/signin/");
	 }

	@Test(priority = 1)
    public void verifyPageTitle() {
        // Fetch the page title
        String actualTitle = driver.getTitle();
        String expectedTitle = "TradeOxy - Sign In";

        // Validate the page title
        System.out.println("Actual Title: " + actualTitle);
        if (actualTitle.equals(expectedTitle)) {
            System.out.println("Page title is correct.");
        } else {
            System.out.println("Page title mismatch! Expected: '" + expectedTitle + "', but found: '" + actualTitle + "'.");
        }
    }

    @Test(priority = 2)
    public void testLoginForm() {
        try {
            // Navigate to username and password fields
            WebElement usernameField = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("username")));
            WebElement passwordField = driver.findElement(By.id("password"));
            WebElement loginButton = driver.findElement(By.id("loginButton"));

            //test credentials
            usernameField.sendKeys("neymarcr7@gmail.com");
            passwordField.sendKeys("Neymar@07");
            loginButton.click();
            System.out.println("Login form filled and submitted.");

            // validate login result and check for url page contents
            wait.until(ExpectedConditions.urlContains("dashboard"));
            String currentUrl = driver.getCurrentUrl();
            if (currentUrl.contains("dashboard")) {
                System.out.println("Login successful, navigated to dashboard.");
            } else {
                System.out.println("Login failed, please check credentials or form behavior.");
            }
        } catch (Exception e) {
            System.out.println("Login form test encountered an error: " + e.getMessage());
        }
    }

    @Test(priority = 3)
    public void verifyElementPresence() {
        try {
            // validate presence of the key
            WebElement logo = wait.until(ExpectedConditions.presenceOfElementLocated(By.id("siteLogo")));
            WebElement footer = driver.findElement(By.id("footer"));

            if (logo.isDisplayed()) {
                System.out.println("Site logo is displayed.");
            } else {
                System.out.println("Site logo is missing!");
            }

            if (footer.isDisplayed()) {
                System.out.println("Footer is displayed.");
            } else {
                System.out.println("Footer is missing!");
            }
        } catch (Exception e) {
            System.out.println("Error while verifying elements: " + e.getMessage());
        }
    }

    @Test(priority = 4)
    public void tearDown() {
        driver.quit();
        System.out.println("Browser closed.");
    }
}
	
	
	

