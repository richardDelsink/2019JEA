package domain;

import javax.persistence.*;

@Entity
@Table(name = "User")
@NamedQueries({@NamedQuery(name = "",query = "")})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name="photo")
    private String photo;
    private String firstname;
    private String lastname;
    private String city;
    private String about;

    private String email;
    private String personalLink;
    private Boolean privacy;
    private String distance;
    private String temperature;

    private String password;

    public User(int id, String photo, String firstname, String lastname, String city, String about, String email, String personalLink, Boolean privacy, String distance, String temperature, String password) {
        this.id = id;
        this.photo = photo;
        this.firstname = firstname;
        this.lastname = lastname;
        this.city = city;
        this.about = about;
        this.email = email;
        this.personalLink = personalLink;
        this.privacy = privacy;
        this.distance = distance;
        this.temperature = temperature;
        this.password = password;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
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
}


