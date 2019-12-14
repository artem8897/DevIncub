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
<%--          todo reddddddddddddddddddddddddddddddddddddddddddddddddddd

                        сделать ридеректом!!!

 --%>
                <td><button button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=button_diet_page&user_id=${user.key}'" ><fmt:message key="label.update_diet"/> </button></td>
                <td><button button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=empty_review&user_id=${user.key}'"><fmt:message key="label.review"/></button></td>
                <td><button button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=all_users_training_page&user_id=${user.key}&recordsPerPage=5&currentPage=0'" ><fmt:message key="label.update_training"/> </button></td>

                <td><button class="w3-btn w3-hover-border-blue w3-round-large" onclick="location.href='controller?command=show_trainers_reviews&trainer_id=${user.key}'" ><fmt:message key="label.display_reviews"/> </button></td>
            </tr>
        </c:forEach>
    </table>

<%--    <form name="loginForm" method="POST" action="controller">--%>

<%--        <select name="user_id">--%>
<%--            <c:forEach var="item" items="${alluser}">--%>
<%--                <option value="${item.key}"/> ${item.value}--%>
<%--            </c:forEach>--%>
<%--        </select>--%>
<%--        <hr/>--%>

<%--        <div class="w3-container w3-center">--%>
<%--            <div class="w3-bar w3-padding-large w3-padding-24">--%>

<%--                <button button class="w3-btn w3-hover-green w3-round-large" name="command" value=button_diet_page >diet</button>--%>
<%--                <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=empty_review >review</button>--%>
<%--                <button button class="w3-btn w3-hover-light-blue w3-round-large" name="command" value=all_users_training_page >training </button>--%>
<%--                <input type="submit" name="command" value=showalluserscommand />--%>
<%--                <input type="submit" name="command" value=all_personal_information />--%>
<%--            </div>--%>
<%--        </div>--%>

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
