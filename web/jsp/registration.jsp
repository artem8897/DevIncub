<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.registration"/></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
<div style="text-align: center;">

<div class="w3-panel w3-green w3-opacity w3-center">

    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers&currentPage=1&recordsPerPage=5'"><fmt:message key="label.display_trainers"/> </button>

    <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers'">adress </button>
</div>
<form name="RegistrationType" method="POST" action="controller"/>
<input type="hidden" name="command" value=registration />
<input type="hidden" name="redirect" value="controller?command=gotologin">
<input type="hidden" name="students" value="${students}">

    </br><fmt:message key="label.email"/> <br/>

    <input type="email" name="email"  value="${students['email']}" required/>
    <br/><fmt:message key="label.password"/><br/>
    <input type="password" name="password" value="${students['password']}"  required pattern="[0-9a-zA-Z]{5,10}"/>
    <br/><fmt:message key="label.confirmpassword"/><br/>
    <input type="password" name="password2" value="${students['password']}"  required pattern="[0-9a-zA-Z]{5,10}"/>
    <br/><fmt:message key="label.username"/><br/>
    <input type="text" name="username" value="${students['username']}" required pattern="[0-9a-zA-Z_]{5,12}"/>

<br/>
${wrong_fields}
<br/><tr bgcolor="#c8d8f8">
    <td  valign=top colspan=2>

        <fmt:message key="label.choosesex"/>

        <input type="radio" name="sex" value="Male" checked> <fmt:message key="label.male"/>

        <input type="radio" name="sex" value="Female" > <fmt:message key="label.female"/>

    </td>
</tr>
<br/>
${wrongregistration}
<br/>
<button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=registration ><fmt:message key="label.registration"/></button>
</form><hr/>
<a href="controller?command=gotologin"><fmt:message key="label.login"/></a>
<ctg:table-revenue/>
</div>
</body></html>