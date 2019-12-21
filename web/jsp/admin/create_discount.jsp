<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" scope="session" />
<html>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<div style="text-align: center">
<form name="Training" method="POST" action="controller">
</br><fmt:message key="label.discount"/> <br/>
<input type="text" name="discount"  value="" required pattern="[0-9]{1,2}"/>
<br><fmt:message key="label.date"/> <br/>
    <br>
<input type="date" name="date" max="${max}" min="${min}" value="${min}"/>
    <br/>
    <button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value="add_discount"><fmt:message key="label.add_discount"/></button>
<input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">
    <br/>
</form><hr/>
${info}
<br/>
<c:import url="/jsp/footer.jsp"/>
</div>
</body>
</html>