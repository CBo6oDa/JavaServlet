package models;

import builders.FlightBuilder;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import org.codehaus.jackson.map.annotate.JsonDeserialize;
import org.codehaus.jackson.map.annotate.JsonSerialize;
import serialisation.dataTimeSerializer.JSON.JsonLocalDateDeserializer;
import serialisation.dataTimeSerializer.JSON.JsonLocalDateSerializer;
import serialisation.dataTimeSerializer.JSON.JsonLocalTimeDeserializer;
import serialisation.dataTimeSerializer.JSON.JsonLocalTimeSerializer;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlRootElement;
import java.time.LocalDate;
import java.time.LocalTime;

@XmlRootElement
public class Flight {
    @XmlEnum
    public enum City {
        KIEV, LVIV, KHARKIV, LONDON, BERLIN, WARSAW, LISSABON, PARIS, CHISINAU, MINSK, MADRID, TOKIO, PEKIN,
        WASHINGTON, OTTAWA, CHICAGO
    }
    @XStreamAlias("id")
    private  int id;
    @XStreamAlias("companyName")
    private String companyName;
    @JsonSerialize(using = JsonLocalDateSerializer.class)
    @JsonDeserialize(using = JsonLocalDateDeserializer.class)
    @XStreamAlias("dateOfFlight")
    private LocalDate dateOfFlight;
    @XStreamAlias("timeInFlight")
    private int timeInFlight;
    @JsonSerialize(using = JsonLocalTimeSerializer.class)
    @JsonDeserialize(using = JsonLocalTimeDeserializer.class)
    @XStreamAlias("timeTakeOff")
    private LocalTime timeTakeOff;
    @XStreamAlias("price")
    private double price;
    @XStreamAlias("from")
    private City from;
    @XStreamAlias("to")
    private City to;

    public Flight(){super();}
    public Flight(FlightBuilder builder) {
        this.id = builder.getId();
        this.companyName = builder.getCompanyName();
        this.dateOfFlight = builder.getDateOfFlight();
        this.timeInFlight = builder.getTimeInFlight();
        this.timeTakeOff = builder.getTimeTakeOff();
        this.price = builder.getPrice();
        this.from = builder.getFrom();
        this.to = builder.getTo();
    }

    public void setId(int id){this.id = id;}
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public void setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
    }
    public void setTimeInFlight(int timeInFlight) {
        this.timeInFlight = timeInFlight;
    }
    public void setTimeTakeOff(LocalTime timeTakeOff) {
        this.timeTakeOff = timeTakeOff;
    }
    public void setPrice(double price) {
        this.price= price;
    }
    public void setFrom(City from) {
        this.from = from;
    }
    public void setTo(City to) { this.to = to; }

    public String getCompanyName() {
        return companyName;
    }
    public LocalDate getDateOfFlight() {
        return dateOfFlight;
    }
    public int getTimeInFlight() {
        return timeInFlight;
    }
    public LocalTime getTimeTakeOff() {
        return timeTakeOff;
    }
    public double getPrice() {
        return price;
    }
    public City getFrom() {
        return from;
    }
    public City getTo() {
        return to;
    }
    public int getId() {
        return id;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Flight flight = (Flight) o;

        if (id != flight.id) return false;
        if (timeInFlight != flight.timeInFlight) return false;
        if (Double.compare(flight.price, price) != 0) return false;
        if (companyName != null ? !companyName.equals(flight.companyName) : flight.companyName != null) return false;
        if (dateOfFlight != null ? !dateOfFlight.equals(flight.dateOfFlight) : flight.dateOfFlight != null)
            return false;
        if (timeTakeOff != null ? !timeTakeOff.equals(flight.timeTakeOff) : flight.timeTakeOff != null) return false;
        if (from != flight.from) return false;
        return to == flight.to;
    }
    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (companyName != null ? companyName.hashCode() : 0);
        result = 31 * result + (dateOfFlight != null ? dateOfFlight.hashCode() : 0);
        result = 31 * result + timeInFlight;
        result = 31 * result + (timeTakeOff != null ? timeTakeOff.hashCode() : 0);
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (from != null ? from.hashCode() : 0);
        result = 31 * result + (to != null ? to.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "\nFlight{" +
                "id : " + id +
                ",company : " + companyName  +
                ",date : " + dateOfFlight +
                ",timeIn : " + timeInFlight +
                ",timeTakeOff : " + timeTakeOff +
                ",price : " + price +
                ",from : " + from +
                ",to : " + to +
                "}\n";
    }
}

















