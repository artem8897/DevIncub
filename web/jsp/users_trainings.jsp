<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Countries</title>
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
            <td><button button class="w3-btn w3-hover-light-blue w3-round-large" name="training" value=${training.key} ><fmt:message key="label.update_training"/></button><td>

        </tr>

    </c:forEach>
</table>
    </form>
</div>

    <nav aria-label="Navigation for trainer">
        <ul class="pagination">
            <c:if test="${currentPage != 0}">
                <li class="page-item"><a class="page-link"
                                         href="controller?command=all_users_training_page&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&user_id=${userId}">Previous</a>
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
                        <li class="page-item"><a class="page-link"
                                                 href="controller?command=all_users_training_page&recordsPerPage=${recordsPerPage}&currentPage=${i}&user_id=${userId}">${i}</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </c:forEach>

            <c:if test="${currentPage lt noOfPages}">
                <li class="page-item"><a class="page-link"
                                         href="controller?command=all_users_training_page&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&user_id=${userId}">Next</a>
                </li>
            </c:if>
        </ul>
    </nav>

    <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</body>
</head>

</html>
