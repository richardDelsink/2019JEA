package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "Step")

@NamedQueries({
        @NamedQuery(name = "step.getStepsByJourney", query = "SELECT s FROM Step s WHERE s.jouney = :journey"),
        @NamedQuery(name = "step.findByName", query = "SELECT s FROM Step s WHERE s.name = :name")})

@XmlRootElement
public class Step implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int stepId;
    private Journey journey;
    private String location;
    private String stepName;
    private Date arrivalTime;
    private String story;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> like;
    private Date postDate;

    public Step(Journey journey , String location, String stepName, Date arrivalTime, String story) {
        this.journey = journey;
        this.location = location;
        this.stepName = stepName;
        this.arrivalTime = arrivalTime;
        this.story = story;
        this.like = new ArrayList<>();
        this.postDate = new Date();
    }

    public int getStepId() {
        return stepId;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
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

    public Date getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(Date arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public String getStory() {
        return story;
    }

    public void setStory(String story) {
        this.story = story;
    }

    public List<User> getLike() {
        return like;
    }

    public void setLike(List<User> like) {
        this.like = like;
    }

    public void addLike(User user) throws IllegalArgumentException {
        if (this.like.contains(user)) {
            throw new IllegalArgumentException("User already liked this step");
        }

        this.like.add(user);
    }

    public void removeLike(User user) throws IllegalArgumentException {
        if (!this.like.contains(user)) {
            throw new IllegalArgumentException("User didn't like this step");
        }
        this.like.remove(user);
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
