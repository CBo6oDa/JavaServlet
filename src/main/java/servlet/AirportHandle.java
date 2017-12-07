package servlet;

import dataBase.JDBC;
import models.Airport;
import models.Flight;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class AirportHandle extends HttpServlet {
    private static String INSERT = "/index.jsp";
    private static String Edit = "/edit.jsp";
    private static String List = "/airportList.jsp";
    private Airport airport=null;
    JDBC jdbc=null;

    public AirportHandle() throws SQLException, ClassNotFoundException {
        super();
        jdbc = new JDBC();
        airport = new Airport();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String redirect="";
        String aId = request.getParameter("airportId");
        String action = request.getParameter("action");
        if(!((aId)== null) && action.equalsIgnoreCase("insert")) {
            int id = Integer.parseInt(aId);
            Airport airport = new Airport();
            airport.setId(id);
            airport.setName(request.getParameter("airportName"));

            try {
                jdbc.addAirport(airport);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            redirect = List;
            try {
                request.setAttribute("airport", jdbc.getAllAirports());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record Added Successfully");
        }

        else if (action.equalsIgnoreCase("delete")){
            String airportId = request.getParameter("airportId");
            int id = Integer.parseInt(airportId);

            try {
                jdbc.deleteAirport(id);
            } catch (SQLException e) {
                e.printStackTrace();
            }

            redirect = List;
            try {
                request.setAttribute("airports", jdbc.getAllAirports());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            System.out.println("Record Deleted Successfully");
        } else if (action.equalsIgnoreCase("editform")){
            redirect = Edit;
        } else if (action.equalsIgnoreCase("edit")){
            String userId = request.getParameter("airportId");
            int aid = Integer.parseInt(userId);
            Airport airport = new Airport();
            airport.setId(aid);
            airport.setName(request.getParameter("airportName"));
            try {
                jdbc.updateAirport(airport);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            request.setAttribute("airport", airport);
            redirect = List;
            System.out.println("Record updated Successfully");
        } else if (action.equalsIgnoreCase("airportList")){
            redirect = List;
            try {
                request.setAttribute("airport", jdbc.getAllAirports());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } else {
            redirect = INSERT;
        }
        RequestDispatcher rd = request.getRequestDispatcher(redirect);
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}