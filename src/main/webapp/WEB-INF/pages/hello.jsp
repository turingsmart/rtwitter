<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%--
  Created by IntelliJ IDEA.
  User: maverick
  Date: 19/7/13
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${username}</title>
</head>
<body>

<c:forEach items="${list}" var="item">
    <br>
    <a href="http://localhost:8080/${item.username}"><c:out value="${item.username}" /></a>
    <br>
    <c:out value="${item.tweettext}"/>
    <br>

</c:forEach>

</body>
</html>