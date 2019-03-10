package daoTest;

import dao.StepDaoCollection;
import domain.Journey;
import domain.Step;
import domain.User;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class StepDaoCollectionTest {

    private StepDaoCollection stepDaoColl;
    private Journey journey;
    private User user;
    private User user1;
    private Step step;
    private Step step1;

    @Before
    public void setUp() throws Exception {
        stepDaoColl = new StepDaoCollection();
        user = new User("","Willem","","","","",true,"","","");
        user1 = new User("","Willem2","","","","",true,"","","");
        int noOfDays = 12;
        Calendar cal = Calendar.getInstance();
        Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date = cal.getTime();
        journey = new Journey("Canada","So hot",startdate,date,"Friends",1);
        step = new Step(journey,"Mont","Test",startdate,"There is no story");
        step1 = new Step(journey,"Mont2","Test2",date,"There is no story2");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void remove() {
        stepDaoColl.add(step);
        assertThat(1,is(stepDaoColl.Count()));
        stepDaoColl.remove(step);
        assertThat(0,is(stepDaoColl.Count()));
    }

    @Test
    public void findByName() {
        stepDaoColl.add(step);
        assertThat("Test",is(stepDaoColl.findByName(step.getStepName()).getStepName()));
    }

    @Test
    public void add() {
        stepDaoColl.add(step);
        assertThat(1,is(stepDaoColl.Count()));
    }

    @Test
    public void getStepByJourney() {
        stepDaoColl.add(step);
        stepDaoColl.add(step1);
        assertThat(2,is(stepDaoColl.getStepByJourney(journey).size()));
    }

    @Test
    public void likeStep() {
        stepDaoColl.likeStep(step, user);
        assertThat(1,is(step.getLike().size()));
        stepDaoColl.likeStep(step, user1);
        assertThat(2,is(step.getLike().size()));
    }

    @Test
    public void unlikeStep() {
        stepDaoColl.likeStep(step, user);
        stepDaoColl.likeStep(step, user1);
        assertThat(2,is(step.getLike().size()));
        stepDaoColl.unlikeStep(step,user);
        assertThat(1,is(step.getLike().size()));
    }

    @Test
    public void findStepById() {
        stepDaoColl.add(step);
        assertThat(step,is(stepDaoColl.findStepById(step.getStepId())));
    }
}