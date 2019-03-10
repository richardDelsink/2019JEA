package service;

import dao.JPA;
import dao.MessageDao;
import domain.Message;
import domain.Step;
import interceptor.MessageInterceptor;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import java.util.List;

@Stateless
public class MessageService {

    @Inject @JPA
    private MessageDao commentDao;

    @Interceptors(MessageInterceptor.class)
    public void addComment(Message comment){
        commentDao.add(comment);
    }

    public void removeComment(Message comment){

        commentDao.remove(comment);
    }

    public void removeComment(String name) {
        Message comment = commentDao.findByName(name);
        commentDao.remove(comment);
    }

    public Message findByName(String name){

        return commentDao.findByName(name);
    }

    public List<Message> findByStep(Step step) {

        return commentDao.getCommentsByStep(step);
    }


}
