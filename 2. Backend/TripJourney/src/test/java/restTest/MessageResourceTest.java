package restTest;

import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageResourceTest {

    @Before
    public void setUp() throws Exception {
        RestAssured.port = 8080;
        RestAssured.baseURI = "http://localhost";
        RestAssured.basePath = "/TripJourney/api/Message/";
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getComment() {
    }

    @Test
    public void createComment() {
    }

    @Test
    public void getCommentByStep() {
    }

    @Test
    public void deleteComment() {
    }
}