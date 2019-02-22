package dao;

import domain.Comment;
import domain.User;

import java.util.ArrayList;

public interface MessageDao {
    ArrayList<Comment> getComments(User user);
}
