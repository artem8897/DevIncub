<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.registration"/></title></head>
<div class="w3-container w3-center">
    <body>
    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/user_navigation.jsp"/>

    <form name="TrainerInformation" method="POST" action="controller"/>
    <c:set var="trainers" value="${trainers}"/>
    </br><fmt:message key="label.work_experience"/> <br/>
    <input type="text" name="work_experience"  value="${trainers['work_experience']}" required pattern = "([0-9]{1,2})"/>
    <br/><fmt:message key="label.name"/><br/>
    <input type="text" name="name" value="${trainers['name']}" required pattern = "(([А-Яа-я]{5,20})|([a-zA-z]{5,20}))"/>
    <br/><fmt:message key="label.training_type"/><br/>
    <input type="text" name="training_type" value="${trainers['training_type']}" required pattern = "(([а-яА-я ]{5,14})|([a-zA-Z ]{2,20}))"/>
    <input type="hidden" name="user_id" value="${user_id}">
    <br/>
    <span style="color: red">${info}</span>
    <br/>
    <c:set var = "move" value = "${mov}"/>
    <br/>

    <c:choose>
        <c:when test="${move == 'ADD'}">
            <input type="hidden" name="redirect" value="controller?command=GO_TO_TRAINER">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=add_trainer_information ><fmt:message key="label.create_trainer_information"/></button>
        </c:when>
        <c:when test="${move == 'UPDATE'}">
            <button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_trainer_information ><fmt:message key="label.change_trainer_information"/></button>
        </c:when>
    </c:choose><hr/>


    <c:set var = "user_type" value = "${user_type}"/>

    <c:choose>
        <c:when test="${user_type.equals('ADMIN')}">
            <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">
        </c:when>
        <c:when test="${user_type.equals('TRAINER')}">
            <input type="hidden" name="redirect" value="controller?command=GO_TO_TRAINER">
        </c:when>
    </c:choose>
    </form>
    <hr/>

    <c:import url="/jsp/footer.jsp"/>
    </body>
</div>
</html>