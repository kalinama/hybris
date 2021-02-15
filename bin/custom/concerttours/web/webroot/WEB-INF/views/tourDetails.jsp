<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tour Details</title>
</head>
<div>
    <h1>${tour.tourName} </h1>
    <h2>${tour.description}</h2>
    <h3>Producer: ${tour.producer}</h3>

    <c:forEach items="${tour.concerts}" var="concert">
        <a href="${pageContext.request.contextPath}/concerts/${concert.id}"> ${concert.venue}</a>
        ${concert.type}
        ${concert.date}
        <br>
    </c:forEach>

</div>
</html>
