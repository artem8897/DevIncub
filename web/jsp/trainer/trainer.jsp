<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="ctg" uri="/WEB-INF/tld/custom.tld" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.trainer_page" /></title></head>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<fmt:message key="label.trainer_acc"/>
<div class="w3-hover-text-brown"><ctg:table-revenue/></div>
<hr/>
${info}
<hr/>
<c:import url="/jsp/footer.jsp"/>
</body></html>