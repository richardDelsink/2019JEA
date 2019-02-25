package dao;

import domain.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class UserDaoCollection implements UserDao{

    CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(User user) {
        users.remove(user);
    }

    @Override
    public User findByName(String name) {
        for (User user : users) {
            if (user.getName().contentEquals(name)) {
                return user;
            }
        }
        return null;
    }

    @Override
    public void update(User user) {
        for (User u : users) {
            if (u.getId()==(user.getId())) {
                users.set(users.indexOf(user),user);
            }
        }
    }

    @Override
    public List<User> getFollowers(User user) {
        return user.getFollowing();
    }

    @Override
    public void followUser(User user, User toFollow) {
        user.followUser(toFollow);
    }

    @Override
    public void unfollowUser(User user, User toUnfollow) {
        user.unfollowUser(toUnfollow);
    }

    @Override
    public boolean login(String username, String password) {
        for (User u : users) {
            if (u.getName() == username && u.getPassword() == password) {
                return true;
            }
        }
        return false;
    }
    public int Count(){
        return users.size();
    }

    @PostConstruct
    private void init(){
        // System.out.println("StudentDaoColl");
    }
    public UserDaoCollection(){
    }
}
