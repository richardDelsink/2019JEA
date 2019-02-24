package dao;

import domain.Comment;
import domain.Step;
import domain.User;

import java.util.List;

public interface MessageDao extends GenericInterface<Comment> {
    List<Comment> getComments(User user);
    List<Comment> getCommentsByStep(Step step);
}
