<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<html><head><title>
</div><fmt:message key="label.title" /></title></head>

<body class="w3-border-blue-gray">
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<hr/>
<div class="w3-hover-text-brown">user <fmt:message key="label.welcome" /> ${User.username}</div>
<hr/>
${info}
</body>
<c:import url="footer.jsp"/>
</div>
</html>
