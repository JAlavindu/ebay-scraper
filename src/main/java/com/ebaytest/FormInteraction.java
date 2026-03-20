package com.ebaytest;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class FormInteraction {

    TestBrowserCommands testBrowserCommands = new TestBrowserCommands();

    public void advancedNavigation(WebDriver driver){
        driver.navigate().to("https://www.ebay.com/sch/ebayadvsearch");
        
        String currentURL = driver.getCurrentUrl();
        testBrowserCommands.testBrowser(currentURL, driver);

        WebElement keywordInput = driver.findElement(By.xpath("//input[@id='_nkw']"));
        keywordInput.sendKeys("laptop");
        System.out.println("Keyword entered: " + keywordInput.getAttribute("value"));

        WebElement categorydropdown = driver.findElement(By.xpath("//div[@class='field adv-field___sacat']//select[contains(@id,'s0-1-20-4')]"));
        Select categorySelect = new Select(categorydropdown);
        categorySelect.selectByIndex(1);
        //categorySelect.selectByVisibleText("Exact words, exact order");

        WebElement searchCheckbox1 = driver.findElement(By.name("LH_TitleDesc"));
        searchCheckbox1.click();
        Assert.assertTrue(searchCheckbox1.isSelected(), "Search checkbox 1 was not selected!");

        WebElement searchCheckbox2 = driver.findElement(By.name("LH_Complete"));
        searchCheckbox2.click();
        Assert.assertTrue(searchCheckbox2.isSelected(), "Search checkbox 2 was not selected!");

        WebElement minPricElement = driver.findElement(By.name("_udlo"));
        minPricElement.sendKeys("100");
        String actualMinPrice = minPricElement.getAttribute("value");
        Assert.assertEquals(actualMinPrice, "100", "Minimum price was not set correctly!");
        System.out.println("Minimum price validated successfully: " + actualMinPrice);

        WebElement maxPricElement = driver.findElement(By.name("_udhi"));
        maxPricElement.sendKeys("500");
        String actualMaxPrice = maxPricElement.getAttribute("value");
        Assert.assertEquals(actualMaxPrice, "500", "Maximum price was not set correctly!");
        System.out.println("Maximum price validated successfully: " + actualMaxPrice);

        WebElement searchButton = driver.findElement(By.xpath("//div[@class='adv-form__actions']//button[@type='submit'][normalize-space()='Search']"));
        searchButton.click();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.srp-controls__count-heading")));
        String resultText = resultCountElement.getText();
        Assert.assertTrue(resultText.contains("results for"), "Search results did not load as expected!");
        System.out.println("Search results validated successfully: " + resultText);

    }
}
