<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html>
<head>
    <c:import url="/jsp/header.jsp"/>
<body>
<div class="w3-panel w3-green w3-opacity w3-center">
<table style="mso-table-anchor-horizontal: column">
    <c:forEach var="item" items="${review}">
        <tr>
            <ul>
                <td><c:out value="${item.value.review}"/></td>
                <td><c:out value="${item.value.rate}"/></td>
                <c:set var = "user_type" value = "${user_type}"/>

                <br/>
<%--                <c:if test="${user_type.equals('ADMIN')}">--%>
<%--                    <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">--%>
<%--                    <button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=delete_review ><fmt:message key="label.registration"/></button>--%>
<%--                </c:if>--%>
            </ul>
        </tr>
    </c:forEach>
</table>
</div>
</body>
</html>
