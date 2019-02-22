package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Step {

    private int stepId;
    private String location;
    private String stepName;
    private LocalDateTime arrivalTime;
    private String story;
    private int like;

    public Step(int stepId, String location, String stepName, LocalDateTime arrivalTime, String story, int like) {
        this.stepId = stepId;
        this.location = location;
        this.stepName = stepName;
        this.arrivalTime = arrivalTime;
        this.story = story;
        this.like = like;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getStepName() {
        return stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
