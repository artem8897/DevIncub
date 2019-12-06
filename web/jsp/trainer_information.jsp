<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.registration"/></title></head>
<div class="w3-container w3-center">
    <body>
    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/user_navigation.jsp"/>
    <c:import url="/jsp/user_redirect.jsp"/>

    <c:set var="trainers" value="${trainers}"/>
    <form name="AddTrainerInformation" method="POST" action="controller"/>
    </br><fmt:message key="label.work_experience"/> <br/>
    <input type="text" name="work_experience"  value="${trainers['work_experience']}" required pattern = "([0-9]{1,2})"/>
    <br/><fmt:message key="label.name"/><br/>
    <input type="text" name="name"  value="${trainers['name']}" required pattern = "(([А-Яа-я]{5,20})|([a-zA-z]{5,20}))"/>
    <br/><fmt:message key="label.training_type"/><br/>
    <input type="text" name="training_type" value="${trainers['training_type']}" required pattern = "(([а-яА-я]{5,14})|([a-zA-Z]{2,20}))"/>
    <input type="hidden" name="user_id" value="${user_id}">
    <br/>
    <c:set var = "move" value = "${mov}"/>
    <br/>

    <c:choose>
        <c:when test="${move == 'ADD'}">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=add_trainer_information ><fmt:message key="label.create_trainer_information"/></button>
        </c:when>
        <c:when test="${move == 'UPDATE'}">
            <button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_trainer_information ><fmt:message key="label.change_trainer_information"/></button>
        </c:when>
    </c:choose></form><hr/>

    </form><hr/>
    <a href="controller?command=logout"><fmt:message key="label.logout"/></a>
    </body>
</div>
</html>