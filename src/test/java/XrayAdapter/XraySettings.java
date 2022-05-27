package XrayAdapter;

import java.util.ArrayList;
import java.util.List;

public class XraySettings {

    /** PART 1 - Xray PROJECT SETTINGS **/
    public static String client_id = "28984BAA666F4F4A87DB365E918DA03B";
    public static String client_secret = "df38857f67acc72dd600bf6338171664e7714c02cc31995832b887f0cc9fed35";
    public static String projectKey = "TRM";
    public static String testSetDefaultForNewTests = "TRM-63";
    public static String testExecutionKeyDefault = "TRM-50";
    public static String testPlanKeyDefault = "TRM-59";

    public static boolean isCreateNewTestExecution = true; //is to create new TestExecution for each run? if no, use Default
    public static boolean isUseLatestKeyForDuplicateTests = true; //if Jira has some Tests with same name, then use the latest one (if False, then use first one)
    public static String newTestTypeDefault = "Automation";
    public static String commentForSuccessTest = "Automation test passed successfully";
    public static String executionSummary = "Sample Summary for Automated execution";
    public static String executionDescription = "Sample Description of Automated execution";

    /** Jira PROJECT Settings **/
    public static String jiraSiteName = "testresmig";
    public static String jiraUserName = "testsigman@gmail.com";
    public static String jiraUserToken = "cGHrKUFs6RgUuGPwiEBB9D10";



    /** PART 2 - Xray API Settings
     * do not change until global PractiTest API settings changed **/
    public static String baseURL = "https://xray.cloud.getxray.app/api/v2/";
    public static String endpointAauthenticate = "authenticate";
    public static String endpointImportExecution = "import/execution";
    public static String endpointImportTest = "import/test/bulk";
    /** Jira API Settings
     * do not change until global Jira API settings changed **/
    public static String jiraEndpointSearch = ".atlassian.net/rest/api/3/search?jql=summary~";
    public static String jiraSearchCondition = "%20AND%20issuetype=Test";



    /** PART 3 - Runtime Parameters **/
    private static String bearerToken;
    public static String getBearerToken() {
        return bearerToken;
    }
    public static void setBearerToken(String newToken) {
        bearerToken = newToken;
    }

    private static List<String[]> testResults = new ArrayList<String[]>();
    public static void addTestResults(String[] testResCur) {
        testResults.add(testResCur);
    }
    public static List<String[]> getTestResults() {
        return testResults;
    }

}





