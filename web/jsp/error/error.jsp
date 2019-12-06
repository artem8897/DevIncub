<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<html>
<head><title>Error Page</title></head>
<body>
<ul>
    <li>Exception type: <c:out value="${requestScope['javax.servlet.error.exception_type']}" /></li>
    <li>Exception message: <c:out value="${requestScope['javax.servlet.error.message']}" /></li>
    <li>Request URI: <c:out value="${requestScope['javax.servlet.error.request_uri']}" /></li>
    <li>Servlet name: <c:out value="${requestScope['javax.servlet.error.servlet_name']}" /></li>
    <li>Status code: <c:out value="${requestScope['javax.servlet.error.status_code']}" /></li>

    <c:set var="exception" value="${requestScope['javax.servlet.error.by.bsu.finalproject.exception']}"/>

    <c:if test="${exception != null}">
        <h4>${exception}</h4>
        <c:forEach var="stackTraceElem" items="${exception.stackTrace}">
            <c:out value="${stackTraceElem}"/><br/>
        </c:forEach>
    </c:if>
</ul>
</body>
</html>