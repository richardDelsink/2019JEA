package service;


import dao.JPA;
import dao.StepDao;
import domain.Journey;
import domain.Step;
import domain.User;
import event.StepEvent;

import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.NotFoundException;
import java.util.List;

@Stateless
public class StepService {

    @Inject @JPA
    private StepDao stepDao;

    /*@Inject
    private Event<StepEvent> stepEvent;*/

    public void addStep(Step step){
        stepDao.add(step);
    }

    public void removeStep(Step step){
        stepDao.remove(step);
    }

    public void removeStep(String name) {
        Step step = findByName(name);
        stepDao.remove(step);
    }

    public Step findByName(String name){
        return stepDao.findByName(name);
    }

    public List<Step> findByJourney(Journey journey) {
        return stepDao.getStepByJourney(journey);
    }

    public Step likeStep(User user, int stepId)  {
        Step s = stepDao.findStepById(stepId);
        if (s == null) {
            throw new NotFoundException("Step does not exist");
        }
        return stepDao.likeStep(s, user);
    }

    public Step unlikeStep(User user, int stepId) throws NotFoundException {
        Step s = stepDao.findStepById(stepId);

        if (s == null) {
            throw new NotFoundException("Step does not exist");
        }

        if (!s.getLike().contains(user)) {
            throw new NotFoundException("User didn't like this step");
        }

        return stepDao.unlikeStep(s, user);
    }

    /*public void addStepEvent(Step step) {

        stepEvent.fire(new StepEvent(step));
    }*/
}
