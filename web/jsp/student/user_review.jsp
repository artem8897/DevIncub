<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setLocale value="${local}" scope="session" />
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setBundle basename="local" />
<!DOCTYPE>
<html><head><title><fmt:message key="label.registration"/></title>
    <script>
    function validate() {

        var val = document.getElementById('textArea_id').value;
        var form = document.getElementById('form_id');

        if (val.length < 100 && val.length > 5) {
            form.submit();
        }else{
            alert("5-100!");
        }
    }
</script>
</head>
<body>
<c:import url="/jsp/header.jsp"/>
<c:import url="/jsp/user_navigation.jsp"/>
<div style="text-align: center">
<form name="AddUserInformation" id="form_id" method="POST" action="controller"/>
<input type="hidden" name="redirect" value="controller?command=student_acc">
<input type="hidden" name="command" value=createuserreview />

</br><fmt:message key="label.rate"/> <br/>
<%--<input type="text" name="rate"  value="${rate}" required pattern = "[0-9]{1}"/>--%>
    <link rel="stylesheet" type="text/css" href="styles/star.css">
    <div class="rate" style="align-content: center">
        <input type="radio" id="star5" name="rate" value=5 />
        <label for="star5" title="text">5</label>
        <input type="radio" id="star4" name="rate" value=4 />
        <label for="star4" title="text">4</label>
        <input type="radio" id="star3" name="rate" value=3 />
        <label for="star3" title="text">3</label>
        <input type="radio" id="star2" name="rate" value=2 />
        <label for="star2" title="text">2</label>
        <input type="radio" id="star1" name="rate" value=1 />
        <label for="star1" title="text">1</label>
    </div>
<br/><fmt:message key="label.review"/><br/>

<textarea id="textArea_id" rows="10" cols="40" name="review" value=""></textarea>
<br/>
<select name="trainer_id">
    <c:forEach var="item" items="${trainers}">
        <option value="${item.key}"/> ${item.value.trainerName}
    </c:forEach>
</select>
<br/>
${info}
<br/>
<button class="w3-btn w3-hover-light-blue w3-round-large" onclick="validate()"><fmt:message key="label.write_review"/></button>
</form><hr/>
<a href="controller?command=logout"><fmt:message key="label.logout"/></a>
</div>
</body></html>
