<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.title" /></title></head>
<body>
<div style="text-align: center;">
    <c:import url="/jsp/header.jsp"/>
        <c:import url="/jsp/user_navigation.jsp"/>
        <div class="w3-container w3-center">
            <div class="w3-bar w3-padding-large w3-padding-24">

                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=admin_choose_person&currentPage=0&recordsPerPage=5'"><fmt:message key="label.edit_information"/> </button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=admin_choose_person&currentPage=0&recordsPerPage=5'"> СКИДКИ!!!!! </button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=admin_choose_person&currentPage=0&recordsPerPage=5'"> СДЕЛАТЬ УДАЛЕНИЕ!!! </button>
                <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=ADMIN_EDIT_USER&currentPage=0&recordsPerPage=5'"><fmt:message key="label.change_user_status"/> </button>
                <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='controller?command=edition_trainer'"><fmt:message key="label.edit_trainer"/> </button>
                <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=empty_trainer >create trainer </button>

            </div>
        </div>
        ${User}, admin <fmt:message key="label.welcome" />
<hr/>
<a href="controller?command=ShowAllUsersCommand"><fmt:message key="label.show_all_users"/></a>
<hr>
<a href="controller?command=logout"><fmt:message key="label.logout"/></a>
</body></html>