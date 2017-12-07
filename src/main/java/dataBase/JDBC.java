package dataBase;

import builders.AirportBuilder;
import builders.FlightBuilder;
import models.Airport;
import models.Flight;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class JDBC {

    private static Connection con = null;
    private static String username = "root";
    private static String password = "12345";
    private static String URL = "jdbc:mysql://localhost:3306/airports";

    public JDBC() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        con = DriverManager.getConnection(URL, username, password);
    }

    public static Connection getCon() {
        return con;
    }

    public static void setCon(Connection con) {
        JDBC.con = con;
    }

    public void addFlight(Flight fl, int airportId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO flight (fCompanyName,fDateOfFlight,fTimeInFlight,fTimeTakeOff,fPrice,fFrom,fTo,fk_aId) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)");
            stmt.setString(1, fl.getCompanyName());
            stmt.setDate(2, Date.valueOf(LocalDate.of(fl.getDateOfFlight().getYear(), fl.getDateOfFlight().getMonth(), fl.getDateOfFlight().getDayOfMonth())));
            stmt.setInt(3, fl.getTimeInFlight());
            stmt.setTime(4, Time.valueOf(LocalTime.of(fl.getTimeTakeOff().getHour(), fl.getTimeTakeOff().getMinute())));
            stmt.setDouble(5, fl.getPrice());
            stmt.setString(6, String.valueOf(fl.getFrom()));
            stmt.setString(7, String.valueOf(fl.getTo()));
            stmt.setInt(8, airportId);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void addAirport(Airport airport) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("INSERT INTO airport (aId,aName)"
                    + "VALUES (?,?)");

            stmt.setInt(1, airport.getId());
            stmt.setString(2, airport.getName());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Collection<Flight> getAllFlightsFromAirport(int idAirport) throws SQLException {
        Collection<Flight> flights = new ArrayList<Flight>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.prepareStatement("SELECT fId, fCompanyName, fDateOfFlight,fTimeInFlight, "
                    + "fTimeTakeOff, fPrice, fTo, fFrom FROM flight WHERE fk_aId =? "
                    + "ORDER BY fCompanyName");
            stmt.setInt(1, idAirport);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Flight fl = new FlightBuilder().
                        setIdentification(rs.getInt("fId")).
                        setCompanyName(rs.getString("fCompanyName")).
                        setDateOfFlight(rs.getDate("fDateOfFlight").toLocalDate()).
                        setTimeInFlight(rs.getInt("fTimeInFlight")).
                        setTimeTakeOff(rs.getTime("fTimeTakeOff").toLocalTime()).
                        setPrice(rs.getDouble("fPrice")).
                        setTo(Flight.City.valueOf(rs.getString("fTo"))).
                        setFrom(Flight.City.valueOf(rs.getString("fFrom"))).
                        build();
                flights.add(fl);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return flights;
    }

    public Airport getAirport(int idAirport) throws SQLException {
        Airport airport = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM airport WHERE aId =?");
            stmt.setInt(1, idAirport);
            rs = stmt.executeQuery();
            rs.next();
            return airport = new AirportBuilder().
                    setIdentification(rs.getInt("aId")).
                    setName(rs.getString("aName")).
                    build();
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public Flight getFlight(int idFlight) throws SQLException {
        Flight flight = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM flight WHERE fId =?");
            stmt.setInt(1, idFlight);
            rs = stmt.executeQuery();
            rs.next();
            return flight = new FlightBuilder().
                    setIdentification(rs.getInt("fId")).
                    setCompanyName(rs.getString("fCompanyName")).
                    setDateOfFlight(rs.getDate("fDateOfFlight").toLocalDate()).
                    setTimeInFlight(rs.getInt("fTimeInFlight")).
                    setTimeTakeOff(rs.getTime("fTimeTakeOff").toLocalTime()).
                    setPrice(rs.getDouble("fPrice")).
                    setTo(Flight.City.valueOf(rs.getString("fTo"))).
                    setFrom(Flight.City.valueOf(rs.getString("fFrom"))).
                    build();
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public void deleteFlight(int idFlight) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM flight WHERE fId=?");
            stmt.setInt(1,idFlight);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void deleteAirport(int idAirport) throws SQLException {
        PreparedStatement flightStmt = null;
        PreparedStatement airportStmt = null;
        try {
            flightStmt = con.prepareStatement("DELETE FROM flight WHERE fk_aId = ?");
            flightStmt.setInt(1,idAirport);
            flightStmt.execute();

            airportStmt = con.prepareStatement("DELETE FROM airport WHERE aId = ?");
            airportStmt.setInt(1,idAirport);
            airportStmt.execute();
        } finally {
            if (flightStmt != null) {
                flightStmt.close();
                airportStmt.close();
            }
        }
    }

    public void deleteFlightByPeriod(int airportId, LocalDate startPeriod,LocalDate endPeriod) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM flight WHERE fk_aId=? AND fDateOfFlight >= ? AND fDateOfFlight <= ?");
            stmt.setInt(1, airportId);
            stmt.setDate(2, Date.valueOf(startPeriod));
            stmt.setDate(3, Date.valueOf(endPeriod));
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void  deleteFlightFromAirport(int idAirport) throws SQLException {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("DELETE FROM flight WHERE fk_aId=?");
                stmt.setInt(1, idAirport);
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
    }

    public void updateAirport(Airport airport) throws SQLException {
        PreparedStatement airportStmt = null;
        try {
            airportStmt = con.prepareStatement("UPDATE airport SET "
                    + "aName=?"
                    + "WHERE aId=?");
            airportStmt.setString(1,airport.getName());
            airportStmt.setInt(2,airport.getId());
            airportStmt.execute();
        } finally {
            if (airportStmt != null) {
                airportStmt.close();
            }
        }
    }

    public void updateFlight(Flight fl) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE flight SET "
                    + "fCompanyName=?, fDateOfFlight=?, fTimeInFlight=?, "
                    + "fTimeTakeOff=?, fPrice=?,fFrom=?,fTo=? WHERE fId =?");
            stmt.setString(1, fl.getCompanyName());
            stmt.setDate(2, Date.valueOf(LocalDate.of(fl.getDateOfFlight().getYear(),fl.getDateOfFlight().getMonth(),fl.getDateOfFlight().getDayOfMonth())));
            stmt.setInt(3, fl.getTimeInFlight());
            stmt.setTime(4, Time.valueOf(LocalTime.of(fl.getTimeTakeOff().getHour(),fl.getTimeTakeOff().getMinute())));
            stmt.setDouble(5, fl.getPrice());
            stmt.setString(6, String.valueOf(fl.getFrom()));
            stmt.setString(7, String.valueOf(fl.getTo()));
            stmt.setInt(8,fl.getId());
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public void moveFlightsToAirport(int oldId,int newId) throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("UPDATE flight SET fk_aId=? "
                    + "WHERE fk_aId=?");
            stmt.setInt(1, newId);
            stmt.setInt(2, oldId);
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public Collection<Flight> getFlightToCity(String toCity) throws SQLException {
        Collection<Flight> flights = new ArrayList<Flight>();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        toCity = toCity +"%";
        try {
            stmt = con.prepareStatement("SELECT * FROM flight WHERE fTo LIKE ?");
            stmt.setString(1,toCity);
            rs = stmt.executeQuery();
            while (rs.next()) {
                Flight fl = new FlightBuilder().
                        setIdentification(rs.getInt("fId")).
                        setCompanyName(rs.getString("fCompanyName")).
                        setDateOfFlight(rs.getDate("fDateOfFlight").toLocalDate()).
                        setTimeInFlight(rs.getInt("fTimeInFlight")).
                        setTimeTakeOff(rs.getTime("fTimeTakeOff").toLocalTime()).
                        setPrice(rs.getDouble("fPrice")).
                        setTo(Flight.City.valueOf(rs.getString("fTo"))).
                        setFrom(Flight.City.valueOf(rs.getString("fFrom"))).
                        build();
                flights.add(fl);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return flights;
    }
    public void deleteAllFlights() throws SQLException {
            PreparedStatement stmt = null;
            try {
                stmt = con.prepareStatement("DELETE FROM flight");
                stmt.execute();
            } finally {
                if (stmt != null) {
                    stmt.close();
                }
            }
    }
    public void deleteAllAirports() throws SQLException {
        PreparedStatement stmt = null;
        try {
            stmt = con.prepareStatement("DELETE FROM airport");
            stmt.execute();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public void clearDataBase() throws SQLException {
        this.deleteAllFlights();
        this.deleteAllAirports();
    }
    public int getCountOfFlights() throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM flight");
            rs.next();
            return rs.getInt("rowcount");
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }
    public int getCountOfAirports() throws SQLException {
        ResultSet rs = null;
        Statement stmt = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT COUNT(*) AS rowcount FROM airport");
            rs.next();
            return rs.getInt("rowcount");
        }
        finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
    }

    public List<Airport> getAllAirports() throws SQLException {
        List<Airport> airports = new ArrayList<Airport>();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            rs = stmt.executeQuery("SELECT * FROM airport");
            while (rs.next()) {
                Airport airport = new Airport();
                airport.setId(rs.getInt("aId"));
                airport.setName(rs.getString("aName"));
                airports.add(airport);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return airports;
    }

    public ArrayList<Flight> getAllFlights() throws SQLException {
        ArrayList<Flight> flights = new ArrayList<Flight>();
        Flight flight = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            stmt = con.prepareStatement("SELECT * FROM flight");
            rs = stmt.executeQuery();
            while (rs.next()) {
                flight = new FlightBuilder().
                        setIdentification(rs.getInt("fId")).
                        setCompanyName(rs.getString("fCompanyName")).
                        setDateOfFlight(rs.getDate("fDateOfFlight").toLocalDate()).
                        setTimeInFlight(rs.getInt("fTimeInFlight")).
                        setTimeTakeOff(rs.getTime("fTimeTakeOff").toLocalTime()).
                        setPrice(rs.getDouble("fPrice")).
                        setTo(Flight.City.valueOf(rs.getString("fTo"))).
                        setFrom(Flight.City.valueOf(rs.getString("fFrom"))).
                        build();
                flights.add(flight);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (stmt != null) {
                stmt.close();
            }
        }
        return flights;
    }
}