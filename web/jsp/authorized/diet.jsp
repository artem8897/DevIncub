<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<div style="text-align: center">
<form name="Trainer" method="POST" action="controller"/>
<c:set var="diet" value="${diet}"/>
<select name="diet_type" onselect="${diet['diet_type']}">

    <option value="low calorie diet" > <fmt:message key="label.low_calorie_diet"/>

    <option value="mono diet" > <fmt:message key="label.mono_diet"/>

    <option value="fat restriction diet"> <fmt:message key="label.fat_restriction_diet"/>

    <option value="low carb diet"> <fmt:message key="label.low_carb_diet"/>

    <option value="separate food"> <fmt:message key="label.separate_food"/>

    <option value="balanced diet"> <fmt:message key="label.balanced_diet"/>
</select>
</br><fmt:message key="label.proteins"/> <br/>
<input type="text" name="proteins"  value="${diet['proteins']}" required pattern="[0-9]{0,3}"/>
</br><fmt:message key="label.fats"/> <br/>
<input type="text" name="fats"  value="${diet['fats']}" required pattern="[0-9]{0,3}"/>
</br><fmt:message key="label.carbohydrates"/> <br/>
<input type="text" name="carbohydrates"  value="${diet['carbohydrates']}" required pattern="[0-9]{0,3}"/>
<input type="hidden" name="user_id" value="${user_id}">
<br/>
<c:set var = "move" value = "${mov}"/>
<br/>
<c:choose>
    <c:when test="${move == 'ADD'}">
        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=add_diet ><fmt:message key="label.make_diet"/></button>
    </c:when>
    <c:when test="${move == 'UPDATE'}">
        <button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_diet ><fmt:message key="label.update_diet"/></button>
    </c:when>
</c:choose>
<br/>
</div>

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

<c:import url="/jsp/footer.jsp"/>

</body>
</html>