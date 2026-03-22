package com.ebaytest.question2;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class TestNGBasicsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("@BeforeClass: Setting up test environment");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testNavigation(){
        System.out.println("@Test: Navigating to eBay and verifying title");

        driver.get("https://www.ebay.com/");
        String pageTitle = driver.getTitle();
        System.out.println("Page title: " + pageTitle);
        Assert.assertTrue(pageTitle.contains("eBay"), "Page title does not contain 'eBay'!");
    }

    @BeforeTest
    public void prepareForTest() {
        System.out.println("@BeforeTest: Preparing for test");
    }

    @AfterTest
    public void cleanUpAfterTest() {
        System.out.println("@AfterTest: Cleaning up after test");
    }

    @AfterClass
    public void endTest() {
        System.out.println("@AfterClass: Cleaning up test environment");
        if(driver != null) {
            driver.quit();
        }
    }

}
