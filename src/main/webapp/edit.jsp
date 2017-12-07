<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="models.Airport"%>
<%@ page import="dataBase.JDBC"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Edit airport</title>
</head>
<body>
<%
    Airport airport = new Airport();
%>
<%
    JDBC jdbc = new JDBC();
%>
<form method="POST" action='AiportHandle' name="frmEditAirport"><input type="hidden" name="action" value="edit"/> <%
    String aId = request.getParameter("airportId");
    if (!((aId) == null)) {
        int id = Integer.parseInt(aId);
        airport = jdbc.getAirport(id);
%>
    <table>
        <tr>
            <td>Airport ID</td>
            <td><input type="text" name="airportId" readonly="readonly" value="<%=airport.getId()%>"></td>
        </tr>
        <tr>
        <td>Name</td>
            <td><input type="text" name="airportName" /></td>
        </tr>
        <tr>
            <td></td>
            <td><input type="submit" value="Update" /></td>
        </tr>
    </table>
    <%
    }
    %>
</form>
</body>
</html>