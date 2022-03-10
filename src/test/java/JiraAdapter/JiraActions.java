package JiraAdapter;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class JiraActions {

    public void createJiraIssue(String issueSummary, String issueDescription) {
        String body = "{\"fields\": {\"summary\": \""+ issueSummary + "\",\"issuetype\": {\"name\": \"Bug\"},\"project\": {\"key\": \""
                + JiraSettings.projectName + "\"},\"description\": {\"type\": \"doc\",\"version\": 1,\"content\": [{\"type\": "
                + "\"paragraph\",\"content\": [{\"text\": \"" + issueDescription + "\",\"type\": \"text\"}]}]}}}";
        String requestUrl = "https://" + JiraSettings.siteName + JiraSettings.jiraEndpointIssue;
        HttpResponse<JsonNode> response = responseBuilderPost(requestUrl, body);
        //System.out.println("response: " + response);
    }


    public void setJiraIssueStatus(int issueID, int issueStatusID) {
        String requestUrl = "https://" + JiraSettings.siteName + JiraSettings.jiraEndpointIssue + issueID + "/transitions";
        String body = "{\"transition\": {\"id\":\"" + issueStatusID + "\"}}";
        //HttpResponse<JsonNode> response = responseBuilderPost(requestUrl, body);
        responseBuilderPost(requestUrl, body);
    }


    public boolean isJiraIssueOpened(String issueSummary) {
        boolean isOpenIssueExist = false;
        String requestUrl = "https://" + JiraSettings.siteName + JiraSettings.jiraEndpointSearch + issueSummary;
        HttpResponse<JsonNode> response = responseBuilderGet(requestUrl);
        int searchResultNumber = getIssueNumbersFromResponse (response);
        System.out.println("found issues with given summary: " + searchResultNumber);

        for (int i=0; i<searchResultNumber; i++) {
            int issueID = getIssueIDFromResponse(response, i);
            String issueStatusName = getIssueStatusNameFromResponse(response, i);
            System.out.println("[" + i + "] issueID: " + issueID + ", issueStatusName: " + issueStatusName);

            if (issueStatusName.equals(JiraSettings.issueOpenStatus1)||issueStatusName.equals(JiraSettings.issueOpenStatus2)
                    ||issueStatusName.equals(JiraSettings.issueOpenStatus3)){
                isOpenIssueExist = true;
                System.out.println("isOpenIssueExist: true. No need to create issue");
                break;
            }
        }
        return isOpenIssueExist;
    }


    public void closeJiraIssues(String issueSummary) {
        String requestUrl = "https://" + JiraSettings.siteName + JiraSettings.jiraEndpointSearch + issueSummary;
        HttpResponse<JsonNode> response = responseBuilderGet(requestUrl);

        int searchResultNumber = Integer.parseInt(response.getBody().getObject().get("total").toString());
        System.out.println("found issues with given summary: " + searchResultNumber);

        for (int i=0; i<searchResultNumber; i++) {
            int issueID = getIssueIDFromResponse(response, i);
            String issueStatusName = getIssueStatusNameFromResponse(response, i);
            System.out.println("[" + i + "] issueID: " + issueID + ", issueStatusName: " + issueStatusName);

            if (issueStatusName.equals(JiraSettings.issueOpenStatus1)||issueStatusName.equals(JiraSettings.issueOpenStatus2)
                    ||issueStatusName.equals(JiraSettings.issueOpenStatus3)){
                System.out.println("issueStatusName: " + issueStatusName + ", changing status...");
                setJiraIssueStatus(issueID, JiraSettings.transitionIdDone);
            }else{
                System.out.println("issueStatusName: " + issueStatusName + " is OK, nothing to do with issue");
            }
        }
    }



    public int getJiraIssueIDBySummary(String issueSummary){
        int issueID;
        String requestUrl = "https://" + JiraSettings.siteName + ".atlassian.net/rest/api/3/search?jql=summary~" + issueSummary;
        HttpResponse<JsonNode> response = responseBuilderGet(requestUrl);

        int searchResultNumber = Integer.parseInt(response.getBody().getObject().get("total").toString());
        System.out.println("response Status: " + response.getStatus());
        System.out.println("found: " + searchResultNumber);

        if (searchResultNumber == 1){
            issueID = Integer.parseInt(response.getBody().getObject().getJSONArray("issues").getJSONObject(0).get("id").toString());
            System.out.println("issue id: " + issueID);
        }else{
            System.out.println("FAILED! found 0 or more than one NOT CLOSED issue with given issueSummary");
            issueID = -1;
        }
        return issueID;
    }




    public HttpResponse<JsonNode> responseBuilderGet(String requestUrl){
    HttpResponse<JsonNode> response = null;
        try {
        response = Unirest.get(requestUrl)
                .basicAuth(JiraSettings.userName, JiraSettings.userToken)
                .header("Accept", "application/json")
                .header("Content-Type", "application/json")
                .asJson();
            System.out.println("response Status: " + response.getStatus());
    } catch (UnirestException e) {
        e.printStackTrace();
    }
        return response;
    }


    public HttpResponse<JsonNode> responseBuilderPost(String requestUrl, String body){
        HttpResponse<JsonNode> response = null;
        try {
            response = Unirest.post(requestUrl)
                    .basicAuth(JiraSettings.userName, JiraSettings.userToken)
                    .header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .body(body)
                    .asJson();
            System.out.println("response Status: " + response.getStatus());
            System.out.println("response: " + response);
        } catch (UnirestException e) {
            e.printStackTrace();
        }
        return response;
    }

    public int getIssueNumbersFromResponse(HttpResponse<JsonNode> response){
        return Integer.parseInt(response.getBody().getObject().get("total").toString());
    }

    public int getIssueIDFromResponse(HttpResponse<JsonNode> response, int issueIndex){
        return Integer.parseInt(response.getBody().getObject().getJSONArray("issues").getJSONObject(issueIndex).get("id").toString());
    }

    public String getIssueStatusNameFromResponse(HttpResponse<JsonNode> response, int issueIndex){
        return response.getBody().getObject().getJSONArray("issues").getJSONObject(issueIndex).getJSONObject("fields").getJSONObject("status").get("name").toString();
    }
}
