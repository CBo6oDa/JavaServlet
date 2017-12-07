<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Add New Airport</title>
</head>
<body>
<form method="POST" action='AirportHandle' name="frmAddUser"><input type="hidden" name="action" value="insert" />
    <p><b>Add New Record</b></p>
    <table>
        <tr>
            <td>Airport ID</td>
            <td><input type="text" name="airportId" /></td>
        </tr>
        <tr>
            <td>Airport name</td>
            <td><input type="text" name="airportName"/></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Submit" /></td>
        </tr>
    </table>
</form>
<p><a href="AirportHandle",action=airportList>View-All-Records</a></p>
</body>
</html>