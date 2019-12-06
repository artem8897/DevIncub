<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>students</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<c:import url="/jsp/header.jsp"/>
<body class="m3">
<form name="Choose_user" method="POST" action="controller">

    <input type="hidden" name="command" value="edition_page_command" />

    <div class="row col-md-6">

    <table class="table table-striped table-bordered table-sm">

        <tr>
            <th><fmt:message key="label.name"/></th>
            <th><fmt:message key="label.second_name"/></th>
            <th><fmt:message key="label.training_type"/></th>
        </tr>
            <c:forEach var="item" items="${personal_information}">
        <tr>
            <td> ${item.value.name}</td>
            <td>${item.value.secondName}</td>
            <td><button button class="w3-btn w3-hover-light-blue w3-round-large" name="user_id" value=${item.key} > choose </button><td>
        </tr>
            </c:forEach>
        <hr/>
    </table>
</div>
</form>

<nav aria-label="Navigation for admin">
    <ul class="pagination">
        <c:if test="${currentPage != 0}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=ADMIN_CHOOSE_PERSON&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&user_id=${userId}">Previous</a>
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
                                             href="controller?command=ADMIN_CHOOSE_PERSON&recordsPerPage=${recordsPerPage}&currentPage=${i}&user_id=${userId}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=ADMIN_CHOOSE_PERSON&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&user_id=${userId}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

<a href="controller?command=logout"><fmt:message key="label.logout"/></a>
    </div>
</div>
</body></html>
