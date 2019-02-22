package dao;

import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class UserDaoCollection implements GenericInterface<User>{

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

    public UserDaoCollection(){

    }
}
