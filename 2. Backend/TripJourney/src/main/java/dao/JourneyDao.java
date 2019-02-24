package dao;

import domain.Journey;
import domain.User;

import java.util.List;

public interface JourneyDao extends GenericInterface<Journey> {

    List<Journey> getJourneyByUser(User user);
}
