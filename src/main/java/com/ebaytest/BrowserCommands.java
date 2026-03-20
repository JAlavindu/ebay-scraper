package com.ebaytest;

import java.text.Normalizer.Form;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class BrowserCommands {

    private WebDriver driver;
    FormInteraction formInteraction = new FormInteraction();
    TestBrowserCommands testBrowserCommands = new TestBrowserCommands();

    public void openBrowser(WebDriver driver) {
        // WebDriverManager.chromedriver().setup();
        // driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://www.ebay.com/");
        String currentURL = driver.getCurrentUrl();
        // testBrowserCommands(currentURL);
        testBrowserCommands.testBrowser(currentURL, driver);
        formInteraction.advancedNavigation(driver);
        
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }

    // public void testBrowserCommands(String currentURL) {
    //     System.out.println("=== Browser Commands Test ===");

    //     Assert.assertTrue(
    //             currentURL.contains("ebay.com"),
    //             "Navigation failed! URL does not contain 'ebay.com'"
    //         );
        
    //     Assert.assertFalse(
    //             driver.getTitle().isEmpty(),
    //             "Page title is empty — page may not have loaded!"
    //         );

    //     System.out.println("Navigation validated successfully!");
    //     System.out.println("Page Title: " + driver.getTitle() + "character length: " + driver.getTitle().length());
    //     System.out.println("Current URL: " + currentURL);
    //     System.out.println("Page source length: " + driver.getPageSource().length());
    // }
    
}
