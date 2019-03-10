package dao;

import domain.Message;
import domain.Step;
import domain.User;

import java.util.List;

public interface MessageDao extends GenericInterface<Message> {
    List<Message> getComments(User user);
    List<Message> getCommentsByStep(Step step);
}
