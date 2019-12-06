<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.registration"/></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<form name="AddUserInformation" method="POST" action="controller"/>
<input type="hidden" name="redirect" value="controller?command=student_acc">
<input type="hidden" name="command" value=createuserreview />

</br><fmt:message key="label.rate"/> <br/>
<input type="text" name="rate"  value="${rate}" required pattern = "[0-9]"/>
<br/><fmt:message key="label.review"/><br/>
<textarea rows="10" cols="40" name="review" value="" required pattern = "(([а-яА-яa-zA-Z,.)(]{5,100}))"></textarea>>

<select name="trainer_id">
    <c:forEach var="item" items="${trainers}">
        <option value="${item.key}"/> ${item.value.name}
    </c:forEach>
</select>
<br/>
${wrongregistration}
<br/>
<button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=createuserreview ><fmt:message key="label.write_review"/></button>
</form><hr/>
<a href="controller?command=logout"><fmt:message key="label.logout"/></a>
</body></html>
