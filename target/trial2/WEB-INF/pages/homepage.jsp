<%--<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>&lt;%&ndash;--%>
  <%--Created by IntelliJ IDEA.--%>
  <%--User: maverick--%>
  <%--Date: 19/7/13--%>
  <%--Time: 1:54 PM--%>
  <%--To change this template use File | Settings | File Templates.--%>
<%--&ndash;%&gt;--%>
<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title>${username}</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<br><h2> ${message}</h2><br>--%>

<%--<c:forEach items="${list}" var="item">--%>
    <%--<br>--%>
    <%--Username : <a href="/${item.username}/timeline"><c:out value="${item.username}" /></a>--%>
    <%--<br>--%>
    <%--tweet : <c:out value="${item.tweettext}"/>--%>
    <%--<br>--%>
    <%--Tweet posted at <c:out value="${item.timestamp}"/>--%>
    <%--<br>--%>

<%--</c:forEach>--%>

<%--</body>--%>
<%--</html>--%>

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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <title>${username}</title>
    <style type="text/css">
        .bigtextarea {
            width:1000px;
            padding: 3px;
            border: none;
        }
    </style>

</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="">
                        <a href="./home">Home</a>
                    </li>

                    <li class="">
                        <a href="./${username}/timeline">timeline</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br><br>
<div>
    <table class="table" style="background-color:#3e3e3e" width="100%">

        <tr>
            <td align="left"> <img src="http://citizenmed.files.wordpress.com/2011/08/user-icon1-e1313533162370.jpg?w=150&h=138" class="img-rounded" width=100 height=100>
            </td>
            <td align="center">
                <font color="#fff3ee">
                    <h2>  ${message}</h2></font>
            </td>
            <td>
            </td>
    </table>
</div>
<form method="post" action="">
    <table>
        <tr>

            <td><textarea class="bigtextarea" rows="4"  width ="60%" id="tweettext" name="tweettext" maxlength="140"/></textarea> </td>

        </tr>
    </table>
    <input type="submit" value="Submit" onsubmit=/>

</form>

<c:forEach items="${list}" var="item">

    <div style="display: inline-block; float:left">

        <a href="/${item.username}/timeline"><c:out value="${item.username}" /></a>
    </div>
    <div style="display: inline-block ; float:right">
        <div>
            tweet : <c:out value="${item.tweettext}"/>
        </div>
        <div >
            Tweet posted at <c:out value="${item.timestamp}"/>
        </div>
    </div>
    <div class="inline"></div>

    </div>
    <br>
    <br>
</c:forEach>

</body>
</html>