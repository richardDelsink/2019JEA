package daoTest;

import dao.JourneyDaoJpa;
import dao.UserDaoJpa;
import domain.Journey;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import util.DatabaseCleaner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class JourneyDaoJpaTest {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Journey");
    private EntityManager em;
    private EntityTransaction tx;
    private JourneyDaoJpa journeyDaoJpa;
    private UserDaoJpa userDaoJpa;

    private User willem;
    private User wesley;
    private User frank;
    private User richard;


    private Journey canada;
    private Journey usa;
    private Journey germany;
    private Journey belgium;

    @Before
    public void setUp() throws Exception {

        // starting up db cleaner with entitymanager
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(JourneyDaoJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDaoJpa = new UserDaoJpa();
        journeyDaoJpa = new JourneyDaoJpa();
        journeyDaoJpa.setEm(em);
        userDaoJpa.setEm(em);

        // some pre-made users
        dummyUsers();

        // some pre-made journeys
        dummyJourney();

    }

    public void dummyUsers() {

        willem = new User("", "Willem", "", "", "", "", true, "", "", "");
        wesley = new User("", "Wesley", "", "", "", "", true, "", "", "");
        frank = new User("", "Frank", "", "", "", "", true, "", "", "");
        richard = new User("", "Richard", "", "", "", "", true, "", "", "");

        tx.begin();
        userDaoJpa.add(willem);
        userDaoJpa.add(wesley);
        userDaoJpa.add(frank);
        userDaoJpa.add(richard);
        tx.commit();
    }


    public void dummyJourney(){
        // setting the startdate and enddate of the journey
        int noOfDays = 15;
        Calendar cal = Calendar.getInstance();
        java.util.Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        java.util.Date date = cal.getTime();

        canada = new Journey("Canada", "Going to Canada all alone",startdate,date,"friends",willem.getId());
        usa = new Journey("Usa", "Going to America all alone",startdate,date,"friends",wesley.getId());
        germany = new Journey("Germany", "Going to Germany all alone",startdate,date,"friends",willem.getId());
        belgium = new Journey("Belgium", "Going to Belgium all alone",startdate,date,"friends",frank.getId());
    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getJourneyByUser() {
        tx.begin();
        journeyDaoJpa.add(canada);
        Journey j = journeyDaoJpa.getJourneyByUser(willem).get(0);
        tx.commit();
        assertThat("Canada", is(j.getJourneyName()));
    }

    @Test
    public void add() {
        tx.begin();
        journeyDaoJpa.add(canada);
        tx.commit();
        assertThat("Canada", is(journeyDaoJpa.findByName(canada.getJourneyName()).getJourneyName()));
    }

    @Test
    public void remove() {

        tx.begin();
        journeyDaoJpa.add(canada);
        tx.commit();
        assertThat("Canada", is(journeyDaoJpa.findByName(canada.getJourneyName()).getJourneyName()));
        tx.begin();
        journeyDaoJpa.remove(canada);
        tx.commit();

        String s = "";
        try {
            journeyDaoJpa.findByName("Canada");
        } catch(ArrayIndexOutOfBoundsException e) {
            s = "Not found";
        }
        assertThat("Not found" ,is(s));
    }

    @Test
    public void update() {
        tx.begin();
        journeyDaoJpa.add(belgium);
        tx.commit();
        assertThat("Belgium", is(journeyDaoJpa.findByName(belgium.getJourneyName()).getJourneyName()));
        tx.begin();
        Journey j = journeyDaoJpa.findByName("Belgium");
        j.setJourneyName("Bali");
        j.setJourneySummary("Going to Bali all alone");
        journeyDaoJpa.update(j);
        tx.commit();

        assertThat("Bali" ,is(journeyDaoJpa.findByName("Bali").getJourneyName()));

    }

    @Test
    public void findByName() {
        tx.begin();
        journeyDaoJpa.add(belgium);
        tx.commit();
        assertThat("Belgium", is(journeyDaoJpa.findByName(belgium.getJourneyName()).getJourneyName()));
    }
}