<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Band Details</title>
</head>
<div>
    <h1>${band.name} </h1>
    <h2>${band.description}</h2>
    <img src="${band.imageUrl}">
    <h2>Players: </h2>

    <c:forEach items="${band.players}" var="player">
        ${player}
        <br>
    </c:forEach>

</div>
</html>
