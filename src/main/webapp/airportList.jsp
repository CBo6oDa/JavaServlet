<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@taglib  uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored ="false" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>All Airpots</title>
</head>
<body>

<table border="1" cellpadding="5" cellspacing="1" >
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>Flights</th>
    </tr>
    <c:forEach items="${airports}" var="airport" >
        <tr>
            <td>${airport.id}</td>
            <td>${airport.name}</td>
            <td><a href="AirportHandle?action=edit&airportId=<c:out value="${airport.id}"/>">Update</a></td>
            <td><a href="AirportHandle?action=delete&airportId=<c:out value="${airport.id}"/>">Delete</a></td>
            <td><a href="FlightHandle?action=flightList&airportId=<c:out value="${airport.id}"/>">click</a></td>
        </tr>
    </c:forEach>
</table>
    <p><a href="AirportHandle?action=insert">Add airport</a></p>
</body>
</html>
