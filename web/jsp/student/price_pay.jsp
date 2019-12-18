<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html>
<head>
</head>
<body>
<c:import url="/jsp/header.jsp"/>
<form name="Choose_trainer_and_pay" method="POST" action="controller"/>
    <input type="hidden" name="command" value=PAY />
        <input type="hidden" name="price" value="${price}"/>
        <input type="hidden" name="redirect" value="controller?command=student_acc">
        <input type="hidden" name="training_amount" value="${training_amount}"/>
    <div style="text-align: center;">
        <h2>
            <c:out value="${price}"/>
            <fmt:message key="label.for"/>
            <c:out value="${training_amount}"/>
            <br/>
            ${wrongregistration}
            <br/>

            <select name="trainer_id">
                <c:forEach var="item" items="${trainers}">
                    <option value="${item.key}"/> ${item.value.trainerName},${item.value.trainingType}
                </c:forEach>
            </select>

            <input type="password" name="card" value="" required pattern="[0-9]{10}"/>
        </h2>
        <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value="PAY" ><fmt:message key="label.pay"/></button>
        </form><hr/>
    </div>

<c:import url="/jsp/footer.jsp"/>
</body>
</html>