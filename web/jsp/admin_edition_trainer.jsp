<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.title" /></title></head>
<body>
<div style="text-align: center;">

    <form name="loginForm" method="POST" action="controller">
<div class="w3-center">
        <c:import url="/jsp/header.jsp"/>
        <select name="user_id">
            <c:forEach var="item" items="${alltrainer}">
                <option value="${item.key}"/> ${item.value}
            </c:forEach>
        </select>

        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">

                <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=TRAINER_PAGE><fmt:message key="label.change_trainer_personal_information"/> </button>
                <button button class="w3-btn w3-hover-green w3-round-large" name="command" value=delete_user ><fmt:message key="label.delete"/> </button>

            </div>
        </div>
</div>
            <a href="controller?command=logout"><fmt:message key="label.logout"/></a>
</body></html>
