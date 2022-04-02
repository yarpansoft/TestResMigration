package ATestPlan;

import PractiTestAdapter.PractiTestSettings;
import org.testng.Assert;
import org.testng.annotations.*;

/** Uncomment those Adapter/Adapters that you want to use **/
//@Listeners(JiraAdapter.JiraListener.class)
//@Listeners(TestRailAdapter.TestRailListener.class)
@Listeners(PractiTestAdapter.PractiTestListener.class)
//@Listeners({JiraAdapter.JiraListener.class, TestRailAdapter.TestRailListener.class})

public class TestSuite {
    public static boolean isTestShouldBePassed = true;

    /** BeforeSuite is used for PractiTestAdapter Adapter only **/
    @BeforeSuite
    public void selectTestSet(){
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
        PractiTestAdapter.PractiTestActions practiTestActions = new PractiTestAdapter.PractiTestActions();
        practiTestActions.selectTestSetId(this.getClass().getCanonicalName());

        //System.out.println("Before Suite - " + this.getClass().getCanonicalName()); //packageName.className
        //System.out.println("Before Suite = " + this.getClass().getSimpleName()); //className
    }


    @Test
    public void SuccessfullyLoginTest() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }

    @Test
    public void LoginWithInvalidPassword() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }

    @Test
    public void LoginWithInvalidUsername() {
        boolean expected = true;
        boolean actual = isTestShouldBePassed;
        Assert.assertEquals(expected, actual, "Sample Message to explain Assert Fail");
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }







}