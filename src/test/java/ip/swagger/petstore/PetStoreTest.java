package ip.swagger.petstore;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.get;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.testng.Assert.assertEquals;

public class PetStoreTest {

    @BeforeAll
    public void login() {
        given().get("https://petstore3.swagger.io/api/v3/user/login?username=user1&password=12345").then().statusCode(200);
    }

    @Test
    public void addNewPetDetails() {
        JSONObject request = new JSONObject();
        request.put("id", 30);
        request.put("name", "Horse");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("https://petstore3.swagger.io/api/v3/pet").then().statusCode(200);
        JSONObject request1 = new JSONObject();
        request.put("id", 31);
        request.put("name", "Horse1");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("https://petstore3.swagger.io/api/v3/pet").then().statusCode(200);
        JSONObject request2 = new JSONObject();
        request.put("id", 32);
        request.put("name", "Horse2");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("https://petstore3.swagger.io/api/v3/pet").then().statusCode(200);
    }

    @Test
    public void getPetDetails() {

        given().get("https://petstore3.swagger.io/api/v3/pet/30").then().statusCode(200);
    }

    @Test
    public void getPetCategoryDetails() {
        given().get("https://petstore3.swagger.io/api/v3/pet/7").then().statusCode(200).body("category.id", equalTo(1));
        given().get("https://petstore3.swagger.io/api/v3/pet/7").then().statusCode(200).body("category.name", equalTo("Dogs"));
    }

    @Test
    public void getPetDetailsNotFound() {
        Response response = get("https://petstore3.swagger.io/api/v3/pet/255");
        assertEquals(response.getStatusCode(), 404);
    }

    @Test
    public void getPetDetailsInvalidRequest() {
        RestAssured.baseURI = "https://petstore3.swagger.io";
        RestAssured.basePath = "api/v3/pet/";
        Response response = given().contentType(ContentType.JSON).log().all().put("2");
        assertEquals(response.getStatusCode(), 405);
    }

    @Test
    public void getPetDetailsInvalidBasePath() {
        given().get("https://petstore3.swagger.io/api/v4/pet/20").then().statusCode(404);
    }

    @Test
    public void getPetName() {
        given().get("https://petstore3.swagger.io/api/v3/pet/1").then().statusCode(200).body("name", equalTo("Dogs"));
    }

    @Test
    public void modifyPetDetails() {
        JSONObject request = new JSONObject();
        request.put("id", "30");
        request.put("name", "doggieupdatesNOw");
        System.out.println(request.toJSONString());
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request.toJSONString()).when().put("https://petstore3.swagger.io/api/v3/pet").then().statusCode(200);
    }

    @Test
    public void modifyInvalidPetDetails() {
        JSONObject request = new JSONObject();
        request.put("id", 223);
        request.put("name", 2323);
        System.out.println(request.toJSONString());
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().put("https://petstore3.swagger.io/api/v3/pet").then().statusCode(404);
    }

    @Test
    public void modifyPetDetailsInvalidRequest() {
        JSONObject request = new JSONObject();
        request.put("id", 3);
        request.put("name", "asdfghjkdj");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().get("https://petstore3.swagger.io/api/v3/pet").then().statusCode(405);
    }

    @Test
    public void modifyPetDetailsNotFound() {
        JSONObject request = new JSONObject();
        request.put("id", 405);
        request.put("name", "Cats");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().put("https://petstore3.swagger.io/api/v3/pet").then().statusCode(404);
    }


    @Test
    public void addPetDetailsWithAlreadyExistingPetID() {
        JSONObject request = new JSONObject();
        request.put("id", 30);
        request.put("name", "Horse");
        given().header("Content-Type", "application/json").contentType(ContentType.JSON).accept(ContentType.JSON).body(request).when().post("https://petstore3.swagger.io/api/v3/pet").then().statusCode(200);
    }

    @Test
    public void removeFirstPetDetails() {
        given().delete("https://petstore3.swagger.io/api/v3/pet/32").then().statusCode(200);
    }

    @Test
    public void removeInvalidPetIdDetails() {
        given().delete("https://petstore3.swagger.io/api/v3/pet/32").then().statusCode(200);

    }
}