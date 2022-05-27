package AnyTestPlanName;

import PractiTestAdapter.PractiTestSettings;
import XrayAdapter.XrayActions;
import XrayAdapter.XraySettings;
import org.testng.Assert;
import org.testng.annotations.*;

/** Uncomment those Adapter/Adapters that you want to use **/
//@Listeners(JiraAdapter.JiraListener.class)
//@Listeners(TestRailAdapter.TestRailListener.class)
//@Listeners(PractiTestAdapter.PractiTestListener.class)
@Listeners(XrayAdapter.XrayListener.class)
//@Listeners({JiraAdapter.JiraListener.class, TestRailAdapter.TestRailListener.class})


public class TestSuiteName {
    public static final boolean[] actualResult = {true,false,true};

    /** BeforeSuite for PractiTest Adapter **/
    //@BeforeSuite
    public void selectTestSet(){
        System.out.println("TestSetId = " + PractiTestSettings.getSetId());
        PractiTestAdapter.PractiTestActions practiTestActions = new PractiTestAdapter.PractiTestActions();
        practiTestActions.selectTestSetId(this.getClass().getCanonicalName());
    }

    /** BeforeSuite for Xray Adapter **/
    @BeforeSuite
    public void setXrayToken(){
        XrayAdapter.XrayActions xrayActions = new XrayAdapter.XrayActions();
        xrayActions.createXrayToken();
        System.out.println("getToken: " + XraySettings.getBearerToken());
    }


    @Test
    public void TestLoginSuccess() {
        boolean expectedResult = true;
        Assert.assertEquals(expectedResult, actualResult[0], "Sample Message to explain Assert Fail");
        //System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }


    @Test
    public void TestLoginWithInvalidPassword() {
        boolean expectedResult = true;
        Assert.assertEquals(expectedResult, actualResult[1], "Sample Message to explain Assert Fail");
        //System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }


    @Test
    public void TestLoginWithInvalidUsername() {
        boolean expectedResult = true;
        Assert.assertEquals(expectedResult, actualResult[2], "Sample Message to explain Assert Fail");
        //System.out.println("TestSetId = " + PractiTestSettings.getSetId());
    }


    /** AfterSuite for Xray Adapter **/
    @AfterSuite
    public void publishResults() {
        XrayActions xrayActions = new XrayActions();
        xrayActions.postTestResults();
    }

}