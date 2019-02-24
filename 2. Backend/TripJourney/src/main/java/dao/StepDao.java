package dao;

import domain.Journey;
import domain.Step;
import domain.User;

import java.util.List;

public interface StepDao extends GenericInterface<Step>{

    List<Step> getStepByJourney(Journey journey);
    Step likeStep(Step s, User u);
    Step unlikeStep(Step s, User u);
    Step findStepById(int id);
}
