<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>All trainers</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<body class="m-3">
<div class="w3-center">
<form name="Show review" method="POST" action="controller">

    <input type="hidden" name="command" value="show_trainers_reviews" />

<div class="row col-md-6">
    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="label.name" /></th>
            <th><fmt:message key="label.work_experience" /></th>
            <th><fmt:message key="label.training_type"/> </th>
        </tr>

        <c:forEach items="${trainers}" var="trainer">
            <tr>
                <td>${trainer.trainerName}</td>
                <td>${trainer.workExperience}</td>
                <td>${trainer.trainingType}</td>
                <td><button class="w3-btn w3-hover-border-blue w3-round-large" name = "trainer_id" value=${trainer.id} ><fmt:message key="label.display_reviews"/> </button></td>
            </tr>
        </c:forEach>
    </table>
</div>
</form>

<nav aria-label="Navigation for trainer">
    <ul class="pagination">
        <c:if test="${currentPage != 0}">
            <li class="page-item">
                <form name="previous" method="POST" action="controller">
                    <input type="hidden" name="command" value="show_all_trainers" />
                    <input type="hidden" name="currentPage" value="${currentPage-1}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <button class="page-link" name="command" value="show_all_trainers">Previous</button>
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
                            <input type="hidden" name="command" value="show_all_trainers" />
                            <input type="hidden" name="currentPage" value="${i}">
                            <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                            <button class="page-link" name="command" value="show_all_trainers">${i}</button>
                        </form>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item">
                <form name="next" method="POST" action="controller">
                    <input type="hidden" name="command" value="show_all_trainers" />
                    <input type="hidden" name="currentPage" value="${currentPage+1}">
                    <input type="hidden" name="recordsPerPage" value="${recordsPerPage}">
                    <button class="page-link" name="command" value="show_all_trainers">Next</button>
                </form>
            </li>
        </c:if>
    </ul>
</nav>
</div>
<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>
<c:import url="/jsp/footer.jsp"/>
</body>
</html>
