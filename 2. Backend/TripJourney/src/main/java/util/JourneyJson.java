package util;
import domain.Journey;

import javax.xml.bind.annotation.XmlElement;
import java.time.LocalDateTime;
import java.util.Date;

public class JourneyJson {

        @XmlElement public String journeyName;
        @XmlElement public String journeySummary;
        @XmlElement public Long startDate;
        @XmlElement public Long date;
        @XmlElement public String friends;
        @XmlElement public int userId;

        public JourneyJson(String journeyName, String journeySummary, Long startDate, long date, String friends, int userId) {
                this.journeyName = journeyName;
                this.journeySummary = journeySummary;
                this.startDate = startDate;
                this.date = date;
                this.friends = friends;
                this.userId = userId;
        }

}
