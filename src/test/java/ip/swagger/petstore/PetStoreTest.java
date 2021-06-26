package ip.swagger.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class PetStoreTest {

    @Test
    public void getPetDetails() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "api/v3/pet/";
        Response response = given().contentType(ContentType.JSON).log().all().get("2");
        response.prettyPrint();
        assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getPetCategoryDetails() {
        given().get("http://localhost:8080/api/v3/pet/1").then().statusCode(200).body("category.id",equalTo(2));
        given().get("http://localhost:8080/api/v3/pet/1").then().statusCode(200).body("name",equalTo("Cat 1"));
    }

    @Test
    public void getPetDetailsNotFound() {
        Response response = get("http://localhost:8080/api/v3/pet/20");
        assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getPetDetailsInvalidRequest() {
        RestAssured.baseURI = "http://localhost:8080";
        RestAssured.basePath = "api/v3/pet/";
        Response response = given().contentType(ContentType.JSON).log().all().put("2");
        assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getPetDetailsInvalidBasePath() {
        given().get("http://localhost:8080/api/v3/pet/20").then().statusCode(404);
    }
}
