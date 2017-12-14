<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page isELIgnored ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
    <title>Add New Airport</title>
</head>
<body>

<form method="POST" action='AirportHandle' name="frmAddAirport">

    <table border="1" cellpadding="5" cellspacing="1">
        <tr>
            <td>ID</td>
            <td><input type="text" readonly="readonly" name="airportId"/></td>
        </tr>
        <tr>
            <td>Name</td>
            <td><input type="text" name="airportName" pattern="([A-Z]([a-zA-Z]*(\s?)))+" title="Example : New York Airport"/></td>
        </tr>

        <tr>
            <td><input type="submit" value="add" /></td>
            <td><a href="/AirportHandle?action=airportList">Cancel</a></td>
        </tr>
    </table>
</form>
</body>
</html>