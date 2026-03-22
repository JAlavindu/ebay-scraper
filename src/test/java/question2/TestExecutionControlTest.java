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

    

}
