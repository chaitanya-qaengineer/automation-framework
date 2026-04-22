package api;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class APITest {

    // 👉 Better: pass via JVM arg or env var
    private static final String API_KEY =
            System.getProperty("apiKey", "pub_bd5d17144fe18398a0142b788f509eedd17fb6710c5e52b4648ec71f337db455");

    private static final String BASE_URL =
            "https://reqres.in/api/collections/products/records?project_id=14698";

    @Test
    public void getProductsTest() {

        Response response = RestAssured
                .given()
                .header("x-api-key", API_KEY)
                .when()
                .get(BASE_URL);

        // 🔍 Print response (for learning/debugging)
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response:\n" + response.asPrettyString());

        // ✅ 1. Status code validation
        Assert.assertEquals(response.getStatusCode(), 200, "Status code mismatch");

        // ✅ 2. Total records validation
        int total = response.jsonPath().getInt("meta.total");
        Assert.assertEquals(total, 3, "Total records mismatch");

        // ✅ 3. First product name validation
        String firstProductName = response.jsonPath().getString("data[0].data.name");
        Assert.assertEquals(firstProductName, "Wireless Headphones", "First product name mismatch");

        // ✅ 4. Validate price of second product
        float secondPrice = response.jsonPath().getFloat("data[1].data.price");
        Assert.assertEquals(secondPrice, 24.0f, "Second product price mismatch");

        // ✅ 5. Validate stock status of third product
        boolean inStock = response.jsonPath().getBoolean("data[2].data.in_stock");
        Assert.assertFalse(inStock, "Expected third product to be out of stock");
    }
}