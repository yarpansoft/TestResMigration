package TestPackage;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(TestResultListener.class)
public class TestClass {

    @Test
    public void test_01() {
        boolean actual = true;
        boolean expected = false;
        Assert.assertEquals(actual, expected, "Sample Message to explain Assert Fail");
    }



    //@AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testResult = String.valueOf(result.getThrowable());
            System.out.println("AfterMethod " + testResult);
        }
    }


}