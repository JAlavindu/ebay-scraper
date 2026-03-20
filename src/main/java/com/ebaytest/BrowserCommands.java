package com.ebaytest;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.chrome.ChromeDriver;

public class BrowserCommands {

    private WebDriver driver;

    public void openBrowser() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.ebay.com/");
        driver.manage().window().maximize();
    }

    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
    
}
