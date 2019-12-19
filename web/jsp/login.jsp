<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head>
    <title><fmt:message key="label.login"/></title></head>
<body>
    <c:import url="/jsp/header.jsp"/>
    <div class="w3-panel w3-green w3-opacity w3-center">

        <form name="Trainers" method="GET" action="controller">
            <input type="hidden" name="currentPage" value="0" required pattern="[0-9]{0,2}">
            <input type="hidden" name="recordsPerPage" value="5" required pattern="[0-9]{0,2}">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=show_all_trainers><fmt:message key="label.display_trainers"/></button>
        </form>

        <% System.out.println(this.getServletConfig()); %>
        <form name="Choose_users_local" method="GET" action="controller">

            <input type="hidden" name="command" value="change_localization" />

            <td  valign=top colspan=2>

                <fmt:message key="label.choose_local"/>

                <input type="radio" name="local" value="en_US" checked> ENG

                <input type="radio" name="local" value="ru_RU" > РУС

            </td>

            <td> <button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value="change_localization"><fmt:message key="label.change_locale"/></button></td>
        </form>
    </div>

<form name="loginForm" method="POST" action="controller">
    <div style="text-align: left;">
<input type="hidden" name="command" value=login />
    <fmt:message key="label.email" /><br/>
    <input type="email" name="email" value="" class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required pattern="[A-Za-z0-9]{4,20}@[a-z]{3,7}.[a-z]{2,3}"><br />
    <br/><fmt:message key="label.password"/> <br/>
    <input type="password" name="password" value=""  class="w3-input w3-animate-input w3-border w3-round-large" style="width: 30%" required pattern="[0-9a-zA-Z]{5,10}"/>
    <br/>​
        ${errorLoginPassMessage}
        <br/>
        ${wrongAction}
        <br/>
        ${nullPage}
        <br/>
        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=login><fmt:message key="label.login"/></button>
</form><hr/>
</div>
    <div style="text-align: center;">

        <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=gotoregistration'"><fmt:message key="label.registration"/></button>
</div>
</body></html>
