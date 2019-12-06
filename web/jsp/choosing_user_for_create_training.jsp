<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<head>
    <meta charset="UTF-8">
    <title>Countries</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css">
</head>
<c:import url="/jsp/header.jsp"/>
<body class="m-3">


<table class="table table-striped table-bordered table-sm">
    <tr>
        <th><fmt:message key="label.name"/></th>
        <th><fmt:message key="label.second_name"/></th>
        <th><fmt:message key="label.weight"/></th>
        <th><fmt:message key="label.height"/></th>
    </tr>

    <c:forEach items="${personal_information}" var="user">
        <tr>
            <td>${user.value.name}</td>
            <td>${user.value.secondName}</td>
            <td>${user.value.weight}</td>
            <td>${user.value.height}</td>
            <br/>
            <c:set var = "type" value = "${type}"/>

            <td><c:choose>
                <c:when test="${type == 'diet'}">
                    <form name="SetDiet" method="POST" action="controller">
                        <input type="hidden" name="command" value="emptydiet" />
                        <input type="hidden" name="user_id" value="${user.key}">
                        <button class="w3-btn w3-hover-green w3-round-large" name="command" value="emptydiet"><fmt:message key="label.set_diet"/></button>
                    </form>
                </c:when>
                <c:when test="${type == 'training'}">
                    <form name="SetTraining" method="POST" action="controller">
                        <input type="hidden" name="command" value="empty_training_page" />
                        <input type="hidden" name="user_id" value="${user.key}">
                        <button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value="empty_training_page"><fmt:message key="label.set_training"/></button>
                    </form>
                </c:when>
            </c:choose></form><hr/>
            </td>
            <br/>
        </tr>
    </c:forEach>
</table>


<nav aria-label="Navigation for trainer">
    <ul class="pagination">
        <c:if test="${currentPage != 0}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=CHOOSE_USER_FOR_CREATING_DATA&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}&type=${type}">Previous</a>
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
                                             href="controller?command=CHOOSE_USER_FOR_CREATING_DATA&recordsPerPage=${recordsPerPage}&currentPage=${i}&type=${type}">${i}</a>
                    </li>
                </c:otherwise>
            </c:choose>
        </c:forEach>

        <c:if test="${currentPage lt noOfPages}">
            <li class="page-item"><a class="page-link"
                                     href="controller?command=CHOOSE_USER_FOR_CREATING_DATA&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}&type=${type}">Next</a>
            </li>
        </c:if>
    </ul>
</nav>

<script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

</form>
</div>
<a href="controller?command=logout"><fmt:message key="label.logout"/></a>
</body></html>
