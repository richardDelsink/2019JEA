package util;
import domain.Journey;

import javax.xml.bind.annotation.XmlElement;

public class JourneyJson {

        @XmlElement public int id;
        @XmlElement public String journeyName;
        @XmlElement public String journeySummary;
        @XmlElement public Long startdate;
        @XmlElement public Long endate;
        @XmlElement public String whoCanSee;
        @XmlElement public int userId;
}
