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

                <td> <form name="changeTraining" method="POST" action="controller">
                    <input type="hidden" name="command" value="all_users_training_page" />
                    <input type="hidden" name="currentPage" value="0">
                    <input type="hidden" name="recordsPerPage" value="5">                    <input type="hidden" name="user_id" value="${user.key}" />
                    <button class="w3-btn w3-hover-light-blue w3-round-large" value="all_users_training_page"><fmt:message key="label.update_training"/></button>
                    </form>
                </td>

                <td> <form name="changeDiet" method="POST" action="controller">
                    <input type="hidden" name="command" value="button_diet_page" />
                    <input type="hidden" name="user_id" value="${user.key}" />
                    <button class="w3-btn w3-hover-light-blue w3-round-large" value="button_diet_page"><fmt:message key="label.update_diet"/></button>
                     </form>
                </td>
            </tr>
        </c:forEach>
    </table>

        <nav aria-label="Navigation for trainer">
            <ul class="pagination">
                <c:if test="${currentPage != 0}">
                    <li class="page-item"><a class="page-link"
                                             href="controller?command=trainer_edition&recordsPerPage=${recordsPerPage}&currentPage=${currentPage-1}">Previous</a>
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
                                                     href="controller?command=trainer_edition&recordsPerPage=${recordsPerPage}&currentPage=${i}">${i}</a>
                            </li>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>

                <c:if test="${currentPage lt noOfPages}">
                    <li class="page-item"><a class="page-link"
                                             href="controller?command=trainer_edition&recordsPerPage=${recordsPerPage}&currentPage=${currentPage+1}">Next</a>
                    </li>
                </c:if>
            </ul>
        </nav>

        <script src="https://code.jquery.com/jquery-3.1.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"></script>

    </form>
</div>
    <c:import url="/jsp/footer.jsp"/>
</body></html>
