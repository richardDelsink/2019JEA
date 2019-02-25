package daoTest;

import dao.MessageDaoCollection;
import dao.StepDaoCollection;
import domain.Comment;
import domain.Journey;
import domain.Step;
import domain.User;
import interceptor.MessageInterceptor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.interceptor.Interceptors;
import java.util.Calendar;
import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MessageDaoCollectionTest {

    private StepDaoCollection stepDaoColl;
    private MessageDaoCollection messageDaoColl;
    private Comment comment;
    private Comment comment1;
    private User user;
    private Step step;
    private Journey journey;
    private User user1;

    @Before
    public void setUp() throws Exception {
        messageDaoColl = new MessageDaoCollection();
        stepDaoColl = new StepDaoCollection();
        user = new User(1,"","Willem","","","","",true,"","","");
        user1 = new User(2,"","Willem2","","","","",true,"","","");
        int noOfDays = 14;
        Calendar cal = Calendar.getInstance();
        Date startdate = cal.getTime();
        cal.add(Calendar.DAY_OF_YEAR, noOfDays);
        Date date = cal.getTime();
        journey = new Journey("Canada","So hot",startdate,date,"Friends",2);
        step = new Step(journey,"Mont","Test",startdate,"There is no story");
        comment = new Comment(0,1,"vet goede spull te zien");
        comment1 = new Comment(0,2,"vet");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void remove() {
    }

    @Test
    public void findByName() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getComments() {
    }

    @Test
    public void add() {
        messageDaoColl.add(comment);
        assertThat(1,is(messageDaoColl.getCommentsByStep(step).size()));
        messageDaoColl.add(comment1);
        assertThat(2,is(messageDaoColl.getCommentsByStep(step).size()));

    }

    @Test
    public void getCommentsByStep() {
        messageDaoColl.add(comment);
        messageDaoColl.add(comment1);
        messageDaoColl.getCommentsByStep(step);
        assertThat(2,is(messageDaoColl.getCommentsByStep(step).size()));
    }
}