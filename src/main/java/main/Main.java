package main;

import builders.AirportBuilder;
import builders.FlightBuilder;
import dataBase.JDBC;
import models.Airport;
import models.Flight;
import providers.AirportProvider;
import providers.AirportProviderImpl;
import services.AirportService;
import services.AirportServiceImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

    Airport airport = new AirportBuilder().setIdentification(4).setName("MAO").build();
    List<Airport> airports = new ArrayList<Airport>();
    AirportProvider airportProvider = new AirportProviderImpl(airports);
    AirportService airportService = new AirportServiceImpl(airportProvider);

    Airport myAirport = new AirportBuilder().setIdentification(4).setName("Enterprise").build();
    airportService.addAirport(myAirport);

    Airport secondAirport = new AirportBuilder().setId().setName("New Line").build();
    airportService.addAirport(secondAirport);

        try {
        Flight flight = new FlightBuilder()
                .setId()
                .setCompanyName("Eclipse")
                .setDateOfFlight(LocalDate.of(2013, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(8, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.KHARKIV)
                .setTo(Flight.City.WASHINGTON)
                .build();
        airportService.addFlight(myAirport.getId(), flight);

    } catch (RuntimeException e) {
        System.out.println(e);
    }

        try {
        Flight flight = new FlightBuilder()
                .setId()
                .setCompanyName("West Side")
                .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(8, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.KIEV)
                .setTo(Flight.City.KHARKIV)
                .build();
        airportService.addFlight(secondAirport.getId(), flight);
    } catch (RuntimeException e) {
        System.out.println(e);
    }

        try {
        Flight flight = new FlightBuilder()
                .setId()
                .setCompanyName("West")
                .setDateOfFlight(LocalDate.of(2011, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(10, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.MADRID)
                .setTo(Flight.City.KIEV)
                .build();
        airportService.addFlight(secondAirport.getId(), flight);
    } catch (RuntimeException e) {
        System.out.println(e);
    }

        try {
        Flight flight = new FlightBuilder()
                .setId()
                .setCompanyName("Eclipce")
                .setDateOfFlight(LocalDate.of(2019, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(14, 30))
                .setTimeInFlight(120)
                .setPrice(120.99)
                .setFrom(Flight.City.MADRID)
                .setTo(Flight.City.MINSK)
                .build();
        airportService.addFlight(myAirport.getId(), flight);
    } catch (RuntimeException e) {
        System.out.println(e);
    }
        Flight flight=null;
        try {
        flight = new FlightBuilder()
                .setId()
                .setCompanyName("Fdea")
                .setDateOfFlight(LocalDate.of(2015, Month.SEPTEMBER, 25))
                .setTimeTakeOff(LocalTime.of(14, 30))
                .setTimeInFlight(120).setPrice(120.99)
                .setFrom(Flight.City.BERLIN)
                .setTo(Flight.City.TOKIO)
                .build();
        airportService.addFlight(myAirport.getId(), flight);
    } catch (RuntimeException e) {
        System.out.println(e);
    }
        System.out.println();
//
//    FileWriter fileWriter = null;
//        try {
//        fileWriter = new FileWriter("jsonFile.json");
//    } catch (IOException e) {
//        e.printStackTrace();
//    }
//
//
//    ArrayList<Flight> air = new ArrayList<Flight>();
//        try {
//        Flight flight = new FlightBuilder()
//                .setId()
//                .setCompanyName("West Side")
//                .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
//                .setTimeTakeOff(LocalTime.of(8, 30))
//                .setTimeInFlight(120)
//                .setPrice(120.99)
//                .setFrom(Flight.City.KIEV)
//                .setTo(Flight.City.LONDON)
//                .build();
//        air.add(flight);
//    } catch (RuntimeException e) {
//        System.out.println(e);
//    }
//    File jsonFile = new File("jsonFile.json");
//    File xmlFile = new File("xmlFile.xml");
//    //        Serialisation<Airport> xmlFlight = new XmlSerializeImpl<Airport>(Airport.class);
////        xmlFlight.toFile(airports,xmlFile);
////        ArrayList<Airport> obj = null;
////        try {
////            obj = xmlFlight.fromFile(xmlFile);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        for (Airport ob:obj) {
////            System.out.println(ob);
////        }
////        Serialisation<Airport> json = new JsonSerializeImpl<Airport>(Airport.class);
////        json.toFile(airports,jsonFile);
////        ArrayList<Airport> ob = null;
////        try {
////            ob = json.fromFile(jsonFile);
////        } catch (Exception e) {
////            e.printStackTrace();
////        }
////        for (Airport o:ob) {
////            System.out.println(o);
////        }
//    ArrayList<Flight> flightArrayList = new ArrayList<Flight>();
//    Flight testFlight = null;
//        try {
//        testFlight = new FlightBuilder()
//                .setId()
//                .setCompanyName("West Side")
//                .setDateOfFlight(LocalDate.of(2017, Month.SEPTEMBER, 25))
//                .setTimeTakeOff(LocalTime.of(8, 30))
//                .setTimeInFlight(120)
//                .setPrice(120.99)
//                .setFrom(Flight.City.KIEV)
//                .setTo(Flight.City.LONDON)
//                .build();
//        flightArrayList.add(testFlight);
//        flightArrayList.add(testFlight);
//    } catch (RuntimeException e) {
//        System.out.println(e);
//    }
//        try {
//        testFlight = new FlightBuilder()
//                .setId()
//                .setCompanyName("MOA")
//                .setDateOfFlight(LocalDate.of(2018, Month.DECEMBER, 23))
//                .setTimeTakeOff(LocalTime.of(3, 20))
//                .setTimeInFlight(140)
//                .setPrice(120)
//                .setFrom(Flight.City.LVIV)
//                .setTo(Flight.City.MADRID)
//                .build();
//        flightArrayList.add(testFlight);
//        flightArrayList.add(testFlight);
//    } catch (RuntimeException e) {
//        System.out.println(e);
//    }
//    File txtFile = new File("txtFile.txt");
//    TxtSerialisationImpl<Airport> txtSerialisation = new TxtSerialisationImpl<Airport>();
//        txtSerialisation.toFile(airportService.getAirportList(), txtFile);
//        try {
//        ArrayList<Airport> airportList = new ArrayList<Airport>();
//        airportList = txtSerialisation.fromFile(txtFile);
//        for (Airport fl : airportList) {
//            System.out.println(fl);
//        }
//    } catch (Exception e) {
//        e.printStackTrace();
//    }
        JDBC jdbc = null;
        try {
            jdbc = new JDBC();
            jdbc.clearDataBase();
            jdbc.addAirport(myAirport);
            for (int i=0;i<myAirport.getFlights().size();i++){
                jdbc.addFlight(myAirport.getFlights().get(i),myAirport.getId());
            }
            jdbc.addAirport(secondAirport);
            for (int i=0;i<secondAirport.getFlights().size();i++){
                jdbc.addFlight(secondAirport.getFlights().get(i),secondAirport.getId());
            }
            System.out.println(jdbc.getAirportByFlight(2));
//            jdbc.deleteAirport(4);
//            for(Flight fl : jdbc.getAllFlightsFrom(airport.getId())){
//                System.out.println(fl);
//            }
//            jdbc.deleteFlight(15);
//            Flight flight = new FlightBuilder()
//                    .setIdentification(14)
//                    .setCompanyName("Side")
//                    .setDateOfFlight(LocalDate.of(2013, Month.APRIL, 5))
//                    .setTimeTakeOff(LocalTime.of(8, 3))
//                    .setTimeInFlight(10)
//                    .setPrice(120.99)
//                    .setFrom(Flight.City.LVIV)
//                    .setTo(Flight.City.OTTAWA)
//                    .build();
//          jdbc.updateFlight(flight);
//            Airport airport1 = new AirportBuilder().setId().setName("Turkey Airlines").build();
//            jdbc.addAirport(airport1);
//          jdbc.deleteAirport(airport1.getId());
//          jdbc.updateAirport(new AirportBuilder().setIdentification(4).setName("POLICY").build());

//            LocalDate stDate = LocalDate.of(2010,9,25);
//            LocalDate enDate = LocalDate.of(2017,9,25);
//            jdbc.deleteFlightByPeriod(myAirport.getId(),stDate,enDate);
//            airports = new ArrayList<Airport>();
//            airports = jdbc.getAirports();
//            for(Airport airport1 : airports){
//                airport1.setFlights((ArrayList<Flight>) jdbc.getAllFlightsFrom(airport1.getId()));
//            }
//            jdbc.moveFlightsToAirport(1,4);
//            jdbc.deleteFlightFromAirport(4);
//            jdbc.getFlightTo("KH");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
