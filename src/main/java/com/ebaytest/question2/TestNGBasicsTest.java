package com.ebaytest.question2;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

public class TestNGBasicsTest {
    WebDriver driver;

    @BeforeClass
    public void setUp() {
        System.out.println("@BeforeClass: Setting up test environment");
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
    }

}
