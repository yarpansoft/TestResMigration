package ATestPlan;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

//Uncomment those Adapter/Adapters that you want to use
//@Listeners(JiraAdapter.JiraListener.class)
@Listeners(TestRailAdapter.TestRailListener.class)
//@Listeners({JiraAdapter.JiraListener, TestRailAdapter.TestRailListener.class})

public class TestSuite {
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