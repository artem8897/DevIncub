<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE html>
<html>
<head><title><fmt:message key="label.training_type"/> </title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<body class="m3">
<div class="row col-md-6">
    <form name="Choose Training Form" method="POST" action="controller">

    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="label.date"/></th>
            <th><fmt:message key="label.personality"/></th>
            <th><fmt:message key="label.training_type"/></th>
        </tr>

    <c:forEach var="training" items="${training}">

        <tr>
            <td>${training.value.date}</td>
            <td>${training.value.personality}</td>
            <td>${training.value.trainingType}</td>

            <input type="hidden" name="command" value="training_page"/>
            <td><button class="w3-btn w3-hover-light-blue w3-round-large" name="training" value=${training.key} ><fmt:message key="label.update_training"/></button><td>

        </tr>

    </c:forEach>
</table>
    </form>
</div>
<nav aria-label="Navigation for trainer">
    <ul class="pagination">
        <c:if test="${currentPage != 0}">
            <li class="page-item">
                <form name="previous" method="POST" action="controller">
                    <input type="hidden" name="command" value="all_users_training_page" />
                    <input type="hidden" name="user_id" value="${user_id}"/>
                    <input type="hidden" name="currentPage" value="${currentPage-1}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <button class="page-link" name="command" value="all_users_training_page">Previous</button>
                </form>
            </li>
        </c:if>

        <c:forEach begin="0" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">

                    <li class="page-item active"><a class="page-link">
                            ${i} <span class="sr-only">(current)</span></a>
                    </li>
                </c:when>
                <c:otherwise>
                    <li class="page-item">
                        <form name="this" method="POST" action="controller">
                            <input type="hidden" name="command" value="all_users_training_page" />
                            <input type="hidden" name="user_id" value="${user_id}"/>
                            <input type="hidden" name="currentPage" value="${i}">
                            <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                            <button class="page-link" name="command" value="all_users_training_page">${i}</button>
                        </form>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item">
                <form name="next" method="POST" action="controller">
                    <input type="hidden" name="command" value="all_users_training_page" />
                    <input type="hidden" name="user_id" value="${user_id}"/>
                    <input type="hidden" name="currentPage" value="${currentPage+1}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <button class="page-link" name="command" value="all_users_training_page">Next</button>
                </form>
            </li>
        </c:if>
    </ul>
</nav>

    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<c:import url="/jsp/footer.jsp"/>

</body>
</html>
