package com.ebaytest.question1;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class FormInteraction {

    

    public static void main(String[] args) {
       WebDriver driver;

       BrowserCommands browserCommands = new BrowserCommands();

       WebDriverManager.chromedriver().setup();
       driver = new ChromeDriver();

       try{
        browserCommands.openBrowser(driver);
       }
       catch(Exception e){
        System.out.println("An error occurred: " + e.getMessage());

       }finally {
           browserCommands.closeBrowser();
           System.out.println("Browser closed successfully.");
       }
       

    }
}
