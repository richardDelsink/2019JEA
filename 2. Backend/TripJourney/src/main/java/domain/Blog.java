package domain;

import java.time.LocalDateTime;
import java.util.List;

public class Blog {

    private int blogId;
    private String location;
    private String stepName;
    private LocalDateTime arrivalTime;
    private String story;
    private List<String> photo;
    private List<String> comment;
    private int like;

    public Blog(int blogId, String location, String stepName, LocalDateTime arrivalTime, String story, List<String> photo, List<String> comment, int like) {
        this.blogId = blogId;
        this.location = location;
        this.stepName = stepName;
        this.arrivalTime = arrivalTime;
        this.story = story;
        this.photo = photo;
        this.comment = comment;
        this.like = like;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
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

    public List<String> getPhoto() {
        return photo;
    }

    public void setPhoto(List<String> photo) {
        this.photo = photo;
    }

    public List<String> getComment() {
        return comment;
    }

    public void setComment(List<String> comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}
