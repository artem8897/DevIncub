<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="ru_RU
" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.title" /></title></head>
<body>
<h3><fmt:message key="label.welcome" /></h3>
<ul>
    <c:forEach items="${alluser}" var="i">
        <li><c:out value="${i}"/></li>
    </c:forEach>
</ul>
<select name="department">
    <c:forEach var="item" items="${alluser}">
        <option value="${item.key}" ${item.key == selectedDept ? 'selected="selected"' : ''}>${item.value}</option>
    </c:forEach>
</select>
<hr>
<hr/>
