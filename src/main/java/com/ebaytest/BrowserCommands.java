package com.ebaytest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class BrowserCommands {

    private WebDriver driver;
    FormInteraction formInteraction = new FormInteraction();
    TestBrowserCommands testBrowserCommands = new TestBrowserCommands();

    public void openBrowser(WebDriver driver) {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://www.ebay.com/");
        String currentURL = driver.getCurrentUrl();
        testBrowserCommands.testBrowser(currentURL, driver);
        formInteraction.advancedNavigation(driver);
        
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
