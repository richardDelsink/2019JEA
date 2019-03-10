package restTest;

import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserResourceTest {

    Client client;
    WebTarget root;
    static final String PATH = "/TripJourney/api/User/";
    static final String BASEURL = "http://localhost:8080" + PATH;

    public UserResourceTest() {
    }

    @Before
    public void setUp() throws Exception {
        this.client = ClientBuilder.newClient();
        this.root = this.client.target(BASEURL);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getUser() {

    }

    @Test
    public void createUser() {
        String mediaType = MediaType.APPLICATION_JSON;
        User user = new User("","Willem","","","","",true,"","","");

        final Entity<User> entity = Entity.entity(user, mediaType);
        User usr = this.root.request().post(entity, User.class);
        assertThat(usr, is(user));

      /*  Student result = this.root.path(student.getName()).request(mediaType).get(Student.class);
        assertThat(result, is(student));


        Collection<Student> studenten = this.root.request().get(new GenericType<Collection<Student>>() {});
        // startup: 6 persons
        assertThat(studenten.size(), is(7));
        assertThat(studenten, hasItem(student));


        this.root.path(student.getName()).request(mediaType).delete(Student.class);

        studenten = this.root.request().get(new GenericType<Collection<Student>>() {});

        assertThat(studenten.size(), is(6));*/
    }

    @Test
    public void deleteUser() {
    }

    @Test
    public void getFollowers() {
    }

    @Test
    public void followUser() {
    }

    @Test
    public void getFollowing() {
    }

    @Test
    public void unfollowUser() {
    }
}