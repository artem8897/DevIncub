<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.registration"/></title></head>
<div class="w3-container w3-center">

    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/user_navigation.jsp"/>

    <div style="text-align: center;">

        <body>
        <form name="AddUserInformation" method="POST" action="controller"/>
        <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">
        <input type="hidden" name="user_id" value="${user_id}">

        <select name="status">
            <c:forEach var="status" items="${status}">
                <option value="${status.key}"/> ${status.value}
            </c:forEach>
        </select>

        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=update_pay_status ><fmt:message key="label.change_user_status"/></button>
        </form>
        ${info}
        <hr/>

        <c:import url="/jsp/footer.jsp"/>
        </body>
    </div>
</div>
</html>