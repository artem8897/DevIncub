<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" scope="session" />
<html>
<body>
<c:import url="/jsp/header.jsp"/>
<form name="Training" method="POST" action="controller"/>

<br/><fmt:message key="label.password"/><br/>
<input type="password" name="password" value= ""  required pattern="[0-9a-zA-Z]{5,10}"/>
<br/><fmt:message key="label.confirmpassword"/><br/>
<input type="password" name="password2" value= ""  required pattern="[0-9a-zA-Z]{5,10}"/>

<button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_training ><fmt:message key="label.update_training"/></button>


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