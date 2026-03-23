package question2;

import org.testng.Assert;
import org.testng.annotations.Test;

public class DependencyTest {

    @Test
    public void startApplication() {
        System.out.println("1. startApplication: Starting the application...");
        Assert.assertTrue(true, "Application started successfully");
    }

    @Test(dependsOnMethods = {"startApplication"})
    public void loginUser() {
        System.out.println("2. loginUser: Simulating user login...");
        Assert.assertTrue(true, "User logged in successfully");
    }

    @Test(dependsOnMethods = {"loginUser"})
    public void performSearch() {
        System.out.println("3. performSearch: Attempting to search for an item...");
        Assert.fail("Intentional Failure: Search service is down!");
    }

    @Test(dependsOnMethods = {"loginUser", "performSearch"})
    public void addToCart() {
        System.out.println("4. addToCart: Adding item to cart...");
        Assert.assertTrue(true, "Item added to cart");
    }

    @Test(dependsOnMethods = {"addToCart"}, alwaysRun = true)
    public void logoutAndCleanup() {
        System.out.println("5. logoutAndCleanup: Logging out... This always runs!");
        Assert.assertTrue(true, "Cleanup successful");
    }
}

/*

What Happens When a Dependency Fails?
When a method that other tests depend on fails (like performSearch in the example above):

Skipped Tests, Not Failed: TestNG will completely bypass executing any downstream dependent methods (like addToCart). Instead of marking them as FAILED (red cross), TestNG marks them as SKIPPED (usually a yellow circle/triangle in reports).
Chain Reaction: If "Test C" depends on "Test B", and "Test B" depends on "Test A", a failure in "Test A" means both "Test B" and "Test C" will be skipped.
The Exception (alwaysRun = true): If a downstream test has alwaysRun = true configured (often used for cleanup, logging out, or closing database connections), TestNG will catch the dependency failure but still forcibly execute the method as a soft safeguard. This is why logoutAndCleanup() will still print its message despite the failures above it.

 */