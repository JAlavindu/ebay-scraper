package question1;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class ElementInteraction {
    public void advancedNavigation(WebDriver driver){
        driver.navigate().to("https://www.ebay.com/sch/ebayadvsearch");
        
        String currentURL = driver.getCurrentUrl();
        testBrowser(currentURL, driver);

        WebElement keywordInput = driver.findElement(By.xpath("//input[@id='_nkw']"));
        keywordInput.sendKeys("laptop");
        System.out.println("Keyword entered: " + keywordInput.getAttribute("value"));

        WebElement keywordCategorydropdown = driver.findElement(By.xpath("//div[@class='field adv-field___sacat']//select[contains(@id,'s0-1-20-4')]"));
        Select keywordCategorySelect = new Select(keywordCategorydropdown);
        keywordCategorySelect.selectByIndex(1);
        WebElement selectedOption = keywordCategorySelect.getFirstSelectedOption();
        System.out.println("selected option: " + selectedOption.getText());
        //keywordCategorySelect.selectByVisibleText("Exact words, exact order");
        //keywordCategorySelect.selectByValue("2");

        List<WebElement> optionsList = keywordCategorySelect.getOptions();

        for(WebElement option : optionsList){
            String optionText = option.getText();
            String optionValue = option.getAttribute("value");
    
            System.out.println("Text: '" + optionText + "' | Value: '" + optionValue + "'");
        }

        //checkboxes
        /*
        By.id is generally the fastest and most efficient locator strategy in Selenium.
        Because HTML standards dictate that id attributes should be unique across the entire page,
        the browser can find the element instantly without having to scan the whole DOM tree (unlike XPath).
        */
        WebElement searchCheckbox1 = driver.findElement(By.id("s0-1-20-5[1]-[0]-LH_TitleDesc"));
        searchCheckbox1.click();
        Assert.assertTrue(searchCheckbox1.isSelected(), "Search checkbox 1 was not selected!");

        /* 
        By.name is highly reliable and stable, especially for form elements.
        Because backend servers rely on the 'name' attribute to process submitted form data,
        it rarely changes over time, unlike dynamic IDs or easily broken XPaths.
        */
        WebElement searchCheckbox2 = driver.findElement(By.name("LH_Complete"));
        searchCheckbox2.click();
        Assert.assertTrue(searchCheckbox2.isSelected(), "Search checkbox 2 was not selected!");

        //min and max price
        /*
        Using By.cssSelector for the price fields is a good choice because 
        it allows us to target elements based on their attributes (like name) 
        without relying on potentially unstable IDs or complex XPaths.
         */
        WebElement minPricElement = driver.findElement(By.cssSelector("input[name='_udlo']"));
        minPricElement.sendKeys("100");
        String actualMinPrice = minPricElement.getAttribute("value");
        Assert.assertEquals(actualMinPrice, "100", "Minimum price was not set correctly!");
        System.out.println("Minimum price validated successfully: " + actualMinPrice);

        WebElement maxPricElement = driver.findElement(By.name("_udhi"));
        maxPricElement.sendKeys("500");
        String actualMaxPrice = maxPricElement.getAttribute("value");
        Assert.assertEquals(actualMaxPrice, "500", "Maximum price was not set correctly!");
        System.out.println("Maximum price validated successfully: " + actualMaxPrice);

        //radio buttons
        WebElement buyingFormatRadio = driver.findElement(By.id("s0-1-20-6[3]-[0]-LH_BO"));
        buyingFormatRadio.click();

        WebElement conditionRadio = driver.findElement(By.id("s0-1-20-6[4]-[0]-LH_ItemCondition"));
        conditionRadio.click();

        WebElement searchButton = driver.findElement(By.xpath("//div[@class='adv-form__actions']//button[@type='submit'][normalize-space()='Search']"));
        System.out.println("button name:" + searchButton.getText());
        searchButton.sendKeys(Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement resultCountElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("h1.srp-controls__count-heading")));
        String resultText = resultCountElement.getText();
        Assert.assertTrue(resultText.toLowerCase().contains("result"), "Search results did not load as expected!");
        System.out.println("Search results validated successfully: " + resultText);

    }

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
