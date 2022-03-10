package TestPackage;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestResultListener.class)
//@Listeners({TestResultListener.class, ExtentITestListenerAdapter.class})
public class TestClass {
    public static boolean isTestShouldBePassed = true;


    @Test
    public void SuccessfullyLoginTest() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
    }

    @Test
    public void LoginWithInvalidPassword() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
    }

    @Test
    public void LoginWithInvalidUsername() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
    }



}