package daoTest;

import dao.UserDaoCollection;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.CoreMatchers.is;

import static org.junit.Assert.assertThat;


public class UserDaoCollectionTest {

    private UserDaoCollection userDaoColl;
    private User user;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        userDaoColl = new UserDaoCollection();
        user = new User("","Willem","","","","",true,"","","");
        user1 = new User("","Wesley","","","","",true,"","","");
        user2 = new User("","Frank","","","","",true,"","","");
        user3 = new User("","Richard","","","","",true,"","","");

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        userDaoColl.add(user);
        assertThat(1,is(userDaoColl.Count()));
    }

    @Test
    public void remove() {
        userDaoColl.remove(user);
        assertThat(0,is(userDaoColl.Count()));
    }

    @Test
    public void removeNonExistingUser() {
        userDaoColl.remove(user);
        assertThat(0,is(userDaoColl.Count()));
    }

    @Test
    public void findByName() {
        userDaoColl.add(user3);
        assertThat(user3 ,is(userDaoColl.findByName("Richard")));
    }

    @Test
    public void update() {
        userDaoColl.add(user3);
        User u = userDaoColl.findByName("Richard");
        u.setName("Tony");
        userDaoColl.update(u);
        assertThat(u,is(userDaoColl.findByName("Tony")));
    }

    @Test
    public void getFollowers() {
        userDaoColl.add(user3);
        userDaoColl.getFollowers(user3);
        assertThat(0,is(userDaoColl.findByName("Richard").getFollowing().size()));
    }

    @Test
    public void followUser() {
        userDaoColl.followUser(user1,user2);
        assertThat(1, is(user1.getFollowing().size()));
    }

    @Test
    public void unfollowUser() throws IllegalArgumentException {
        userDaoColl.followUser(user1,user2);
        userDaoColl.unfollowUser(user1,user2);
        assertThat(0, is(user1.getFollowing().size()));
    }

    @Test
    public void login() {
        userDaoColl.add(user3);
        userDaoColl.login("Richard","");
        assertThat(true,is(userDaoColl.login("Richard","")));
    }
}