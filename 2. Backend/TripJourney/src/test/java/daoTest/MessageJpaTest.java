package daoTest;

import dao.JourneyDaoJpa;
import dao.MessageJpa;
import dao.StepJpa;
import dao.UserDaoJpa;
import domain.Journey;
import domain.Message;
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
import javax.xml.stream.events.Comment;

import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MessageJpaTest {
    EntityManagerFactory emf = Persistence.createEntityManagerFactory("Journey");
    private EntityManager em;
    private EntityTransaction tx;
    private MessageJpa messageDaoJPA;
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

    private Message canadaComment;
    private Message canadaComment1;
    private Message canadaComment2;

    @Before
    public void setUp() throws Exception {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(MessageJpaTest.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDaoJpa = new UserDaoJpa();
        journeyDaoJpa = new JourneyDaoJpa();
        stepDaoJpa = new StepJpa();
        messageDaoJPA = new MessageJpa();

        journeyDaoJpa.setEm(em);
        userDaoJpa.setEm(em);
        stepDaoJpa.setEm(em);
        messageDaoJPA.setEm(em);

        // add dummy users
        dummyUsers();

        // add dummy journeys
        dummyJourney();

        // add dummy steps
        dummyStep();

        // add dummy comments not in db
        dummyComments();
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

        tx.begin();
        stepDaoJpa.add(stepCanada);
        stepDaoJpa.add(stepCanada2);
        tx.commit();


    }

    public void dummyComments(){
        canadaComment = new Message(stepCanada.getStepId(),richard.getId(),"vet goede spull te zien");
        canadaComment1 = new Message(stepCanada.getStepId(),willem.getId(),"Dit ziet er goed uit");
        canadaComment2 = new Message(stepCanada.getStepId(),frank.getId(),"Geweldig!!!!!!!!!!!!!!");
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void getCommentsByStep() {
        tx.begin();
        messageDaoJPA.add(canadaComment);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment1);
        tx.commit();
        assertThat(2,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment2);
        tx.commit();
        assertThat(0,is(messageDaoJPA.getCommentsByStep(stepCanada2).size()));
    }

    @Test
    public void add() {
        tx.begin();
        messageDaoJPA.add(canadaComment);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getComments(richard).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment1);
        tx.commit();
        assertThat(2,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment2);
        tx.commit();
        assertThat(3,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

    }

    @Test
    public void remove() {
        tx.begin();
        messageDaoJPA.add(canadaComment);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment1);
        tx.commit();
        assertThat(2,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.add(canadaComment2);
        tx.commit();
        assertThat(0,is(messageDaoJPA.getCommentsByStep(stepCanada2).size()));

        tx.begin();
        messageDaoJPA.remove(canadaComment);
        tx.commit();
        assertThat(2,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.remove(canadaComment1);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));

        tx.begin();
        messageDaoJPA.remove(canadaComment2);
        tx.commit();
        assertThat(0,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));
    }

    @Test
    public void update() {
        tx.begin();
        messageDaoJPA.add(canadaComment);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getComments(richard).size()));

        tx.begin();
        Message m = messageDaoJPA.findByName(canadaComment.getComment());
        m.setComment("Dit is de bedoeling");
        messageDaoJPA.update(m);
        tx.commit();
        assertThat(1,is(messageDaoJPA.getCommentsByStep(stepCanada).size()));
        assertThat(m.getComment(),is(messageDaoJPA.findByName(canadaComment.getComment()).getComment()));
    }

    @Test
    public void findByName() {
        tx.begin();
        messageDaoJPA.add(canadaComment);
        tx.commit();
        assertThat("vet goede spull te zien",is(messageDaoJPA.findByName(canadaComment.getComment()).getComment()));
    }
}