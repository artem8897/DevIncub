<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<html><head><title><fmt:message key="label.registration"/></title></head>
<div class="w3-container w3-center">

    <c:import url="/jsp/header.jsp"/>
    <c:import url="/jsp/user_navigation.jsp"/>

        <div style="text-align: center;">

    <body>
    <form name="AddUserInformation" method="POST" action="controller"/>
    <c:set var="students" value="${students}"/>
    </br><fmt:message key="label.name"/> <br/>
    <input type="text" name="name" value="${students['name']}" required pattern = "(([А-Яа-я]{3,14})|([A-Za-z]{2,20}))"/>
    <br/><fmt:message key="label.second_name"/><br/>
    <input type="text" name="second_name" value="${students['second_name']}" required pattern = "(([а-яА-я]{5,14})|([a-zA-Z]{2,20}))"/>
    <br/><fmt:message key="label.weight"/><br/>
    <input type="text" name="weight" value="${students['weight']}" required pattern="[0-9]{2,3}"/>
    <br/><fmt:message key="label.height"/><br/>
    <input type="text" name="height" value="${students['height']}" required pattern="[0-9]{3}"/>
    <br/>

    <tr bgcolor="#c8d8f8">
    <td  valign=top colspan=2>

        <fmt:message key="label.choosesex"/>

        <input type="radio" name="sex" value="Male" checked> <fmt:message key="label.male"/>

        <input type="radio" name="sex" value="Female" > <fmt:message key="label.female"/>

    </td>
</tr>
    <input type="hidden" name="user_id" value="${user_id}"/>
    <br/>
${wrongregistration}
<br/>
    <br/>
    <c:set var = "move" value = "${mov}"/>

    <br/>
    <c:choose>
        <c:when test="${move == 'ADD'}">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=addinformationcommand ><fmt:message key="label.create_user_dat"/></button>
        </c:when>
        <c:when test="${move == 'UPDATE'}">
            <button button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=user_information_update><fmt:message key="label.edit_student"/></button>
        </c:when>
    </c:choose>
    <br/>


    <c:set var = "user_type" value = "${user_type}"/>

    <br/>
    <c:choose>
        <c:when test="${user_type.equals('ADMIN')}">
            <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">
        </c:when>
        <c:when test="${user_type.equals('USER')}">
            <input type="hidden" name="redirect" value="controller?command=student_acc">
        </c:when>
    </c:choose></form><hr/>


    </form><hr/>

    <c:import url="/jsp/footer.jsp"/>
</body>
        </div>
</div>
</html>