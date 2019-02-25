package dao;

import domain.Journey;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class JourneyDaoCollection implements JourneyDao{

    CopyOnWriteArrayList<Journey> journeys = new CopyOnWriteArrayList<>();

    @Override
    public void add(Journey journey) {
        journeys.add(journey);
    }

    @Override
    public void remove(Journey journey) {
        journeys.remove(journey);
    }

    @Override
    public Journey findByName(String name) {
        for (Journey journey : journeys) {
            if (journey.getJourneyName().contentEquals(name)) {
                return journey;
            }
        }
        return null;
    }

    @Override
    public void update(Journey journey) {
        for (Journey j : journeys) {
            if (j.getJourneyId()==(journey.getJourneyId())) {
                journeys.set(journeys.indexOf(journey),journey);
            }
        }
    }

    @Override
    public List<Journey> getJourneyByUser(User user) {
        List<Journey> journeyss = new ArrayList<>();
        for (Journey j : journeys) {
            if (j.getUserId()==user.getId()) {
                journeyss.add(j);
            }
        }
        return journeyss;
    }

    public int Count(){
        return journeys.size();
    }
    public JourneyDaoCollection() {
    }
}
