package event;

import domain.Step;

public class StepEvent {
    private Step step;

    public StepEvent(Step step) {
        this.step = step;
    }

    public Step getStep() {
        return step;
    }

    public void setStep(Step step) {
        this.step = step;
    }
}
