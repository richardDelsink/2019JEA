package dao;

import domain.Comment;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class MessageDaoCollection implements GenericInterface<Comment>, MessageDao {

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
            if (c.getMessageId()==(comment.getMessageId())) {
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
}
