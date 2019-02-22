package dao;

import domain.Step;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import java.util.concurrent.CopyOnWriteArrayList;

@Stateless
@Default
public class StepDaoCollection implements GenericInterface<Step> {

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

    public StepDaoCollection() {
    }
}
