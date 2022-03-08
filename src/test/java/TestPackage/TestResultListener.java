package TestPackage;

import JiraAdapter.JiraActions;
import JiraAdapter.JiraSettings;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestResultListener extends TestListenerAdapter {

        @Override
        public void onTestFailure(ITestResult result) {
            // do what you want to do
                String testFullName = result.getInstanceName() + "." + result.getName();
                System.out.println("onTestFailure ***********");
                System.out.println("testFullName = " + testFullName);

                if (!JiraActions.ifIssueExist (testFullName) ||
                        JiraActions.getIssueStatus (testFullName) != JiraSettings.issueStatusOpen){
                        // create issue
                }

                String testResult = String.valueOf(result.getThrowable());
                System.out.println(testResult);
        }


        @Override
        public void onTestSuccess(ITestResult result) {
                String testFullName = result.getInstanceName() + "." + result.getName();
                System.out.println("onTestSuccess ***********");

                if (JiraActions.ifIssueExist (testFullName) &&
                        JiraActions.getIssueStatus (testFullName) == JiraSettings.issueStatusOpen){
                        // close issue
                }
        }


        @Override
        public void onTestSkipped(ITestResult result) {
            // do what you want to do
                System.out.println("onTestSkipped ***********");
                System.out.println("result.getSkipCausedBy() = " + result.getSkipCausedBy().toString());
        }




    }
