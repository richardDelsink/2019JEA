package service;

import dao.JPA;
import dao.JourneyDao;
import domain.Journey;
import domain.User;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class JourneyService {

    @Inject @JPA
    private JourneyDao journeyDao;

    public void addJourney(Journey journey){
        journeyDao.add(journey);
    }

    public void removeJourney(Journey journey){
        journeyDao.remove(journey);
    }

    public void removeJourney(String name) {
        Journey journey = journeyDao.findByName(name);
        journeyDao.remove(journey);
    }

    public Journey findByName(String name){
        return journeyDao.findByName(name);
    }

    public List<Journey> findByUsername(User user) {
        return journeyDao.getJourneyByUser(user);
    }
}
