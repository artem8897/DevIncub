<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.title" /></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>

<div style="text-align: center;">

    <form name="loginForm" method="POST" action="controller">

        <input type="hidden" name="command" value="DELETE_DISCOUNT">
        <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">

        <div class="row col-md-6">

            <table class="table table-striped table-bordered table-sm"><div class="w3-center">

                <c:forEach var="item" items="${discount}">
                <tr>
                    <td>${item.value}</td>
                    <td>${item.key}</td>
                    <td><button class="w3-btn w3-hover-light-blue w3-round-large" name="discount" value = "${item.key}"><fmt:message key="label.delete"/> </button></td>
                </tr>
                </c:forEach>
            </table>
        </div>
    </form>
    <c:import url="/jsp/footer.jsp"/>

</body></html>
