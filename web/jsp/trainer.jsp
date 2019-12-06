<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.title" /></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
    <div class="w3-bar w3-padding-large w3-padding-24">

        <form name="SetTraining" method="POST" action="controller">
            <input type="hidden" name="command" value="CHOOSE_USER_FOR_CREATING_DATA" />
            <input type="hidden" name="currentPage" value="1">
            <input type="hidden" name="type" value="diet">
            <input type="hidden" name="recordsPerPage" value="5">
            <button class="w3-btn w3-hover-light-blue w3-round-large" value="CHOOSE_USER_FOR_CREATING_DATA"><fmt:message key="label.set_diet"/></button>
        </form>
        <form name="SetTraining" method="POST" action="controller">
            <input type="hidden" name="command" value="CHOOSE_USER_FOR_CREATING_DATA" />
            <input type="hidden" name="currentPage" value="0">
            <input type="hidden" name="type" value="training">
            <input type="hidden" name="recordsPerPage" value="5">
            <button class="w3-btn w3-hover-light-blue w3-round-large" value="CHOOSE_USER_FOR_CREATING_DATA"><fmt:message key="label.set_training"/></button>
        </form>
        <form name="SetTraining" method="POST" action="controller">
            <input type="hidden" name="command" value="trainer_edition" />
            <input type="hidden" name="currentPage" value="0">
            <input type="hidden" name="recordsPerPage" value="5">
            <button class="w3-btn w3-hover-light-blue w3-round-large" value="trainer_edition"><fmt:message key="label.change_destination"/></button>
        </form>
<%--        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=&=&=&='"> </button>--%>

<%--        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=&currentPage=1&recordsPerPage=5&type=diet'"></button>--%>

<%--        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=&currentPage=1&recordsPerPage=5'"> </button>--%>

        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=TRAINER_PAGE'"><fmt:message key="label.change_user_information"/> </button>

        <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=show_trainers_reviews&trainer_id=${User.id}'"><fmt:message key="label.display_reviews"/> </button>
</div>
<c:import url="/jsp/footer.jsp"/>
</body></html>