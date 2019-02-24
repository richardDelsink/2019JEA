package dao;

import domain.Comment;
import domain.Step;
import domain.User;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@JPA
@Stateless
public class MessageJpa implements MessageDao{

    @PersistenceContext(unitName = "commentPU")
    private EntityManager em;

    @Override
    public List<Comment> getComments(User user) {
        TypedQuery<Comment> query = em.createNamedQuery("comment.getCommentsByUser", Comment.class);
        query.setParameter("userId", user.getId());
        return query.getResultList();
    }

    @Override
    public List<Comment> getCommentsByStep(Step step) {
        TypedQuery<Comment> query = em.createNamedQuery("comment.getCommentsByStep", Comment.class);
        query.setParameter("stepId", step.getStepId());
        return query.getResultList();
    }

    @Override
    public void add(Comment comment) {
        em.persist(comment);
    }

    @Override
    public void remove(Comment comment) {
        em.remove(em.merge(comment));
    }

    @Override
    public void update(Comment comment) {
        em.merge(comment);
    }

    @Override
    public Comment findByName(String name) {
        TypedQuery<Comment> query = em.createNamedQuery("comment.findByName", Comment.class);
        query.setParameter("name", name);
        List<Comment> result = query.getResultList();
        return result.get(0);
    }
}
