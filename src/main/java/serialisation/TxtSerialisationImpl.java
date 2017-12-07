package serialisation;
import builders.AirportBuilder;
import builders.FlightBuilder;
import models.Airport;
import models.Flight;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TxtSerialisationImpl<T> implements Serialisation<T> {
    private static final String AIRPORT_PATTERN = "Airport\\{id : (\\d{1,6}), name : (([A-Z][a-zA-Z]*\\s?)+), flights : \\[";
    private static final String FLIGHT_PATTERN = "\\[?(, )?Flight\\{id : (\\d{1,6}),company : (([A-Z][a-zA-Z]*\\s?)+),date : (\\d{4})-(\\d{2})-(\\d{2}),timeIn : (\\d{1,3}),timeTakeOff : (\\d{2}):(\\d{2}),price : ((\\d{1,6})(\\.0)?(\\.\\d{2})?),from : (([A-Z])+),to : (([A-Z])+)\\}?]?";
    public String s;

    @Override
    public void toFile(ArrayList<T> object, File file) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            ArrayList<Airport> airports = (ArrayList<Airport>)object;
            for(Airport airport : airports){
                writer.write(airport.toString());
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<T> fromFile(File file) throws IOException {
        BufferedReader in = new BufferedReader(new FileReader(file));
        Pattern patternAirport = Pattern.compile(AIRPORT_PATTERN);
        Pattern patternFlight = Pattern.compile(FLIGHT_PATTERN);
        Matcher flightMatcher;
        Matcher airportMatcher;

        Airport airport = new Airport();
        Flight flight = new Flight();
        ArrayList<Flight> flights = new ArrayList<Flight>();
        ArrayList<T> airportList = new ArrayList<T>();
        ArrayList<Flight> fl = new ArrayList<Flight>();
        String str;

        while((str = in.readLine())!= null){
            if(str.equals("------------------------------" +
                          "------------------------------" +
                          "------------------------------" +
                          "------------------------------")){

                fl = (ArrayList<Flight>) putList(flights);
                airport.setList(fl);
                airportList.add((T) new AirportBuilder().
                        setIdentification(airport.getId()).
                        setName(airport.getName()).
                        setFlights((ArrayList<Flight>) putList(fl)).
                        build());
                flights.clear();
                fl.clear();
                if((str = in.readLine())== null)
                  return airportList;
            }
            airportMatcher = patternAirport.matcher(str);
            if(airportMatcher.matches()){
                    airport.setId(Integer.parseInt(airportMatcher.group(1)));
                    airport.setName(airportMatcher.group(2));
            }

            flightMatcher = patternFlight.matcher(str);
            if(flightMatcher.matches()){
                flight = new Flight();
                flight.setId(Integer.parseInt(flightMatcher.group(2)));
                flight.setCompanyName(flightMatcher.group(3));
                flight.setDateOfFlight(LocalDate.of(
                        Integer.parseInt(flightMatcher.group(5)),
                        Integer.parseInt(flightMatcher.group(6)),
                        Integer.parseInt(flightMatcher.group(7))));
                flight.setTimeInFlight(Integer.parseInt(flightMatcher.group(8)));
                flight.setTimeTakeOff(LocalTime.of(
                        Integer.parseInt(flightMatcher.group(9)),
                        Integer.parseInt(flightMatcher.group(10))));
                flight.setPrice(Double.parseDouble(flightMatcher.group(11)));
                flight.setFrom(Flight.City.valueOf(flightMatcher.group(15)));
                flight.setTo(Flight.City.valueOf(flightMatcher.group(17)));
                flights.add(flight);
            }
        }
        in.close();
        return airportList;
    }

    private List<Flight> putList(ArrayList<Flight> fl) {
        ArrayList<Flight> flightArrayList = new ArrayList<Flight>();
        for (Flight f:fl) {
            flightArrayList.add(new FlightBuilder().setIdentification(f.getId()).
                    setCompanyName(f.getCompanyName()).
                    setDateOfFlight(f.getDateOfFlight()).
                    setTimeInFlight(f.getTimeInFlight()).
                    setTimeTakeOff(f.getTimeTakeOff()).
                    setFrom(f.getFrom()).
                    setPrice(f.getPrice()).
                    setTo(f.getTo()).build());
        }
        return flightArrayList;
    }
}
