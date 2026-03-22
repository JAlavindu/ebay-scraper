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

// package question2;

// import java.time.Duration;
// import org.openqa.selenium.By;
// import org.openqa.selenium.WebDriver;
// import org.openqa.selenium.WebElement;
// import org.openqa.selenium.chrome.ChromeDriver;
// import org.openqa.selenium.support.ui.ExpectedConditions;
// import org.openqa.selenium.support.ui.WebDriverWait;
// import org.testng.Assert;
// import org.testng.annotations.AfterClass;
// import org.testng.annotations.BeforeClass;
// import org.testng.annotations.Test;

// import io.github.bonigarcia.wdm.WebDriverManager;

// public class TestExecutionControlTest {
//     WebDriver driver;
//     WebDriverWait wait;

//     @BeforeClass
//     public void setUp() {
//         System.out.println("@BeforeClass: Setting up test environment");
//         WebDriverManager.chromedriver().setup();
//         driver = new ChromeDriver();
//         driver.manage().window().maximize();
//         // Setup explicit wait for elements that take time to load (very important for eBay)
//         wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//     }

//     @Test(priority = 1)
//     public void testCheckNavigation() {
//         System.out.println("@Test Priority 1: Navigating to eBay Sign In and verifying title");
        
//         // Navigate directly to the Sign In page
//         driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn");
        
//         String pageTitle = driver.getTitle();
//         System.out.println("Sign In Page title: " + pageTitle);
//         Assert.assertTrue(pageTitle.contains("Sign in") || pageTitle.contains("Sign In"), 
//                 "Page title does not contain 'Sign in'!");
//     }

//     @Test(priority = 2)
//     public void testVerifyFormElements() {
//         System.out.println("@Test Priority 2: Verifying username field exists");
//         driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn");

//         // On eBay, the password field doesn't appear until AFTER you enter a valid username.
//         // So we can only reliably check for the username (email) input initially.
//         WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
//         Assert.assertTrue(usernameField.isDisplayed(), "Username/Email field is not displayed!");
        
//         WebElement continueButton = driver.findElement(By.id("signin-continue-btn"));
//         Assert.assertTrue(continueButton.isDisplayed(), "Continue button is not displayed!");
//     }

//     @Test(priority = 3)
//     public void testValidateInputConstraints() {
//         System.out.println("@Test Priority 3: Validating empty login constraints");
//         driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn");

//         WebElement continueButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("signin-continue-btn")));
        
//         // Attempt to click continue without entering an email
//         continueButton.click();

//         // Verify that an error message appears telling the user to enter their email
//         WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("errormsg")));
//         Assert.assertTrue(errorMessage.isDisplayed(), "Error message did not appear for empty login!");
//         Assert.assertTrue(errorMessage.getText().length() > 0, "Error message text is empty!");
//         System.out.println("Validation Error Captured: " + errorMessage.getText());
//     }

//     @Test(priority = 4)
//     public void testSuccessfulSubmission() {
//         System.out.println("@Test Priority 4: Testing valid login flow (Partial)");
//         driver.get("https://signin.ebay.com/ws/eBayISAPI.dll?SignIn");

//         // 1. Enter email and click continue
//         WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("userid")));
//         usernameField.sendKeys("testermctestface@gmail.com"); // Replace with a real test email if you have one
        
//         WebElement continueButton = driver.findElement(By.id("signin-continue-btn"));
//         continueButton.click();

//         // 2. Wait for the password field to appear on the next screen
//         // NOTE: eBay may trigger a Captcha here instead of showing the password field. 
//         // If it does, this test will fail after 10 seconds.
//         try {
//             WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
//             Assert.assertTrue(passwordField.isDisplayed(), "Password field did not appear after entering username!");
            
//             passwordField.sendKeys("FakePassword123!");
//             WebElement signInBtn = driver.findElement(By.id("sgnBt"));
//             signInBtn.click();
            
//             System.out.println("Login submission executed successfully (Credentials may be invalid, but form worked).");
//         } catch (org.openqa.selenium.TimeoutException e) {
//             System.out.println("Warning: eBay security (Captcha or Email Code) blocked the password field from appearing.");
//             throw new org.testng.SkipException("Skipping remainder of login due to eBay bot protection.");
//         }
//     }

//     @AfterClass
//     public void tearDown() {
//         if (driver != null) {
//             driver.quit();
//         }
//     }
// }
