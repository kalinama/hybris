<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Concert Details</title>
</head>
<div>
    <h1>${concert.name}</h1>
    <h2>${concert.date}</h2>
    <h2>Days before the concert: ${concert.daysUntil}</h2>
    <h3>Duration: ${concert.duration} minutes</h3>
    <h3>Venue: ${concert.venue} - ${concert.type}</h3>

    <h4>${concert.description}</h4>
    <h4>Song list:</h4>
    <c:forEach var="song" items="${concert.songs}" varStatus="loopStatus">
        <li>${song}</li>
    </c:forEach>

</div>
</html>
