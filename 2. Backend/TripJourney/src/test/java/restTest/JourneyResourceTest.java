package restTest;

import domain.Journey;
import domain.User;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.Calendar;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.*;

public class JourneyResourceTest {

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
    public void getJourney() {
        given().
                contentType("application/json").
                pathParam("journeyname", "Canada").
                when().
                get("/Journey/get/{journeyname}").
                then().log().ifError().
                statusCode(200);
    }

    @Test
    public void createJourney() {
        int noOfDays = 15;
        Calendar cal = Calendar.getInstance();
        java.util.Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        java.util.Date date = cal.getTime();

        Journey journey = new Journey("Canada", "Going to Canada all alone",startdate,date,"friends",1);

        given().
                contentType("application/json").
                body(journey).
                log().all().
                when().
                post("/Journey").
                then().log().all().
                statusCode(200);
    }

    @Test
    public void getJourneyByUser() {
        given().
                contentType("application/json").
                pathParam("name", "Richard").
                when().
                get("/Journey/search/{name}").
                then().
                statusCode(200);
    }

    @Test
    public void deleteJourney() {
        given().
                pathParam("name", "Canada").
                when().
                delete("/Journey/delete/{name}").
                then().
                statusCode(204);
    }
}