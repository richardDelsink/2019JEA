package dao;

import domain.Journey;
import domain.Step;
import domain.User;
import event.StepEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Observes;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Stateless
@JPA
public class StepJpa implements StepDao{

    @PersistenceContext(unitName = "t")
    private EntityManager em;

    public StepJpa() {
    }

    @Override
    public List<Step> getStepByJourney(Journey journey) {
        TypedQuery<Step> query = em.createNamedQuery("step.getStepsByJourney", Step.class);
        query.setParameter("journey", journey);
        return query.getResultList();
    }

    @Override
    public Step likeStep(Step s, User u) {
        s.addLike(u);
        em.merge(s);
        return s;
    }

    @Override
    public Step unlikeStep(Step s, User u) {
        s.removeLike(u);
        em.merge(s);
        return s;
    }

    @Override
    public Step findStepById(int id) {

        return em.find(Step.class, id);
    }

    @Override
    public void add(Step step) {
        em.persist(step);
    }

    @Override
    public void remove(Step step) {
        em.remove(em.merge(step));
    }

    @Override
    public void update(Step step) {
        em.merge(step);
    }

    @Override
    public Step findByName(String name) {
        TypedQuery<Step> query = em.createNamedQuery("step.findByName", Step.class);
        query.setParameter("name", name);
        List<Step> result = query.getResultList();
        return result.get(0);
    }

    public void addStepEvent(@Observes StepEvent event) {
        Step step = event.getStep();
        em.persist(step);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
