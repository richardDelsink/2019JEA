package domain;

import com.fasterxml.jackson.annotation.JsonFormat;

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
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd@HH:mm:ss.SSSZ", timezone="CET")
    private Date startDate;
    @Temporal(TemporalType.DATE)
    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd@HH:mm:ss.SSSZ", timezone="CET")
    private Date endDate;
    private String whoCanSee;
    private int userId;

    public Journey(String journeyName, String journeySummary, String whoCanSee, int userId) {
        this.journeyName = journeyName;
        this.journeySummary = journeySummary;
        this.whoCanSee = whoCanSee;
        this.userId = userId;
    }

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

   /* public int getJourneyId() {
        return journeyId;
    }*/

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


}
