package question2;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestExecutionControlTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("@BeforeClass: Setting up test environment");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(priority = 1)
    public void  testNavigateToHomePage() {
        System.out.println("@Test: Navigating to eBay and verifying title");

        driver.get("https://www.ebay.com/");
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("eBay"), "Page title does not contain 'eBay'!");
    }

    @Test(priority = 2)
    public void testVerifyFormElements() {
        System.out.println("@Test: Verifying search box is displayed");

        driver.get("https://www.ebay.com/");
        
        List<WebElement> formTags = driver.findElements(By.id("form"));
        Assert.assertTrue(formTags.size() > 0, "No <form> tags found on the page!");

        List<WebElement> inputs = driver.findElements(By.tagName("input"));
        List<WebElement> buttons = driver.findElements(By.tagName("button"));
        List<WebElement> selects = driver.findElements(By.tagName("select"));
        
        int totalFormControls = inputs.size() + buttons.size() + selects.size();
        Assert.assertTrue(totalFormControls > 0, "No inputs, buttons, or selects found!");
        
        System.out.println("Form validation passed. Forms found: " + formTags.size() + 
                           ", Total Form Controls found: " + totalFormControls);
    }

    @Test(priority = 3)
    public void testValidateInputConstraints(){
        System.out.println("@Test: Validating input constraints on search box");

        driver.get("https://www.ebay.com/");
        
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        Assert.assertTrue(searchBox.isDisplayed(), "Search box is not displayed!");

        String maxLength = searchBox.getAttribute("maxlength");
        Assert.assertNotNull(maxLength, "Search box does not have a maxlength attribute!");
        Assert.assertTrue(Integer.parseInt(maxLength) > 0, "Search box maxlength is not greater than 0!");

        System.out.println("Input constraint validation passed. Search box maxlength: " + maxLength);
    }

    @Test(priority = 4)
    public void testSuccessfulSubmission(){
        System.out.println("@Test: Validating successful form submission");

        driver.get("https://www.ebay.com/");
        
        WebElement searchBox = driver.findElement(By.id("gh-ac"));
        searchBox.sendKeys("laptop");

        WebElement searchButton = driver.findElement(By.id("gh-btn"));
        searchButton.click();

        WebElement resultsHeading = driver.findElement(By.cssSelector("h1.srp-controls__count-heading"));
        Assert.assertTrue(resultsHeading.isDisplayed(), "Results heading is not displayed after form submission!");

        String resultsText = resultsHeading.getText();
        Assert.assertTrue(resultsText.toLowerCase().contains("result"), "Results heading does not contain 'result'!");

        System.out.println("Form submission validation passed. Results heading: " + resultsText);
    }

    /**
     * Why disable tests?
     * 1. A feature is currently broken (known bug) and you don't want the CI/CD pipeline to fail while developers are fixing it.
     * 2. The test is hitting a 3rd party API that is temporarily down or rate-limited.
     * 3. The test is still being written/developed and isn't ready to be executed yet.
     */

    // Method 1: Skipping using enabled=false
    // This test will be completely ignored by TestNG. It won't even show up in the skipped count.
    @Test(enabled = false)
    public void testBrokenFeature_WillBeSkipped() {
        System.out.println("This should NEVER print because the test is disabled.");
        driver.get("https://www.ebay.com/broken-page");
        Assert.fail("This test is currently broken, but won't fail because it is disabled.");
    }

    // Method 2: Another example of enabled=false with priority
    @Test(priority = 5, enabled = false)
    public void testIncompleteFeature_WillBeSkipped() {
        System.out.println("This test is disabled because it is not finished being written yet.");
    }

    // Method 3: Skipping conditionally using SkipException
    // This test WILL show up in testing reports as "Skipped" (yellow) instead of passed/failed.
    @Test(priority = 6)
    public void testConditionalSkip() {
        System.out.println("@Test: Checking if we should skip this test conditionally");
        
        // Example condition: Let's pretend this test should only run on weekends
        boolean isWeekend = false; // Imagine this is calculated dynamically
        
        if (!isWeekend) {
            // Throwing SkipException stops the test immediately and marks it as SKIPPED
            throw new org.testng.SkipException("Skipping test because it is not the weekend.");
        }
        
        System.out.println("This will only print if the test is NOT skipped.");
        driver.get("https://www.ebay.com/");
    }


}
