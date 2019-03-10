package dao;

import domain.Journey;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless @JPA
public class JourneyDaoJpa implements JourneyDao{

    @PersistenceContext(unitName = "t")
    private EntityManager em;

    public JourneyDaoJpa() {
    }

    @Override
    public List<Journey> getJourneyByUser(User user) {
        TypedQuery<Journey> query = em.createNamedQuery("journey.getJourneyByUser", Journey.class);
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    @Override
    public void add(Journey journey) {
        em.persist(journey);
    }

    @Override
    public void remove(Journey journey) {
        em.remove(em.merge(journey));
    }

    @Override
    public void update(Journey journey) {
        em.merge(journey);
    }

    @Override
    public Journey findByName(String name) {
        TypedQuery<Journey> query = em.createNamedQuery("journey.findByName", Journey.class);
        query.setParameter("name", name);
        List<Journey> result = query.getResultList();
        return result.get(0);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
