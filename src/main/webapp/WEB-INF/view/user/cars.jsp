<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="kpi.lab2.utils.AttributesHolder" %>
<%@ page import="kpi.lab2.utils.PathsHolder" %>
<html>
<head>
    <title>Cars</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="text-center">
    <h2>Car Management</h2>
</div>
<div>
    <a href="${PathsHolder.ADD_CAR}">
        <button class="btn btn-primary btn-block">Add User</button>
    </a>
</div>
<table class="table">
    <caption>Cars</caption>
    <tr>
        <th>Car Id</th>
        <th>Model</th>
        <th>Year</th>
        <th>Kilometrage</th>
        <th>Fuel Consumption</th>
        <th>Fuel Capacity</th>
        <th>Manufacturer</th>
        <th></th>
        <th></th>
    </tr>
    <c:forEach var="user" items="${requestScope[AttributesHolder.CARS]}">
        <tr>
            <input type="hidden" value="${car.id}" name="${AttributesHolder.ID}">
            <td><c:out value="${car.id}"/></td>
            <td><c:out value="${car.model}"/></td>
            <td><c:out value="${car.year}"/></td>
            <td><c:out value="${car.fuelConsumption}"/></td>
            <td><c:out value="${car.fuelCapacity}"/></td>
            <td><c:out value="${car.manufacturer}"/></td>
            <td><c:out value="${user.manufacturer.id}"/></td>
            <td><a href="${PathsHolder.EDIT_CAR}/${car.id}" class="btn btn-warning btn-block">
                Edit
            </a></td>
            <td><form action="${PathsHolder.DELETE_CAR}/${car.id}" method="post">
                <button class="btn btn-danger btn-block">Delete</button>
            </form>
            </td>
        </tr>
    </c:forEach>
</table>
</body>
</html>