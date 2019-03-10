package daoTest;

import dao.JourneyDaoJpa;
import dao.StepJpa;
import dao.UserDaoJpa;
import domain.Journey;
import domain.Step;
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
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StepJpaTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Journey");
    private EntityManager em;
    private EntityTransaction tx;
    private StepJpa stepDaoJpa;
    private UserDaoJpa userDaoJpa;
    private JourneyDaoJpa journeyDaoJpa;

    private User willem;
    private User wesley;
    private User frank;
    private User richard;


    private Journey canada;
    private Journey usa;
    private Journey germany;
    private Journey belgium;

    private Step stepCanada;
    private Step stepCanada2;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(StepJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDaoJpa = new UserDaoJpa();
        journeyDaoJpa = new JourneyDaoJpa();
        stepDaoJpa = new StepJpa();

        journeyDaoJpa.setEm(em);
        userDaoJpa.setEm(em);
        stepDaoJpa.setEm(em);

        // add dummy users
        dummyUsers();

        // add dummy journeys
        dummyJourney();

        // add dummy steps but not in db
        dummyStep();
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

        tx.begin();
        journeyDaoJpa.add(canada);
        journeyDaoJpa.add(usa);
        journeyDaoJpa.add(belgium);
        journeyDaoJpa.add(germany);
        tx.commit();

    }

    public void dummyStep(){
        int noOfDays = 15;
        Calendar cal = Calendar.getInstance();
        java.util.Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        java.util.Date date = cal.getTime();

        stepCanada = new Step(canada,"Ontario","Ontario",startdate,"There is no story");
        stepCanada2 = new Step(canada,"Quebec","Quebec",startdate,"There is no story");

    }
    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void getStepByJourney() {
        assertThat(0 ,is(stepDaoJpa.getStepByJourney(canada).size()));
    }

    @Test
    public void likeStep() {
        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));

        tx.begin();
        stepDaoJpa.likeStep(stepCanada,richard);
        tx.commit();
        assertThat( 1,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));

        tx.begin();
        stepDaoJpa.likeStep(stepCanada,wesley);
        tx.commit();
        assertThat( 2,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));
    }

    @Test
    public void unlikeStep() {

        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));

        tx.begin();
        stepDaoJpa.likeStep(stepCanada,richard);
        tx.commit();
        assertThat( 1,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));

        tx.begin();
        stepDaoJpa.likeStep(stepCanada,wesley);
        tx.commit();
        assertThat( 2,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));

        tx.begin();
        stepDaoJpa.unlikeStep(stepCanada,wesley);
        tx.commit();
        assertThat( 1,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));

        tx.begin();
        stepDaoJpa.unlikeStep(stepCanada,richard);
        tx.commit();
        assertThat( 0,is(stepDaoJpa.findByName(stepCanada.getStepName()).getLike().size()));

    }



    @Test
    public void add() {
        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));
    }

    @Test
    public void remove() {

        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));

        tx.begin();
        stepDaoJpa.remove(stepCanada);
        tx.commit();
        String s = "";
        try {
            journeyDaoJpa.findByName(stepCanada.getStepName());
        } catch(ArrayIndexOutOfBoundsException e) {
            s = "Not found";
        }
        assertThat("Not found" ,is(s));

    }

    @Test
    public void update() {
        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));

        tx.begin();
        Step s = stepDaoJpa.findByName(stepCanada.getStepName());
        s.setStepName("Qui");
        stepDaoJpa.update(s);
        tx.commit();

        assertThat("Qui",is(stepDaoJpa.findByName(s.getStepName()).getStepName()));
    }

    @Test
    public void findStepById() {
        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));

        Step s;
        tx.begin();
        s = stepDaoJpa.findStepById(stepCanada.getStepId());
        tx.commit();
        assertThat(stepCanada.getStepId(),is(s.getStepId()));
    }

    @Test
    public void findByName() {
        tx.begin();
        stepDaoJpa.add(stepCanada);
        tx.commit();
        assertThat(stepCanada.getStepName(),is(stepDaoJpa.findByName(stepCanada.getStepName()).getStepName()));
    }
}