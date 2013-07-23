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
    <title></title>
    <script type="text/javascript"
            src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        function refreshTweetsAjax() {
            var tweetUrl = "${username}";
            var ext = "/newTweets"
            $.ajax({
                URL:tweetUrl.concat(ext),
                success:function(data){
                    $('#tweets').html(data);
                }
            });
        }
    </script>

    <script type="text/javascript">
        intervalId = setInterval(refreshTweetsAjax, 3000);
    </script>
</head>
<body>
<div id='tweets'></div>
<p>separator</p>
<table>
    <c:forEach items="${list}" var="item">
        <tr>
            <td><c:out value="${item.tweettext}" /></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>