package providers;

import models.Airport;
import models.Flight;

import java.util.ArrayList;

public interface AirportProvider {

    void addAirport(Airport airport);
    void editAirport(Airport airport);
    void deleteAirport(int idAirport);

    void addFlight(int idAirport, Flight flight);
    void editFlight(int idAirport, Flight flight);
    void deleteFlight(int idAirport, int idFlight);

    Airport getAirport(int idAirport);
    ArrayList<Airport> getAirportList();
    void setAirportList(ArrayList<Airport> airportList);
}

