package com.ebaytest;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class TestBrowserCommands {
    public void testBrowser(String currentURL, WebDriver driver) {
        System.out.println("=== Browser Commands Test ===");

        Assert.assertTrue(
                currentURL.contains("ebay.com"),
                "Navigation failed! URL does not contain 'ebay.com'"
            );
        
        Assert.assertFalse(
                driver.getTitle().isEmpty(),
                "Page title is empty — page may not have loaded!"
            );

        System.out.println("Navigation validated successfully!");
        System.out.println("Page Title: " + driver.getTitle() + "character length: " + driver.getTitle().length());
        System.out.println("Current URL: " + currentURL);
        System.out.println("Page source length: " + driver.getPageSource().length());
    }
}
