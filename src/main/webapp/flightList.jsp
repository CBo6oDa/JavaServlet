<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Flights</title>
</head>
<body>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Company name</th>
        <th>Date of flight</th>
        <th>Time in flight</th>
        <th>Time take off</th>
        <th>Price</th>
        <th>From</th>
        <th>To</th>
        <th>Edit</th>
        <th>Delete</th>
    </tr>
    <c:forEach items="${flights}" var="flight" >
        <tr>
            <td>${flight.id}</td>
            <td>${flight.companyName}</td>
            <td>${flight.dateOfFlight}</td>
            <td>${flight.timeInFlight}</td>
            <td>${flight.timeTakeOff}</td>
            <td>${flight.price}</td>
            <td>${flight.from}</td>
            <td>${flight.to}</td>
            <td><a href="FlightHandle?action=edit&flightId=<c:out value="${flight.id}"/>">Update</a></td>
            <td><a href="FlightHandle?action=delete&flightId=<c:out value="${flight.id}"/>">Delete</a></td>
        </tr>
    </c:forEach>
</table>

<p><a href="FlightHandle?action=insert&airportId=${param.airportId}">Add Flight</a></p>
<p><a href="AirportHandle?action=airportList">Back To Airport</a></p>

</body>
</html>
