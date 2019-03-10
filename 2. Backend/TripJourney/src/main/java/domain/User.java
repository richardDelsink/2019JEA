package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@NamedQueries({
        @NamedQuery(name = "user.getFollowing", query = "SELECT u FROM User u WHERE :user MEMBER OF u.following"),
        @NamedQuery(name = "user.getByName", query = "SELECT u FROM User u WHERE u.name = :username"),
        @NamedQuery(name = "user.getLogin", query = "SELECT u FROM User u WHERE u.name = :username AND u.password = :password")})
@XmlRootElement
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String photo;
    @Column(nullable = false, unique = true)
    private String name;
    private String city;
    private String about;

    private String email;
    private String personalLink;
    private Boolean privacy;
    private String distance;
    private String temperature;
    @OneToMany(fetch = FetchType.LAZY)
    private List<User> following;
    private String password;

    public User(){}
    public User(String photo, String name, String city, String about, String email, String personalLink, Boolean privacy, String distance, String temperature, String password) {
        this.photo = photo;
        this.name = name;
        this.city = city;
        this.about = about;
        this.email = email;
        this.personalLink = personalLink;
        this.privacy = privacy;
        this.distance = distance;
        this.temperature = temperature;
        this.password = password;
        this.following = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPersonalLink() {
        return personalLink;
    }

    public void setPersonalLink(String personalLink) {
        this.personalLink = personalLink;
    }

    public Boolean getPrivacy() {
        return privacy;
    }

    public void setPrivacy(Boolean privacy) {
        this.privacy = privacy;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<User> getFollowing() {
        return following;
    }

    public void setFollowing(List<User> following) {
        this.following = following;
    }
    public void followUser(User user) throws IllegalArgumentException {
        if (this.following.contains(user)) {
            throw new IllegalArgumentException("User already follows this user");
        }

        this.following.add(user);
    }

    public void unfollowUser(User user) throws IllegalArgumentException {
        if (!this.following.contains(user)) {
            throw new IllegalArgumentException("User didn't follow this user");
        }
        this.following.remove(user);
    }
    
}


