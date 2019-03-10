package domain;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Entity
@NamedQueries({
        @NamedQuery(name = "journey.getJourneyByUser", query = "SELECT j FROM Journey j WHERE j.userId = :userId"),
        @NamedQuery(name = "journey.findByName", query = "SELECT j FROM Journey j WHERE j.journeyName = :name")
       })
@XmlRootElement
public class Journey implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int  journeyId;
    private String journeyName;
    private String journeySummary;
    @Temporal(TemporalType.DATE)
    private Date startDate;
    @Temporal(TemporalType.DATE)
    private Date endDate;
    private String whoCanSee;
    private int userId;

    public Journey(String journeyName, String journeySummary, Date startDate, Date endDate, String whoCanSee, int userId) {
        this.journeyName = journeyName;
        this.journeySummary = journeySummary;
        this.startDate = startDate;
        this.endDate = endDate;
        this.whoCanSee = whoCanSee;
        this.userId = userId;
    }

    public Journey() {
    }

    public int getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(int journeyId) {
        this.journeyId = journeyId;
    }

    public String getJourneyName() {
        return journeyName;
    }

    public void setJourneyName(String journeyName) {
        this.journeyName = journeyName;
    }

    public String getJourneySummary() {
        return journeySummary;
    }

    public void setJourneySummary(String journeySummary) {
        this.journeySummary = journeySummary;
    }

    public Date getStartDatel() {
        return startDate;
    }

    public void setStartDatel(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getWhoCanSee() {
        return whoCanSee;
    }

    public void setWhoCanSee(String whoCanSee) {
        this.whoCanSee = whoCanSee;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
