<%--
  Created by IntelliJ IDEA.
  User: karthik
  Date: 22/7/13
  Time: 6:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %><html>
<head>
    <title>followers</title>
</head>
<body>


<p>${username} followed by:</p>
<table>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><a href="/${item}/timeline"><c:out value="${item}" /></a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>