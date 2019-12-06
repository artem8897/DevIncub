<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html>
<head><title>Footer</title></head>
<body class="w3-image">
<hr/>
<div style="text-align: center;">
<div class="w3-animate-zoom w3-hide-small">
    <button class="w3-btn w3-hover-border-blue-gray w3-round-large" onclick="Location.href='controller?command=logout'"><fmt:message key="label.logout"/></button>
    <ctg:table-revenue/>
</div>${requestScope.admin}
</body></html>