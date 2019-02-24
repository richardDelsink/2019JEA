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

public class UserDaoJpaT {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("UserDaoTestPU");
    private EntityManager em;
    private EntityTransaction tx;
    private UserDaoJpa userDao;

    public UserDaoJpaT() {
    }

    @Before
    public void setUp() {
        try {
            new DatabaseCleaner(emf.createEntityManager()).clean();
        } catch (SQLException ex) {
            Logger.getLogger(UserDaoJpaT.class.getName()).log(Level.SEVERE, null, ex);
        }
        em = emf.createEntityManager();
        tx = em.getTransaction();

        userDao = new UserDaoJpa();
        userDao.setEm(em);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void findUser() {
        User user = new User(1,"","Willem","","","","",true,"","","");
        tx.begin();
        userDao.add(user);
        User userObject = userDao.findByName("Willem");
        tx.commit();
        assertThat(userObject,is(user));
    }
    @Test
    public void savingUserSucceesful() {
        User user = new User(2,"","Richard","","","","",true,"","","");
        tx.begin();
        userDao.add(user);
        User userObject = userDao.findByName("Richard");
        tx.commit();
        assertThat(userObject,is(user));
    }
}
