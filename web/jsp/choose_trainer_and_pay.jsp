<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html>
<head>
    <c:import url="/jsp/header.jsp"/>

    <form name="Choose_trainer_and_pay" method="POST" action="controller"/>
    <input type="hidden" name="command" value=GO_TO_PAY />

        <div style="text-align: center;">
        <h2>

    </br><fmt:message key="label.training_amount"/><br/>

    <select name="amount of trainings">
        <option value="8"/>8
        <option value="16"/>16
        <option value="32"/>32
        <option value="64"/>64
    </select>
    <br/>
    ${wrongregistration}
    <br/>
    </h2>
    <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=value="GO_TO_PAY" ><fmt:message key="label.calculate_price"/></button>
    </form><hr/></head>
        </div>
<body>

</body>
</html>
