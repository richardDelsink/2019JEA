package restTest;

import domain.User;
import io.restassured.RestAssured;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static io.restassured.RestAssured.given;

public class UserRestAssuredTest {


    public UserRestAssuredTest() {
    }

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
    public void getUser() {
        given().when().get("/User/Willem").then().statusCode(200);
    }

    @Test
    public void createUser() {
        User user = new User( "","Wes","","","","",true,"","","1234");

                given().
                    contentType("application/json").
                    body(user).
                when().
                    post("/User").
                then().
                    statusCode(200);
    }
    @Test
    public void createUser2() {
       User user = new User( "","Jeroen","","","","",true,"","","1234");

                given().
                contentType("application/json").
                body(user).
                when().
                post("/User").
                        then().
                        statusCode(200);

                given().when().get("/User/Jeroen").then().statusCode(200);

    }

    @Test
    public void deleteUser() {
        User user = new User( "","Wesley","","","","",true,"","","1234");

        given().
                contentType("application/json").
                body(user).
                when().
                post("/User").
                then().
                statusCode(200);

        given().
                pathParam("name", "Wesley").
                when().
                delete("/User/{name}").
                then().
                statusCode(204);
    }

    @Test
    public void getFollowers() {
      User user = new User( "","Paul","","","","",true,"","","1234");
        given().
                contentType("application/json").
                body(user).
                when().
                post("/User").
                then().
                statusCode(200);

        given().
                contentType("application/json").
                pathParam("username", "Paul").
                when().
                get("/User/{username}/followers").
                then().
                statusCode(200);
    }

    @Test
    public void followUser() {
       User user = new User( "","Sam","","","","",true,"","","1234");
        given().
                contentType("application/json").
                body(user).
                when().
                post("/User").
                then().
                statusCode(200);

        given().
                contentType("application/json").
                pathParam("username", "Sam").
                when().
                post("/User/{username}/follow").
                then().
                statusCode(500);

    }

    @Test
    public void getFollowing() {
        User user = new User( "","Bo","","","","",true,"","","1234");
        given().
                contentType("application/json").
                body(user).
                when().
                post("/User").
                then().
                statusCode(200);

        given().
                contentType("application/json").
                pathParam("username", "Bo").
                when().
                get("/User/{username}/following").
                then().
                statusCode(200);
    }

    @Test
    public void unfollowUser() {
        given().
                contentType("application/json").
                pathParam("username", "Sam").
                when().
                post("/User/{username}/unfollow").
                then().
                statusCode(500);
    }
}