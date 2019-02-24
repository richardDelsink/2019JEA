package dao;

import domain.Comment;
import domain.Step;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class MessageDaoCollection implements MessageDao {

    CopyOnWriteArrayList<Comment> comments = new CopyOnWriteArrayList<>();

    @Override
    public void remove(Comment comment) {
        comments.remove(comment);
    }

    @Override
    public Comment findByName(String name) {
        for (Comment comment : comments) {
            if (comment.getComment().contentEquals(name)) {
                return comment;
            }
        }
        return null;
    }

    @Override
    public void update(Comment comment) {
        for (Comment c : comments) {
            if (c.getCommentId()==(comment.getCommentId())) {
                comments.set(comments.indexOf(comment),comment);
            }
        }
    }

    @Override
    public ArrayList<Comment> getComments(User user) {
        ArrayList<Comment> c = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getUserId()== user.getId()) {
                c.add(comment);
            }
        }
        return c;
    }

    @Override
    public void add(Comment comment) {
        comments.add(comment);
    }

    @Override
    public ArrayList<Comment> getCommentsByStep(Step step) {
        ArrayList<Comment> commentList = new ArrayList<>();
        for (Comment comment : comments) {
            if (comment.getStepId() == step.getStepId()) {
                commentList.add(comment);
            }
        }
        return commentList;

    }
}
