<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html>
<head>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>

    <form name="Choose_trainer_and_pay" method="POST" action="controller"/>
    <input type="hidden" name="command" value=GO_TO_PAY />

        <div style="text-align: center;">
        <h2>

    </br><fmt:message key="label.training_amount"/><br/>

    <select name="training_amount">
        <option value="8"><c:out value="8"/></option>
        <option value="16"><c:out value="16"/></option>
        <option value="32"><c:out value="32"/></option>
        <option value="64"><c:out value="64"/></option>
    </select>
    <br/>
    ${wrongregistration}
    <br/>
    </h2>
    <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=value="GO_TO_PAY" ><fmt:message key="label.calculate_price"/></button>
    </form><hr/></head>
        </div>
<c:import url="/jsp/footer.jsp"/>

</body>
</html>
