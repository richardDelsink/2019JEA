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
    private UserDaoJpa userDao;


    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJpa();
        userDao.setEm(em);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getFollowers() {
    }

    @Test
    public void followUser() {
    }

    @Test
    public void unfollowUser() {
    }

    @Test
    public void login() {
    }

    @Test
    public void add() {
        User user = new User("","Richard","","","","",true,"","","");
        tx.begin();
        userDao.add(user);
        User userObject = userDao.findByName("Richard");
        tx.commit();
        assertThat(userObject,is(user));
    }

    @Test
    public void remove() {
    }

    @Test
    public void update() {
    }

    @Test
    public void findByName() {
        User user = new User("","Willem","","","","",true,"","","");
        tx.begin();
        userDao.add(user);
        User userObject = userDao.findByName("Willem");
        tx.commit();
        assertThat(userObject,is(user));
    }

    @Test
    public void setEm() {
    }
}