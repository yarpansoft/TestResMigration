package TestPackage;

import JiraAdapter.JiraActions;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;

public class TestResultListener extends TestListenerAdapter {

        @Override
        public void onTestSuccess(ITestResult result) {
                String testFullName = result.getInstanceName() + "." + result.getName();
                System.out.println("TestSuccess! testFullName = " + testFullName);
                JiraActions jiraActions = new JiraActions();
                jiraActions.closeJiraIssues(testFullName);
        }


        @Override
        public void onTestFailure(ITestResult result) {
                String testFullName = result.getInstanceName() + "." + result.getName();
                String testResultException = String.valueOf(result.getThrowable());
                System.out.println("TestFailure! \n testFullName: " + testFullName);
                System.out.println("testResult: \n" + testResultException);
                System.out.println("Seaching for opened Issue for Failed Test...");
                JiraActions jiraActions = new JiraActions();
                if (!jiraActions.isJiraIssueOpened(testFullName)){
                        System.out.println("No opened Issue for Failed Test. Creating Issue...");
                        jiraActions.createJiraIssue(testFullName, testResultException);
                }
//                if (JiraSettings.isNewIssueShouldBeMovedToSprint){
//                        jiraActions.setJiraIssueToCurrentSprint(jiraActions.getJiraCurrentSprintID());
//                }
        }


        @Override
        public void onTestSkipped(ITestResult result) {
                System.out.println("TestSkipped \n result.getSkipCausedBy() = " + result.getSkipCausedBy().toString());
        }



    }
