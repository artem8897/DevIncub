<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head><title>Footer</title></head>
<body style ="text-align: center;">
<hr/>
<%--<div class="w3-animate-zoom w3-hide-small">--%>
    <a href='controller?command=logout'><fmt:message key="label.logout"/></a>
   <br/>
    <fmt:message key="footer.copyright"/>
</body></html>