package dao;

import domain.Journey;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class JourneyDaoCollection implements GenericInterface<Journey>{

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

    public JourneyDaoCollection() {
    }
}
