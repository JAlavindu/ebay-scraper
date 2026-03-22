package com.ebaytest.question1;

import java.time.Duration;

import org.openqa.selenium.WebDriver;

public class BrowserCommands {

    private WebDriver driver;
    FormInteraction formInteraction = new FormInteraction();
    ElementInteraction elementInteraction = new ElementInteraction();

    public void openBrowser(WebDriver driver) {

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        driver.get("https://www.ebay.com/");
        String currentURL = driver.getCurrentUrl();
        elementInteraction.testBrowser(currentURL, driver);
        elementInteraction.advancedNavigation(driver);
        
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
