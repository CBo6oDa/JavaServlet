package builders;

import models.Flight;
import validators.ValidatorServiceImpl;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.atomic.AtomicLong;

public class FlightBuilder {

    private static AtomicLong idCounter = new AtomicLong(1);

    public int id;
    public String companyName;
    public LocalDate dateOfFlight;
    public int timeInFlight;
    public LocalTime timeTakeOff;
    public double price;
    public Flight.City from;
    public Flight.City to;

    private static ValidatorServiceImpl validator;

    public FlightBuilder() { validator = new ValidatorServiceImpl(); }

    public int getId() {
        return id;
    }

    public FlightBuilder setIdentification(int id){
        this.id = id;
        return  this;
    }
    public FlightBuilder setId() {
        this.id = (int) idCounter.getAndIncrement();
        return this;
    }
    public FlightBuilder setCompanyName(String companyName) {
        this.companyName = companyName;
        return this;
    }
    public FlightBuilder setDateOfFlight(LocalDate dateOfFlight) {
        this.dateOfFlight = dateOfFlight;
        return this;
    }
    public FlightBuilder setTimeInFlight(int timeInFlight) {
        this.timeInFlight = timeInFlight;
        return this;
    }
    public FlightBuilder setTimeTakeOff(LocalTime timeTakeOff) {
        this.timeTakeOff = timeTakeOff;
        return this;
    }
    public FlightBuilder setPrice(double price) {
        this.price = price;
        return this;
    }
    public FlightBuilder setFrom(Flight.City from) {
        this.from = from;
        return this;
    }
    public FlightBuilder setTo(Flight.City to) {
        this.to = to;
        return this;
    }

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
    public Flight.City getFrom() {
        return from;
    }
    public Flight.City getTo() {
        return to;
    }

    public Flight build(){

        StringBuilder sb = new StringBuilder();
        Flight obj = new Flight(this);
        if(!validator.isValidId(this.id)){
            sb.append("\nIncorrect id\n");
        }
        if(!validator.isValidName(this.companyName)){
            sb.append("\nIncorrect name\n");
        }
        if(!validator.isValidPrice(this.price)){
            sb.append("Incorrect price\n");
        }
        if(!validator.isValidTimeInFlight(this.timeInFlight)){
            sb.append("Incorrect timeInFlight\n");
        }
        if(sb.length() == 0)
            return obj;
        else
            throw new IllegalArgumentException(sb.toString());
    }
}


