package TestRailAdapter;

public class TestRailSettings {
    /** PART 1 - PROJECT SETTINGS **/
    public static String siteName = "testsigman2";
    public static int projectId = 1;

    public static String userName = "testsigman+2@gmail.com";
    public static String userPassword = "GW2vFSyVt/FW8YVnxGcH";

    public static String testResultCommentForSuccessTest = "Automation test passed successfully";
//    public static String newTestRunPrefixText = "Automation TestRun "; //Prefix for name of new created Test Run (before timestamp)
//    public static String newTestRunTimeStampFormat = "yyyy.MM.dd - HH:mm:ss";
//    public static boolean isCreateNewTestRunEachTime = true;
    public static boolean isUseLastRun = false; //If true then testRunIdDefault skipped in logic
    public static int testRunIdDefault = 3; // Test Run ID for test results
    public static int testSectionIdDefault = 2; // Test Suite where new Test Cases will be created if there are no present yet


    /** PART 2 - TESTRAIL API SETTINGS **/
    public static int testStatusId_passed = 1;
    public static int testStatusId_failed = 5;
    public static String testrailEndpointBase = ".testrail.com/index.php?/api/v2/";
    public static String testrailEndpointAddResult = "add_result_for_case/";
    public static String testrailEndpointGetCases = "get_cases/";
    public static String testrailEndpointAddCase = "add_case/";
    public static String testrailEndpointGetRuns = "get_runs/";
    public static String testrailEndpointAddRun = "add_run/";





}
