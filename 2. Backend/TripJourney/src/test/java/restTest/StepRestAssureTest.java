package restTest;

import domain.Step;
import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class StepRestAssureTest {


    @Before
    public void setUp() throws Exception {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/TripJourney/api/";
    }

    @Test
    public void getStep() {
        given().when().get("/Step/Timboektoe").then().statusCode(200);
    }

    @Test
    public void createStep() {


    }

    @Test
    public void getStepByUser() {
        given().
                contentType("application/json").
                pathParam("name", "Richard").
                when().
                get("/Step/search/{name}").
                then().
                statusCode(200);
    }

    @Test
    public void deleteStep() {
        given().
                pathParam("name", "Timboektoe").
                when().
                delete("/Step/{name}").
                then().log().ifError().
                statusCode(204);
    }

    @Test
    public void likeStep() {
    }

    @Test
    public void unlikeStep() {
    }
}