package question2;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class AssertionsTest {

    // ==========================================
    //            HARD ASSERTIONS
    // ==========================================

    @Test
    public void testStringEquality() {
        System.out.println("Running testStringEquality...");
        String expectedTitle = "eBay";
        String actualTitle = "eBay";
        Assert.assertEquals(actualTitle, expectedTitle, "Page title does not match!");
        System.out.println("testStringEquality completed successfully.");
    }

    @Test
    public void testNumberEquality() {
        System.out.println("Running testNumberEquality...");
        int expectedItemCount = 5;
        int actualItemCount = 5;
        Assert.assertEquals(actualItemCount, expectedItemCount, "Item counts do not match!");
        System.out.println("testNumberEquality completed successfully.");
    }

    @Test
    public void testBooleanConditions() {
        System.out.println("Running testBooleanConditions...");
        boolean isLogoDisplayed = true;
        boolean isErrorVisible = false;

        Assert.assertTrue(isLogoDisplayed, "Logo should be displayed, but it wasn't!");
        Assert.assertFalse(isErrorVisible, "Error message should not be visible!");
        System.out.println("testBooleanConditions completed successfully.");
    }

    @Test
    public void testHardAssertionFailure() {
        System.out.println("Running testHardAssertionFailure...");
        
        Assert.assertEquals("Actual", "Expected", "Intentional Failure: Strings do not match!");
        
        System.out.println("This line will NOT print because the test aborted on the failure above.");
        Assert.assertTrue(true); 
    }

    // ==========================================
    //            SOFT ASSERTIONS
    // ==========================================

    @Test
    public void testSoftAssertionsMultipleChecks() {
        System.out.println("Running testSoftAssertionsMultipleChecks...");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals("Apple", "Orange", "Fruit mismatch! (This fails but continues)");
        System.out.println("Still running after the first soft assertion failure!");

        softAssert.assertTrue(true, "This condition is true.");
        System.out.println("Still running after the second passing check.");

        softAssert.assertFalse(true, "Expected false but got true! (This fails but continues)");
        System.out.println("Still running after the third soft assertion failure!");

        System.out.println("Calling assertAll() to report all accumulated failures.");
        softAssert.assertAll(); 
        
        System.out.println("This will NOT print because assertAll() failed the test.");
    }

    @Test
    public void testSoftAssertionPassing() {
        System.out.println("Running testSoftAssertionPassing...");
        SoftAssert softAssert = new SoftAssert();

        softAssert.assertEquals(100, 100, "Values match");
        softAssert.assertTrue(true, "Condition is true");

        System.out.println("Calling assertAll() on passing checks.");
        softAssert.assertAll();
        System.out.println("testSoftAssertionPassing completed successfully (assertAll passed).");
    }
}