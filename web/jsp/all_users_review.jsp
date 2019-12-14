<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html>
<head>
    <title>users review</title>
</head>
<body>
<form name="Choose_users_review" method="POST" action="controller"/>
<input type="hidden" name="command" value=update_training_review />
<select name="training">
    <c:forEach var="item" items="${review}">
        <option value="${item.key}"/> ${item.value}
        <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" >change review </button>
    </c:forEach>
</select>
<c:import url="/jsp/footer.jsp"/>
</body>
</html>

