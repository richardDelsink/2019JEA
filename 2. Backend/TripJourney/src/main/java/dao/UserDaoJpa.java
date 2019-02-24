package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;



@Stateless @JPA
public class UserDaoJpa implements UserDao{

    @PersistenceContext(unitName = "userPU")
    private EntityManager em;

    public UserDaoJpa() {
    }

    @Override
    public List<User> getFollowers(User user) {
        TypedQuery<User> query = em.createNamedQuery("user.getFollowing", User.class);
        query.setParameter("user", user);
        return query.getResultList();
    }

    @Override
    public void followUser(User u, User toFollow) {
        u.followUser(toFollow);
        em.merge(u);
    }

    @Override
    public void unfollowUser(User u, User toUnfollow) {
        u.unfollowUser(toUnfollow);
        em.merge(u);
    }

    @Override
    public boolean login(String username, String password) {

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            return false;
        }

        try {
            TypedQuery<User> query = em.createNamedQuery("user.getLogin", User.class);
            query.setParameter("username", username);
            query.setParameter("password", password);

            User user = query.getSingleResult();

            if (user != null) {
                return true;
            }
        } catch (NoResultException e) {
            return false;
        }

        return false;
    }

    @Override
    public void add(User user) {
        em.persist(user);
    }

    @Override
    public void remove(User user) {

        em.remove(em.merge(user));
    }

    @Override
    public void update(User user) {
        em.merge(user);
    }

    @Override
    public User findByName(String name) {
        TypedQuery<User> query = em.createNamedQuery("user.findByName", User.class);
        query.setParameter("name", name);
        List<User> result = query.getResultList();
        return result.get(0);
    }
}
