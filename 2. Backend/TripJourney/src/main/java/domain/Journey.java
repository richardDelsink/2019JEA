package domain;

import java.time.LocalDate;

public class Journey {

    private int  journeyId;
    private String journeyName;
    private String journeySummary;
    private LocalDate startDatel;
    private LocalDate endDate;
    private String whoCanSee;

    public Journey(int journeyId, String journeyName, String journeySummary, LocalDate startDatel, LocalDate endDate, String whoCanSee) {
        this.journeyId = journeyId;
        this.journeyName = journeyName;
        this.journeySummary = journeySummary;
        this.startDatel = startDatel;
        this.endDate = endDate;
        this.whoCanSee = whoCanSee;
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

    public LocalDate getStartDatel() {
        return startDatel;
    }

    public void setStartDatel(LocalDate startDatel) {
        this.startDatel = startDatel;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public String getWhoCanSee() {
        return whoCanSee;
    }

    public void setWhoCanSee(String whoCanSee) {
        this.whoCanSee = whoCanSee;
    }
}
