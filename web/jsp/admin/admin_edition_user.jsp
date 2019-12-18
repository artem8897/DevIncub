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
<body class="m-3">

<form name="Edit User Form" method="POST" action="controller">
    <input type="hidden" name="redirect" value="controller?command=GO_TO_ADMIN">

<div class="row col-md-6">

    <table class="table table-striped table-bordered table-sm">
        <tr>
            <th><fmt:message key="label.username"/></th>
            <th><fmt:message key="label.email"/></th>
            <th><fmt:message key="label.user_type"/></th>
            <th><fmt:message key="label.status"/> </th>
        </tr>

        <c:forEach items="${personal_information}" var="user">
            <tr>
                <td>${user.value.username}</td>
                <td>${user.value.email}</td>
                <td><select name="user_type">

                    <option value="${user.value.userType}" selected/>${user.value.userType}
                    <option value="user" /><c:out value="user"/>
                    <option value="admin" /><c:out value="admin"/>
                    <option value="trainer" /><c:out value="trainer"/>

                </select></td>
                <td> <select name="status">

                    <option value="${user.value.status}" selected/>${user.value.status}
                    <option value="active" /><c:out value="active"/>
                    <option value="blocked" /><c:out value="blocked"/>
                    <option value="deleted" /><c:out value="deleted"/>

                </select></td>
                <input type="hidden" name="command" value="CHANGE_USER_STATUS"/>

                <td><button button class="w3-btn w3-hover-light-blue w3-round-large" name="user_id" value=${user.key} ><fmt:message key="label.change_user_status"/></button><td>
            </td>
                </form>
            </tr>
        </c:forEach>
    </table>
</div>

<nav aria-label="Navigation for trainer">
    <ul class="pagination">
        <c:if test="${currentPage != 1}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=ADMIN_EDIT_USER&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                             href="controller?command=ADMIN_EDIT_USER&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=ADMIN_EDIT_USER&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
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

