<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${local}" scope="session" />
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head>
    <meta charset="UTF-8">
    <title>user navigation</title>
    <link rel="stylesheet" href="styles/w3.css">
</head>
<body class="w3-blue"/>
<div class="w3-text-aqua">
</div>
    <div class="w3-bar  w3-padding-large w3-padding-24 w3-center">
        <c:set var = "user" value = "${user_type}" scope="session"/>
                <c:choose>
                    <c:when test="${user.equals('ADMIN')}">
        <table class="table table-striped table-bordered table-sm">

            <tr>
                <td> <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=admin_choose_person&currentPage=0&recordsPerPage=5'"><fmt:message key="label.edit_information"/> </button>  </td>
                <td> <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_discounts'"><fmt:message key="label.show_discounts"/> </button>  </td>
                <td> <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=DISCOUNT'"><fmt:message key="label.discount"/></button> </td>
            </tr>
            <tr>
                <td> <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=ADMIN_EDIT_USER&currentPage=0&recordsPerPage=5'"><fmt:message key="label.change_user_status"/> </button> </td>
                <td> <button class="w3-btn w3-hover-red w3-round-large" onclick="location.href='controller?command=edition_trainer'"><fmt:message key="label.edit_trainer"/> </button> </td>
                <td> <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=GO_TO_PASSWORD'"><fmt:message key="label.change_password"/> </button> </td>
            </tr>
        </table>
                    </c:when>
                    <c:when test="${user.equals('USER')}">
            <table class="table table-striped table-bordered table-sm">

                <tr>
                    <td>  <button class="w3-btn w3-hover-amber w3-round-large" onclick="location.href='controller?command=update_user_information_page'"><fmt:message key="label.edit_my_information"/> </button> </td>
                    <td>  <button class="w3-btn w3-hover-amber w3-round-large" onclick="location.href='controller?command=button_diet_page'" ><fmt:message key="label.update_diet"/> </button> </td>
                    <td>  <button class="w3-btn w3-hover-border-blue-gray w3-round-large" onclick="location.href='controller?command=go_to_choose_trainer'" ><fmt:message key="label.buy_trainings"/> </button> </td>
                    <td>  <button class="w3-btn w3-hover-text-black w3-round-large" onclick="location.href='controller?command=all_users_training_page&currentPage=0&recordsPerPage=5'" ><fmt:message key="label.update_training"/> </button> </td>
                </tr>
                <tr>
                    <td>  <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=empty_review'"><fmt:message key="label.write_review"/> </button> </td>
                    <td>  <button class="w3-btn w3-hover-light-blue w3-round-large" onclick="location.href='controller?command=show_all_trainers&currentPage=0&recordsPerPage=5'"><fmt:message key="label.display_trainers"/> </button> </td>
                    <td>  <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=GO_TO_PASSWORD'"><fmt:message key="label.change_password"/> </button> </td>
                </tr>
            </table>
                    </c:when>
                    <c:when test="${user.equals('TRAINER')}">
                <table class="table table-striped table-bordered table-sm">

                    <tr>
                        <div class="w3-text">
                            <td> <form name="SetDiet" method="GET" action="controller">
                            <input type="hidden" name="command" value="CHOOSE_USER_FOR_CREATING_DATA" />
                            <input type="hidden" name="currentPage" value="1">
                            <input type="hidden" name="type" value="diet">
                            <input type="hidden" name="recordsPerPage" value="5">
                            <button class="w3-btn w3-hover-light-blue w3-round-large" value="CHOOSE_USER_FOR_CREATING_DATA"><fmt:message key="label.set_diet"/></button>
                        </form> </td>
                            <td>  <form name="SetTraining" method="GET" action="controller">
                            <input type="hidden" name="command" value="CHOOSE_USER_FOR_CREATING_DATA" />
                            <input type="hidden" name="currentPage" value="0">
                            <input type="hidden" name="type" value="training">
                            <input type="hidden" name="recordsPerPage" value="5">
                            <button class="w3-btn w3-hover-light-blue w3-round-large" value="CHOOSE_USER_FOR_CREATING_DATA"><fmt:message key="label.set_training"/></button>
                        </form> </td>
                            <td>  <form name="SetTraining" method="GET" action="controller">
                            <input type="hidden" name="command" value="trainer_edition" />
                            <input type="hidden" name="currentPage" value="0">
                            <input type="hidden" name="recordsPerPage" value="5">
                            <button class="w3-btn w3-hover-light-blue w3-round-large" value="trainer_edition"><fmt:message key="label.change_destination"/></button>
                        </form> </td>

                            <td>  <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=TRAINER_PAGE'"><fmt:message key="label.change_user_information"/> </button> </td>

                            <td>  <form name="SetTraining" method="GET" action="controller">
                                <input type="hidden" name="command" value="show_trainers_reviews" />
                                <button class="w3-btn w3-hover-light-blue w3-round-large" value="trainer_edition"><fmt:message key="label.display_reviews"/></button>
                            </form> </td>


                            <td>  <button class="w3-btn w3-hover-green w3-round-large" onclick="location.href='controller?command=GO_TO_PASSWORD'"><fmt:message key="label.change_password"/> </button> </td>
                        </div>
                    </tr>
                </table>

                </c:when>
                </c:choose>
        <hr/>
        </div>

</head>
</html>