<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add new flight</title>
    <style>
        select {
            width: 150px;
        }
    </style>
</head>
<body>
<form method="POST" action='FlightHandle' name="frmAddFlight" id="flightForm">

    <table border="1" cellpadding="5" cellspacing="1">

        <input type="hidden" name="flightId" value="<c:out value="${flight.id}" />" />

        <tr>
            <td>Company name</td>
            <td><input type="text" name="flightCompanyName" pattern="([A-Z]([a-zA-Z]*(\s?)))+" title="Example : Turkish Airlines" value="<c:out value="${flight.companyName}"/>" /></td>
        </tr>

        <tr>
            <td>Date of flight</td>
            <td><input type="date" name="flightDateOfFlight" pattern="[0-9]{4}-(0[1-9]|1[012])-(0[1-9]|1[0-9]|2[0-9]|3[01])" title="Format: YYYY-MM-DD" min = "2017-12-13" value="<c:out value="${flight.dateOfFlight}" />" /></td>
        </tr>

        <tr>
            <td>Time in flight</td>
            <td><input type="text" name="flightTimeInFlight" pattern="\d{1,3}" title="Time In Flight have to be bigger than 0 and less than 1000" value="<c:out value="${flight.timeInFlight}" />" /></td>
        </tr>

        <tr>
            <td>Time take off</td>
            <td><input type="text" name="flightTimeTakeOff" pattern="([01]?[0-9]|2[0-3]):[0-5][0-9]" title="Format : hh:mm" value="<c:out value="${flight.timeTakeOff}" />" /></td>
        </tr>

        <tr>
            <td>Price</td>
            <td><input type="text" name="flightPrice" pattern="\d{1,6}((\.0)?(\.\d{2})?)" title="Example : 120,230.50" value="<c:out value="${flight.price}" />" /></td>
        </tr>

        <tr>
            <td>From</td>
            <td>
                <select name="cityFrom" id="cityFrom">
                    <option value="KIEV">KIEV</option>
                    <option value="LVIV">LVIV</option>
                    <option value="KHARKIV">KHARKIV</option>
                    <option value="BERLIN">BERLIN</option>
                    <option value="WARSAW">WARSAW</option>
                    <option value="LISSABON">LISSABON</option>
                    <option value="PARIS">PARIS</option>
                    <option value="CHISINAU">CHISINAU</option>
                    <option value="MINSK">MINSK</option>
                    <option value="MADRID">MADRID</option>
                    <option value="PEKIN">PEKIN</option>
                    <option value="WASHINGTON">WASHINGTON</option>
                    <option value="OTTAWA">OTTAWA</option>
                    <option value="CHICAGO">CHICAGO</option>
                </select>
            </td>
        </tr>

        <tr>
            <td>To</td>
            <td>
                <select name="cityTo" id="cityTo"size="">
                        <option value="KIEV">KIEV</option>
                        <option value="LVIV">LVIV</option>
                        <option value="KHARKIV">KHARKIV</option>
                        <option value="BERLIN">BERLIN</option>
                        <option value="WARSAW">WARSAW</option>
                        <option value="LISSABON">LISSABON</option>
                        <option value="PARIS">PARIS</option>
                        <option value="CHISINAU">CHISINAU</option>
                        <option value="MINSK">MINSK</option>
                        <option value="MADRID">MADRID</option>
                        <option value="PEKIN">PEKIN</option>
                        <option value="WASHINGTON">WASHINGTON</option>
                        <option value="OTTAWA">OTTAWA</option>
                        <option value="CHICAGO">CHICAGO</option>
                </select>
            </td>
        </tr>
        <tr>
            <input type="hidden" name="airportId" value=${param.airportId} />
        </tr>
        <tr>
            <td><input type="submit" value="Add" /></td>
            <td><a href="/AirportHandle?action=airportList">Cancel</a></td>
        </tr>
    </table>

</form>
</body>
</html>