package util;

import javax.xml.bind.annotation.XmlElement;
import java.util.Date;

public class JourneyJson {

        @XmlElement public String journeyName;
        @XmlElement public String journeySummary;
        @XmlElement public Long startDate;
        @XmlElement public Long date;
        @XmlElement public String friends;
        @XmlElement public int userId;

        public JourneyJson(String journeyName, String journeySummary, Long startDate, Long date, String friends, int userId) {
                this.journeyName = journeyName;
                this.journeySummary = journeySummary;
                this.startDate = startDate;
                this.date = date;
                this.friends = friends;
                this.userId = userId;
        }
}
