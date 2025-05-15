<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="kpi.lab2.utils.AttributesHolder" %>
<%@ page import="kpi.lab2.utils.PathsHolder" %>
<html>
<head>
    <title>Create/Update Car</title>
</head>
<body>
<%@include file="../header.jsp"%>
<div class="container">
    <div class="center jumbotron authorization-section">
        <form action="" method="post">
            <h2 class="form-signin-heading">
                <c:choose>
                    <c:when test="${requestScope[AttributesHolder.CAR] != null && !requestScope[AttributesHolder.NEW_MODE]}">
                        Car Editing
                    </c:when>
                    <c:otherwise>
                        Car Creation
                    </c:otherwise>
                </c:choose>
            </h2>
            <br>
            <c:if test="${requestScope[AttributesHolder.ERROR_MESSAGE] != null}">
                <div class="alrt alert-danger">
                        ${requestScope[AttributesHolder.ERROR_MESSAGE]}
                </div>
            </c:if>
            <input type="hidden" value="${car.id}" name="${AttributesHolder.ID}">
            <br>
            <c:if test="${errors != null && errors.messages[AttributesHolder.MODEL] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.MODEL]}
                </div>
            </c:if>
            <input type="text" class="form-control" name="${AttributesHolder.MODEL}" value="<c:out value="${car.model}"/>"
                   placeholder="Model" required>
            <br>
            <c:if test="${errors != null && errors.messages[AttributesHolder.YEAR] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.YEAR]}
                </div>
            </c:if>
            <input type="text" class="form-control" name="${AttributesHolder.YEAR}" value="<c:out value="${car.year}"/>"
                   placeholder="Year" required>
            <br>
            <c:if test="${errors != null && errors.messages[AttributesHolder.FUEL_CONSUMPTION] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.FUEL_CONSUMPTION]}
                </div>
            </c:if>
            <input type="text" class="form-control" name="${AttributesHolder.FUEL_CONSUMPTION}" value="<c:out value="${car.fuelConsumption}"/>"
                   placeholder="Year" required>
            <br>
            <c:if test="${errors != null && errors.messages[AttributesHolder.FUEL_CAPACITY] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.FUEL_CAPACITY]}
                </div>
            </c:if>
            <input type="text" class="form-control" name="${AttributesHolder.FUEL_CAPACITY}" value="<c:out value="${user.fuelCapacity}"/>"
                   placeholder="Year" required>
            <br>
            <c:if test="${errors != null && errors.messages[AttributesHolder.MANUFACTURER] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.MANUFACTURER]}
                </div>
            </c:if>
            <div class="form-group">
                <select class="form-control" name="${AttributesHolder.MANUFACTURER}" id="sel1">
                    <option value="">Country of Birth</option>
                    <c:forEach var="item" items="${requestScope[AttributesHolder.MANUFACTURERS]}">
                        <c:choose>
                            <c:when test="${requestScope[AttributesHolder.CAR] != null &&
                                    requestScope[AttributesHolder.CAR].manufacturer.id == item.id}">
                                <option selected value="${item.id}">${item.id} - ${item.name}</option>
                            </c:when>
                            <c:otherwise>
                                <option value="${item.id}">${item.id} - ${item.name} - ${item.country}</option>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </select>
            </div>
            <c:if test="${errors != null && errors.messages[AttributesHolder.MODEL] != null}">
                <div class="alrt alert-danger">
                        ${errors.messages[AttributesHolder.MODEL]}
                </div>
            </c:if>
            <input type="text" class="form-control" name="${AttributesHolder.MODEL}" value="<c:out value="${car.model}"/>"
                   placeholder="Model" required >
            <br>
            <button class="btn btn-lg btn-primary btn-block" type="submit">
                Save
            </button>
        </form>
    </div>
</div>
</body>
</html>