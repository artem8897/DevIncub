<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.title" /></title></head>
<body>

<div style="text-align: center;">
    <c:import url="/jsp/header.jsp"/>

    <form name="loginForm" method="POST" action="controller">

        <input type="hidden" name="user_id" value="${user_id}">
        <input type="hidden" name="recordsPerPage" value=5>
        <input type="hidden" name="currentPage" value=0>
        
        <div class="w3-container w3-center">
        <div class="w3-bar w3-padding-large w3-padding-24">

            <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=update_user_information_page><fmt:message key="label.show_all_user_information"/> </button>
            <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=admin_status><fmt:message key="label.change_user_status"/></button>
            <button button class="w3-btn w3-hover-green w3-round-large" name="command" value=button_diet_page ><fmt:message key="label.update_diet"/> </button>
            <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=empty_review ><fmt:message key="label.review"/> </button>
            <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=all_users_training_page ><fmt:message key="label.update_training"/> </button>


        </div>
        </div>
    </form>
</div>
<c:import url="/jsp/footer.jsp"/>
</body></html>
