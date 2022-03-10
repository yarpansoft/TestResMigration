package XTRA;

import org.testng.ITestResult;

public class TestExtra {

    //@AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            String testResult = String.valueOf(result.getThrowable());
            System.out.println("AfterMethod " + testResult);
        }
    }

}
