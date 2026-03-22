package question2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DataProviderTest {

    WebDriver driver;
    WebDriverWait wait;
    
    @BeforeClass
    public void setup(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @DataProvider(name = "searchData")
    public Object[][] searchTerms(){
        return new Object[][]{
            // Make sure these match the dropdown EXACTLY
            {"laptop", "Electronics", true},
            {"shoes", "Fashion", true},
            {"book", "Books & Magazines", true},
            {"invalidSearchTerm", "InvalidCategory", false},
            {"", "All Categories", false}
        };
    }

    @Test(dataProvider = "searchData")
    public void testSearchFunctionality(String searchTerm, String category, boolean isValid){
        driver.get("https://www.ebay.com/");

        try {
            WebElement categoryDropdown = driver.findElement(By.id("gh-cat"));
            Select categorySelect = new Select(categoryDropdown);
            categorySelect.selectByVisibleText(category);
        } catch (NoSuchElementException e) {
            System.out.println("Could not find category: " + category + ". Proceeding with search anyway for negative testing.");
        }

        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("gh-ac")));
        searchBox.clear();
        searchBox.sendKeys(searchTerm);

        WebElement searchButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("gh-search-btn")));
        searchButton.click();

        if (isValid) {
            WebElement resultCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.srp-controls__count-heading")));
            
            String pageTitle = driver.getTitle();
            Assert.assertTrue(pageTitle.toLowerCase().contains(searchTerm.toLowerCase()), "Positive Test failed: Expected title to contain our valid search term!");
            Assert.assertTrue(resultCount.getText().contains("result"), "Expected search results were not found!");
            
            System.out.println("Passed valid search for: " + searchTerm);
        } else {
            if (searchTerm.equals("")) {
                Assert.assertFalse(driver.getTitle().contains("results"), "Negative test failed: Empty search shouldn't show results!");
            } else {
                WebElement resultCount = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.srp-controls__count-heading")));
                Assert.assertTrue(resultCount.getText().contains("0 results"), 
                    "Negative Test failed: Expected 0 results for invalid term, but found results!");
            }
            System.out.println("Passed invalid search for: " + searchTerm);
        }
    }
    
    @AfterClass
    public void teardown() {
        if(driver != null) {
            driver.quit();
        }
    }
}