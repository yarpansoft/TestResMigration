package XTRA;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class UnirestAPI {


    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    @Test
    public void testGetMethod() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.get(BASE_URL + "/users").asJson();
        System.out.println(response.getBody());
        System.out.println("\nFIRST: " + response.getBody().getArray().get(0));

        Assert.assertEquals(response.getStatus(), 200);
    }

    @Test
    public void testPostMethod() throws UnirestException {
        HttpResponse<JsonNode> response = Unirest.post(BASE_URL + "/posts").field("title", "QA Group")
                .field("body", "Nothing to add").field("userId", 1).asJson();

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 201);
    }

    @Test
    public void testPutMethod() throws UnirestException {
        HttpResponse<String> response = Unirest.put(BASE_URL + "/posts/1")
                .body("{\"id\":\"1\",\"title\":\"QA Group Rules\",\"body\":\"Nothing\",\"userId\":\"1\"}").asString();

        System.out.println(response.getBody());
        Assert.assertEquals(response.getStatusText(), "OK");
    }

    @Test
    public void testDeleteMethod() throws UnirestException {
        HttpResponse<String> response = Unirest.delete(BASE_URL + "/posts/1").asString();

        System.out.println(response.getBody());

        Assert.assertEquals(response.getStatus(), 200);
    }
}
