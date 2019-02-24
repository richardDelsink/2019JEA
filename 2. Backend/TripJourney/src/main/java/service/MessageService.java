package service;

import dao.JPA;
import dao.MessageDao;
import domain.Comment;
import domain.Step;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class MessageService {

    @Inject @JPA
    private MessageDao commentDao;

    public void addComment(Comment comment){

        commentDao.add(comment);
    }

    public void removeComment(Comment comment){

        commentDao.remove(comment);
    }

    public void removeComment(String name) {
        Comment comment = commentDao.findByName(name);
        commentDao.remove(comment);
    }

    public Comment findByName(String name){

        return commentDao.findByName(name);
    }

    public List<Comment> findByStep(Step step) {

        return commentDao.getCommentsByStep(step);
    }


}
