package dao;

import domain.Journey;
import domain.Step;
import domain.User;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class StepDaoCollection implements StepDao {

    CopyOnWriteArrayList<Step> steps = new CopyOnWriteArrayList<>();

    @Override
    public void remove(Step step) {
        steps.remove(step);
    }

    @Override
    public Step findByName(String name) {
        for (Step step : steps) {
            if (step.getStepName().contentEquals(name)) {
                return step;
            }
        }
        return null;
    }

    @Override
    public void add(Step step) {
        steps.add(step);
    }

    @Override
    public void update(Step step) {
        for (Step s : steps) {
            if (s.getStepId()==(step.getStepId())) {
                steps.set(steps.indexOf(step),step);
            }
        }
    }

    @Override
    public List<Step> getStepByJourney(Journey journey) {
        List<Step> s = new ArrayList<>();
        for (Step step : steps) {
            if (step.getJourney().equals(journey)) {
                s.add(step);
            }
        }
        return s;
    }

    @Override
    public Step likeStep(Step s, User u) {
        s.addLike(u);
        return s;
    }

    @Override
    public Step unlikeStep(Step s, User u) {
        s.removeLike(u);
        return s;
    }

    @Override
    public Step findStepById(int id) {
        for (Step step : steps) {
            if (step.getStepId() == id) {
                return step;
            }
        }
        return null;
    }

    public int Count(){
        return steps.size();
    }

    public StepDaoCollection() {
    }
}
