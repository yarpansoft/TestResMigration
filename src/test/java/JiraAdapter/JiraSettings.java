package JiraAdapter;

public class JiraSettings {
    //https://{siteName}.atlassian.net/browse/{projectName}-505
    public static String siteName = "testresmig";
    public static String projectName = "TRM";
    public static String jiraEndpointIssue = ".atlassian.net/rest/api/3/issue/";
    public static String jiraEndpointSearch = ".atlassian.net/rest/api/3/search?jql=summary~";

    public static String userName = "testsigman@gmail.com";
    public static String userToken = "4UWrqHXe8FdLA6xvzuZEE5C8";

    public static boolean isNewIssueShouldBeMovedToSprint = true;
    public static String issueOpenStatus1 = "To Do";
    public static String issueOpenStatus2 = "In Progress";
    public static String issueOpenStatus3 = "Testing";
    public static int transitionIdInProgress = 21;
    public static int transitionIdDone = 31;



}
