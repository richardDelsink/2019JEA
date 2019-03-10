package dao;

import domain.Message;
import domain.Step;
import domain.User;
import interceptor.MessageInterceptor;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;


@Stateless @JPA
public class MessageJpa implements MessageDao{

    @PersistenceContext(unitName = "t")
    private EntityManager em;

    public MessageJpa() {
    }

    @Override
    public List<Message> getComments(User user) {
        TypedQuery<Message> query = em.createNamedQuery("comment.getCommentsByUser", Message.class);
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    @Override
    public List<Message> getCommentsByStep(Step step) {
        TypedQuery<Message> query = em.createNamedQuery("comment.getCommentsByStep", Message.class);
        query.setParameter("stepId", step.getStepId());
        return query.getResultList();
    }

    @Override
    @Interceptors(MessageInterceptor.class)
    public void add(Message comment) {
        em.persist(comment);
    }

    @Override
    public void remove(Message comment) {
        em.remove(em.merge(comment));
    }

    @Override
    public void update(Message comment) {
        em.merge(comment);
    }

    @Override
    public Message findByName(String name) {
        TypedQuery<Message> query = em.createNamedQuery("comment.findByName", Message.class);
        query.setParameter("name", name);
        List<Message> result = query.getResultList();
        return result.get(0);
    }

    public void setEm(EntityManager em) {
        this.em = em;
    }
}
