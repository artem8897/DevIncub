<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE html>
<html lang="en">
<head>
    <head>
        <meta charset="UTF-8">
        <title>Countries</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
        <title><fmt:message key="label.welcome"/></title>
        <link rel="stylesheet" href="styles/w3.css">
</head>

<body class="w3-light-grey">
<div class="w3-container w3-blue-grey w3-opacity w3-right-align">
    <h1>
<%--        <fmt:message key="label.welcome"/>--%>
    <c:set var = "user" value = "${user_type}" scope="session"/>
    <c:choose>
        <c:when test="${user == null}">
            <a href="controller?command=gotologin"><fmt:message key="label.welcome"/></a>
        </c:when>
        <c:when test="${user.equals('ADMIN')}">
            <a href="controller?command=GO_TO_ADMIN"><fmt:message key="label.welcome"/></a>
        </c:when>
        <c:when test="${user.equals('USER')}">
            <a href="controller?command=student_acc"><fmt:message key="label.welcome"/></a>
        </c:when>
        <c:when test="${user.equals('TRAINER')}">
            <a href="controller?command=GO_TO_TRAINER"><fmt:message key="label.welcome"/></a>
        </c:when>
    </c:choose>
    </h1>
</div>


</body>
</html>
