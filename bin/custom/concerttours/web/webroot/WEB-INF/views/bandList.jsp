<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Band List</title>
</head>
<div>
    <h1>Bands</h1>
    <table>
        <tr>
            <td>Image</td>
            <td>Name</td>
            <td>Description</td>
            <td>AlbumsSold</td>
            <td>Genres</td>
            <td>Tours</td>
            <td>Players</td>
        </tr>
        <c:forEach items="${bands}" var="band">
            <tr>
                <td><img src="${band.imageUrl}"/></td>
                <td><a href="${pageContext.request.contextPath}/bands/${band.id}"> ${band.name}</a></td>
                <td>${band.description}</td>
                <td>${band.albumsSold}</td>
                <td>
                    <c:forEach var="genre" items="${band.genres}" varStatus="loopStatus">
                        ${genre}
                        <c:if test="${!loopStatus.last}">,</c:if>
                    </c:forEach></td>
                <td>
                    <c:forEach var="tour" items="${band.tours}" varStatus="loopStatus">

                        <a href="${pageContext.request.contextPath}/tours/${tour.id}">
                                ${tour.tourName}
                            <p>Concerts: ${tour.numberOfConcerts}</p>
                        </a>
                    </c:forEach>
                </td>
                <td>
                    <c:forEach var="player" items="${band.players}" varStatus="loopStatus">
                        ${player}
                        <c:if test="${!loopStatus.last}">,</c:if>
                    </c:forEach>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</html>
