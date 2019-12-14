<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE>
<html>
<head><title>Index</title></head>
<body>
<fmt:setLocale value="ru_RU" scope="session" />
Lifecycle: ${lifecycle}
<jsp:forward page="/jsp/login.jsp"/>
</body></html>
