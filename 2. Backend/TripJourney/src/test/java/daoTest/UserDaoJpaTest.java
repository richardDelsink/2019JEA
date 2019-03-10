package daoTest;

import dao.UserDaoJpa;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class UserDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Journey");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDaoJpa userDaoJPA;
    private User user;
    private User user1;
    private User user2;
    private User user3;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDaoJPA = new UserDaoJpa();
        userDaoJPA.setEm(em);

        user = new User("","Willem","","","","",true,"","","");
        user1 = new User("","Wesley","","","","",true,"","","");
        user2 = new User("","Frank","","","","",true,"","","");
        user3 = new User("","Richard","","","","",true,"","","");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFollowers() {
        tx.begin();
        userDaoJPA.add(user);
        tx.commit();
        assertThat(true, is(userDaoJPA.getFollowers(user).isEmpty()));
    }

    @Test
    public void followUser() {
        tx.begin();
        userDaoJPA.add(user);
        userDaoJPA.add(user1);
        userDaoJPA.followUser(user,user1);
        tx.commit();
        assertThat(1, is(userDaoJPA.getFollowers(user1).size()));
    }

    @Test
    public void unfollowUser() {
        tx.begin();
        userDaoJPA.add(user);
        userDaoJPA.add(user1);
        userDaoJPA.followUser(user,user1);
        tx.commit();
        assertThat(1, is(userDaoJPA.getFollowers(user1).size()));
        tx.begin();
        userDaoJPA.unfollowUser(user,user1);
        tx.commit();
        assertThat(0, is(userDaoJPA.getFollowers(user1).size()));
    }


    @Test
    public void add() {
        tx.begin();
        userDaoJPA.add(user);
        tx.commit();
        assertThat(user, is(userDaoJPA.findByName("Willem")));
    }

    @Test
    public void remove() {

        tx.begin();
        userDaoJPA.add(user1);
        tx.commit();
        assertThat(user1, is(userDaoJPA.findByName("Wesley")));
        tx.begin();
        userDaoJPA.remove(user1);
        tx.commit();
        String s = "";
        try {
            userDaoJPA.findByName("Wesley");
        } catch(ArrayIndexOutOfBoundsException e) {
            s = "Not found";
        }
        assertThat("Not found" ,is(s));
    }

    @Test
    public void update() {
        tx.begin();
        userDaoJPA.add(user);
        tx.commit();
        assertThat(user, is(userDaoJPA.findByName("Willem")));
        tx.begin();
        user = userDaoJPA.findByName("Willem");
        user.setName("Pimple");
        userDaoJPA.update(user);
        tx.commit();
        assertThat(user.getName(), is(userDaoJPA.findByName("Pimple").getName()));

    }

    @Test
    public void findByName() {
        tx.begin();
        userDaoJPA.add(user);
        tx.commit();
        assertThat(user, is(userDaoJPA.findByName("Willem")));
    }


}