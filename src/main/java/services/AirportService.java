//package services;
//
//import models.Airport;
//import models.Flight;
//
//import java.time.LocalTime;
//import java.util.ArrayList;
//import java.util.List;
//
//public interface AirportService {
//
//    void addAirport(Airport airport);
//    void editAirport(Airport airport);
//    void deleteAirport(int idAirport);
//
//    void addFlight(int idAirport, Flight flight);
//    void editFlight(int idAirport, Flight flight);
//    void deleteFlight(int idAirport, int idFlight);
//
//    LocalTime getTimeOfLanding(Flight obj);
//    ArrayList<Flight> getFlightFrom(int idAirport, Flight.City from);
//    ArrayList<Flight> getFlightTo(int idAirport, Flight.City to);
//    ArrayList<Flight> searchPath(int idAirport, Flight.City from, Flight.City to, int time);
//
//    List<Airport> getAirportList();
//
//    Airport getAirport(int idAirport);
//}
package services;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportService {

    void addAirport(Airport airport);
    void editAirport(Airport airport);
    void deleteAirport(int idAirport);

    void addFlight(int idAirport, Flight flight);
    void editFlight(int idAirport, Flight flight);
    void deleteFlight(int idAirport, int idFlight);
    ArrayList<Airport> getAirportList();
    Airport getAirport(int idAirport);
}

