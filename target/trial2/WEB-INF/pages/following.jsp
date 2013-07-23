<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<body>
<p>${username} following:</p>
<table>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><a href="http://localhost:8080/${item}"><c:out value="${item}" /></a></td>
        </tr>
    </c:forEach>
</table>
 </body>
</html>