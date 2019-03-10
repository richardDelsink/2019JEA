package dao;

import domain.Message;
import domain.Step;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class MessageDaoCollection implements MessageDao {

    CopyOnWriteArrayList<Message> comments = new CopyOnWriteArrayList<>();

    @Override
    public void remove(Message comment) {
        comments.remove(comment);
    }

    @Override
    public Message findByName(String name) {
        for (Message comment : comments) {
            if (comment.getComment().contentEquals(name)) {
                return comment;
            }
        }
        return null;
    }

    @Override
    public void update(Message comment) {
        for (Message c : comments) {
            if (c.getCommentId()==(comment.getCommentId())) {
                comments.set(comments.indexOf(comment),comment);
            }
        }
    }

    @Override
    public ArrayList<Message> getComments(User user) {
        ArrayList<Message> c = new ArrayList<>();
        for (Message comment : comments) {
            if (comment.getUserId()== user.getId()) {
                c.add(comment);
            }
        }
        return c;
    }



    @Override
    public void add(Message comment) {
        comments.add(comment);
    }

    @Override
    public ArrayList<Message> getCommentsByStep(Step step) {
        ArrayList<Message> commentList = new ArrayList<>();
        for (Message comment : comments) {
            if (comment.getStepId() == step.getStepId()) {
                commentList.add(comment);
            }
        }
        return commentList;

    }
}
