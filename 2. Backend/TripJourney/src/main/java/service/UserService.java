package service;

import dao.GenericInterface;
import dao.JPA;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;

@Stateless
public class UserService {

    @Inject
    private GenericInterface<User> genericDao;

    public void addUser(User user) {
        genericDao.add(user);
    }

    public void removeUser(User user){
        genericDao.remove(user);
    }

    public void removeUser(String name) {
        User user = findByName(name);
        genericDao.remove(user);
    }

    public User findByName(String name){
        return genericDao.findByName(name);
    }
    public UserService(){

    }

}
