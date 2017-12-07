package builders;

import models.Airport;
import models.Flight;
import validators.ValidatorServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class AirportBuilder {

    private static AtomicLong idAirport = new AtomicLong(1);

    private int id;
    private String name;
    private List<Flight> flights;

    private static ValidatorServiceImpl validator;

    public AirportBuilder() {
        flights = new ArrayList<>();
        validator = new ValidatorServiceImpl();
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public List<Flight> getFlights() {
        return flights;
    }

    public AirportBuilder setId() {
        this.id = (int) idAirport.getAndIncrement();
        return this;
    }
    public AirportBuilder setIdentification(int id){
        this.id = id;
        return this;
    }
    public AirportBuilder setName(String name) {
        this.name = name;
        return this;
    }
    public AirportBuilder setFlights(ArrayList<Flight> flights) {
        this.flights = flights;
        return this;
    }

    public Airport build(){

        StringBuilder sb = new StringBuilder();
        Airport obj = new Airport(this);
        if(!validator.isValidName(this.name)){
            sb.append("\nIncorrect name\n");
        }
        if(sb.length() == 0)
            return obj;
        else
            throw new IllegalArgumentException(sb.toString());
    }
}

