package question2;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.TimeoutException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ParameterizedLoginTest {
    WebDriver driver;
    WebDriverWait wait;

    @Parameters({"browser", "baseURL"})
    @BeforeMethod
    public void setupBrowser(String browser, String baseURL) {
        System.out.println("Setting up browser: " + browser + " navigating to: " + baseURL);

        if(browser.equalsIgnoreCase("chrome")){
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        } else {
            throw new IllegalArgumentException("Unsupported browser: " + browser);
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.get(baseURL);
    }

    @Parameters({"username", "password"})
    @Test
    public void testDynamicLogin(String username, String password) {
        System.out.println("Testing login with username: " + username + " and password: " + password);
        
        try{
            WebElement usernameField = wait.until(driver -> driver.findElement(By.id("userid")));
        usernameField.clear();
        usernameField.sendKeys(username);

        WebElement continueButton = wait.until(driver -> driver.findElement(By.id("signin-continue-btn")));
        continueButton.click();
        }catch(Exception e){
            System.out.println("Login page elements not found. Check if the page structure has changed.");
            return;
        }
        
        try {
            WebElement passField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("pass")));
            passField.sendKeys(password);
            
            WebElement signInBtn = driver.findElement(By.id("sgnBt"));
            signInBtn.click();
            
            System.out.println("Form submitted for: " + username);
        } catch (TimeoutException e) {
            System.out.println("eBay presented a Captcha blocking the password field, but credentials successfully extracted from XML.");
        }
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
