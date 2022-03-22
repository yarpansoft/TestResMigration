package JiraAdapter;

public class JiraSettings {

    //https://{siteName}.atlassian.net/browse/{projectName}-505
    public static String siteName = "testresmig";
    public static String projectName = "TRM";
    public static String userName = "testsigman@gmail.com";
    public static String userToken = "ezHxlEAukYRKOYgl007c1E69";

    public static String issueOpenStatus1 = "To Do";
    public static String issueOpenStatus2 = "In Progress";
    public static String issueOpenStatus3 = "Testing";

    public static int transitionIdDone = 31;

    public static boolean isNewIssueShouldBeMovedToSprint = false;

    //public static String[] issueOpenStatuses = new String[] {"To Do", "In Progress", "Testing"};

    //*** Jira API Settings
    //do not change until Jira API settings changed
    public static String jiraEndpointIssue = ".atlassian.net/rest/api/3/issue/";
    public static String jiraEndpointSearch = ".atlassian.net/rest/api/3/search?jql=summary~";
    public static String jiraEndpointSprint = ".atlassian.net/rest/agile/1.0/sprint/";
    public static String jiraEndpointGreenhopper = ".atlassian.net/rest/greenhopper/latest/integration/teamcalendars/sprint/list?jql=project=";
    public static String jiraEndpointSprintSearch = "%20AND%20sprint%20IN%20openSprints()%20AND%20sprint%20NOT%20IN%20futureSprints()%20AND%20sprint%20NOT%20IN%20closedSprints()";


}
