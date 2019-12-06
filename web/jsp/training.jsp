<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" scope="session" />
<html>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<form name="Training" method="POST" action="controller"/>
<c:set var="training" value="${training}"/>
</br><fmt:message key="label.periodicity"/> <br/>
<input type="date" name="date"  value="${training['date']}" required/>
</br><fmt:message key="label.training_type"/> <br/>
<input type="text" name="training type"  value="${training['training']}" required/>
</br>personality <br/>
<input type="text" name="personality"  value="${training['personality']}" required/>

<br/>
<c:set var = "move" value = "${mov}"/>
<br/>

<c:choose>
    <c:when test="${move == 'ADD'}">
        <input type="hidden" name="user_id" value="${user_id}">
        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value="ADD_PERSONAL_TRAINING" ><fmt:message key="label.set_training"/></button>
    </c:when>
    <c:when test="${move == 'UPDATE'}">
        <input type="hidden" name="training_id" value="${training['training_id']}">
        <button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_training ><fmt:message key="label.update_training"/></button>
    </c:when>
</c:choose>
<br/>

<c:set var = "user_type" value = "${user_type}"/>

<br/>
<c:choose>
    <c:when test="${user_type.equals('ADMIN')}">
        <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">
    </c:when>
    <c:when test="${user_type.equals('USER')}">
        <input type="hidden" name="redirect" value="controller?command=student_acc">
    </c:when>
    <c:when test="${user_type.equals('TRAINER')}">
        <input type="hidden" name="redirect" value="controller?command=GO_TO_TRAINER">
    </c:when>
</c:choose></form><hr/>

</form><hr/>
</body>
</html>