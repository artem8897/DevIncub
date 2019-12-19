<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.registration"/></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
<div style="text-align: center;">

<div class="w3-panel w3-green w3-opacity w3-center">

    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers&currentPage=0&recordsPerPage=5'"><fmt:message key="label.display_trainers"/> </button>

</div>
<form name="RegistrationType" method="POST" action="controller"/>
<input type="hidden" name="command" value=registration />
<input type="hidden" name="redirect" value="controller?command=gotologin">
<input type="hidden" name="students" value="${students}">

    </br><fmt:message key="label.email"/> <br/>
    <input type="email" name="email"  value="${students['email']}" required pattern="[A-Za-z0-9]{4,20}@[a-z]{3,7}.[a-z]{2,3}"/>
    </br><label class="invalid-value-label"><c:if test="${ students['email'] eq 'wrong field'}"><fmt:message key="message.wrong_fields"/></c:if></label><br/>
    <br/><fmt:message key="label.password"/><br/>
    <input type="password" name="password" value="${students['password']}"  required pattern="[0-9a-zA-Z]{5,10}"/>
    <br/><fmt:message key="label.confirmpassword"/><br/>
    <input type="password" name="password2" value="${students['password']}"  required pattern="[0-9a-zA-Z]{5,10}"/>
    <br/><fmt:message key="label.username"/><br/>
    <input type="text" name="username" value="${students['username']}" required pattern="[A-Za-z]{1,2}[A-Za-z0-9]{6,8}" />
    <br/><label class="invalid-value-label"><c:if test="${ students['username'] eq 'wrong field'}"><span style="color: darkred"><fmt:message key="label.wrong_username"/></span></c:if></label></br>
    <br/>
${wrong_fields}
${wrongregistration}
<br/>
<button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=registration ><fmt:message key="label.registration"/></button>
</form><hr/>
<a href="controller?command=gotologin"><fmt:message key="label.login"/></a>
</div>
</body></html>