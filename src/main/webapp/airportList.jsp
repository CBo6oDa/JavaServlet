<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="models.Airport"%>
<%@ page import="dataBase.JDBC"%>
<%@ page import="java.util.*"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All airports</title>
</head>
<body>
<%
    JDBC jdbc = new JDBC();
    List<Airport> airportList = jdbc.getAllAirports();

%>
<table border="1">
    <tr>
        <th>Id</th>
        <th>Aiport Name</th>
    </tr>
    <tr>
        <%
            for (Airport airport : airportList) {
        %>
        <td><%=airport.getId()%></td>
        <td><%=airport.getName()%></td>
        <td><a href="AirportHandle?action=editform&airportId=<%=airport.getId()%>">Update</a></td>
        <td><a href="AirportHandle?action=delete&airportId=<%=airport.getId()%>">Delete</a></td>

    </tr>
    <%
        }
    %>
</table>
<p><a href="AirportHandle?action=insert">Add airport</a></p>
</body>
</html>
