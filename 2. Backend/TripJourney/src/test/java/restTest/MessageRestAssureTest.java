package restTest;

import domain.Message;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class MessageRestAssureTest {

    @Before
    public void setUp() throws Exception {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/TripJourney/api/";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getComment() {
        given().when().get("/Message/").then().statusCode(405);
    }

    @Test
    public void createComment() {
        Message canadaComment = new Message(3,78,"vet goede spull te zien");

        given().
                contentType("application/json").
                body(canadaComment).
                when().
                post("/Message").
                then().log().all().
                statusCode(200);
    }

    @Test
    public void getCommentByStep() {
        given().
                contentType("application/json").
                pathParam("name", "Willem").
                when().
                get("/Comment/search/{name}").
                then().
                statusCode(404);
    }

    @Test
    public void deleteComment() {
        given().
                pathParam("name", "Timboektoe").
                when().
                delete("/Message/{name}").
                then().
                statusCode(500);
    }
}