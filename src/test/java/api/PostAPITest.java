package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PostAPITest {

    @Test
    public void createPostTest() {

        // 🔹 Request body (JSON)
        String requestBody = "{\n" +
                "  \"title\": \"API Testing\",\n" +
                "  \"body\": \"Learning RestAssured\",\n" +
                "  \"userId\": 1\n" +
                "}";

        // 🔹 Send POST request
        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .body(requestBody)
                .when()
                .post("https://jsonplaceholder.typicode.com/posts");

        // 🔹 Print response
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.asPrettyString());

        // ✅ Assertions
        Assert.assertEquals(response.getStatusCode(), 201, "Status code mismatch");

        Assert.assertEquals(
                response.jsonPath().getString("title"),
                "API Testing",
                "Title mismatch"
        );

        Assert.assertEquals(
                response.jsonPath().getString("body"),
                "Learning RestAssured",
                "Body mismatch"
        );

        Assert.assertEquals(
                response.jsonPath().getInt("userId"),
                1,
                "UserId mismatch"
        );

        // ✅ Validate ID is generated
        Assert.assertNotNull(response.jsonPath().get("id"), "ID not generated");
    }
}
