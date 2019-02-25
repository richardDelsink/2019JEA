package daoTest;

import dao.JourneyDaoCollection;
import domain.Journey;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.is;

public class JourneyDaoCollectionTest {

    private JourneyDaoCollection journeyDaoColl;
    private User user;
    private User user1;
    private User user2;
    private User user3;
    private Journey journey;
    private Journey journey1;

    @Before
    public void setUp() throws Exception {
        journeyDaoColl = new JourneyDaoCollection();
        user = new User(1,"","Willem","","","","",true,"","","");
        int noOfDays = 14;
        Calendar cal = Calendar.getInstance();
        Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date = cal.getTime();
        journey = new Journey("Canada","So hot",startdate,date,"Friends",0);
        journey1 = new Journey("Germany","Nice beer",startdate,date,"Friends",0);

    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void add() {
        journeyDaoColl.add(journey);
        assertThat(1,is(journeyDaoColl.Count()));
    }

    @Test
    public void remove() {
        journeyDaoColl.add(journey);
        journeyDaoColl.remove(journey);
        assertThat(0,is(journeyDaoColl.Count()));
    }

    @Test
    public void findByName() {
        journeyDaoColl.add(journey);
        assertThat("Canada",is(journeyDaoColl.findByName(journey.getJourneyName()).getJourneyName()));

    }

    @Test
    public void getJourneyByUser() {
        journeyDaoColl.add(journey);
        assertThat(1,is(journeyDaoColl.getJourneyByUser(user).size()));
    }
}