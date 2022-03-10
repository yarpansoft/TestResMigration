package XTRA;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

import java.io.IOException;
import static javafx.scene.input.KeyCode.T;

public class JiraCreate {

    public void createIssue() {
        // The payload definition using the Jackson library
        JsonNodeFactory jnf = JsonNodeFactory.instance;
        ObjectNode payload = jnf.objectNode();
        {
            ObjectNode update = payload.putObject("update");
            {
            }
            ObjectNode fields = payload.putObject("fields");
            {
                fields.put("summary", "Main order flow broken");
                ObjectNode parent = fields.putObject("parent");
                {
                    parent.put("key", "PROJ-123");
                }
                ObjectNode issuetype = fields.putObject("issuetype");
                {
                    issuetype.put("id", "10000");
                }
                ArrayNode components = fields.putArray("components");
                ObjectNode components0 = components.addObject();
                {
                    components0.put("id", "10000");
                }
                fields.put("customfield_20000", "06/Jul/19 3:25 PM");
                ObjectNode customfield_40000 = fields.putObject("customfield_40000");
                {
                    customfield_40000.put("type", "doc");
                    customfield_40000.put("version", 1);
                    ArrayNode content = customfield_40000.putArray("content");
                    ObjectNode content0 = content.addObject();
                    {
                        content0.put("type", "paragraph");
                        content = content0.putArray("content");
                        content0 = content.addObject();
                        {
                            content0.put("text", "Occurs on all orders");
                            content0.put("type", "text");
                        }
                    }
                }
                ArrayNode customfield_70000 = fields.putArray("customfield_70000");
                customfield_70000.add("jira-administrators");
                customfield_70000.add("jira-software-users");
                ObjectNode project = fields.putObject("project");
                {
                    project.put("id", "10000");
                }
                ObjectNode description = fields.putObject("description");
                {
                    description.put("type", "doc");
                    description.put("version", 1);
                    ArrayNode content = description.putArray("content");
                    ObjectNode content0 = content.addObject();
                    {
                        content0.put("type", "paragraph");
                        content = content0.putArray("content");
                        content0 = content.addObject();
                        {
                            content0.put("text", "Order entry fails when selecting supplier.");
                            content0.put("type", "text");
                        }
                    }
                }
                ObjectNode reporter = fields.putObject("reporter");
                {
                    reporter.put("id", "5b10a2844c20165700ede21g");
                }
                ArrayNode fixVersions = fields.putArray("fixVersions");
                ObjectNode fixVersions0 = fixVersions.addObject();
                {
                    fixVersions0.put("id", "10001");
                }
                fields.put("customfield_10000", "09/Jun/19");
                ObjectNode priority = fields.putObject("priority");
                {
                    priority.put("id", "20000");
                }
                ArrayNode labels = fields.putArray("labels");
                labels.add("bugfix");
                labels.add("blitz_test");
                ObjectNode timetracking = fields.putObject("timetracking");
                {
                    timetracking.put("remainingEstimate", "5");
                    timetracking.put("originalEstimate", "10");
                }
                ArrayNode customfield_30000 = fields.putArray("customfield_30000");
                customfield_30000.add("10000");
                customfield_30000.add("10002");
                ObjectNode customfield_80000 = fields.putObject("customfield_80000");
                {
                    customfield_80000.put("value", "red");
                }
                ObjectNode security = fields.putObject("security");
                {
                    security.put("id", "10000");
                }
                ObjectNode environment = fields.putObject("environment");
                {
                    environment.put("type", "doc");
                    environment.put("version", 1);
                    ArrayNode content = environment.putArray("content");
                    ObjectNode content0 = content.addObject();
                    {
                        content0.put("type", "paragraph");
                        content = content0.putArray("content");
                        content0 = content.addObject();
                        {
                            content0.put("text", "UAT");
                            content0.put("type", "text");
                        }
                    }
                }
                ArrayNode versions = fields.putArray("versions");
                ObjectNode versions0 = versions.addObject();
                {
                    versions0.put("id", "10000");
                }
                fields.put("duedate", "2019-05-11");
                fields.put("customfield_60000", "jira-software-users");
                ObjectNode customfield_50000 = fields.putObject("customfield_50000");
                {
                    customfield_50000.put("type", "doc");
                    customfield_50000.put("version", 1);
                    ArrayNode content = customfield_50000.putArray("content");
                    ObjectNode content0 = content.addObject();
                    {
                        content0.put("type", "paragraph");
                        content = content0.putArray("content");
                        content0 = content.addObject();
                        {
                            content0.put("text", "Could impact day-to-day work.");
                            content0.put("type", "text");
                        }
                    }
                }
                ObjectNode assignee = fields.putObject("assignee");
                {
                    assignee.put("id", "5b109f2e9729b51b54dc274d");
                }
            }
        }

// Connect Jackson ObjectMapper to Unirest
        Unirest.setObjectMapper(new ObjectMapper() {
            private com.fasterxml.jackson.databind.ObjectMapper jacksonObjectMapper
                    = new com.fasterxml.jackson.databind.ObjectMapper();

            public <T> T readValue(String value, Class<T> valueType) {
                try {
                    return jacksonObjectMapper.readValue(value, valueType);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }

            public String writeValue(Object value) {
                try {
                    return jacksonObjectMapper.writeValueAsString(value);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        
        
// This code sample uses the  'Unirest' library:
// http://unirest.io/java.html
        HttpResponse<JsonNode> response = null;
        {
            try {
                response = Unirest.post("https://your-domain.atlassian.net/rest/api/3/issue")
                        .basicAuth("email@example.com", "<api_token>")
                        .header("Accept", "application/json")
                        .header("Content-Type", "application/json")
                        .body(payload)
                        .asJson();
            } catch (UnirestException e) {
                e.printStackTrace();
            }
        }

        System.out.println("response Body: " + response.getBody());
        System.out.println("response Status: " + response.getStatus());
    }
}
