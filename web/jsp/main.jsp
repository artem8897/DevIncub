<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<html><head>
<body>
    <c:import url="/jsp/header.jsp"/>
    <div class="w3-panel w3-green w3-opacity w3-center">
               <b>get richest</b>
        <form name="Count" method="GET" action="controller">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=GET_RICHEST>richest</button>
        </form>
                   <b>get count</b>
        <form name="Richest" method="GET" action="controller">
            <button class="w3-btn w3-hover-light-blue w3-round-large"  name="command" value=GET_SUM>count</button>
        </form>

</body></html>
