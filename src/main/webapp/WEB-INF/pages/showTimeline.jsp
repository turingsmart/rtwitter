<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><%--
  Created by IntelliJ IDEA.
  User: maverick
  Date: 19/7/13
  Time: 1:54 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<html>
<head>
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        var initialPath = "/";
        function changeFollowingStatus(id){
            var fullPath = initialPath.concat(id).concat("/changeFollowingStatus")
            $.ajax({
                url: fullPath,
                cache:false,
                success:function(data){
                    changeImage(id)
                }
            });
        }

        function changeImage(id) {

            if (document.getElementById(id).src == "http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg")
            {
                document.getElementById(id).src = "http://www.almostsavvy.com/wp-content/uploads/2009/07/unfollow-300x86.png";
            }
            else
            {
                document.getElementById(id).src = "http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg";
            }
        }
    </script>
    <style type="text/css">
        .bigtextarea {
            width:1000px;
            padding: 3px;
            border: none;
        }
    </style>
    <title>${username}</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
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
                        <a href="/home">Home</a>
                    </li>

                    <li class="">
                        <a href="/${loggedInUser}/timeline">timeline</a>
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
                    <h2>  ${username}</h2></font>
            </td>
            <td>
            </td>
            <td align="top">
                <c:if test="${result==2}">
                    <img alt="" src="http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg"
                         style="height: 85px; width: 198px" id="${username}" onclick="changeFollowingStatus(id)"  />
                    <h2>${username}</h2>
                </c:if>
                <c:if test="${result==1}">
                    <img alt="" src="http://www.almostsavvy.com/wp-content/uploads/2009/07/unfollow-300x86.png"
                         style="height: 85px; width: 198px" id="${username}" onclick="changeFollowingStatus(id)"/>
                    <h2>${username}</h2>
                </c:if>
            </td>
        </tr>
    </table>
    <c:if test="${result==0}">
    <form method="post" action="">
        <table>
            <tr>

                <td><textarea class="bigtextarea" rows="3"  width ="80%" id="tweettext" name="tweettext" maxlength="140"/></textarea> </td>

            </tr>
        </table>
        <input type="submit" value="Submit" onsubmit=/>

    </form>
    </c:if>




    <br><h2> ${message}</h2><br>


    <c:forEach items="${list}" var="item">
    <br>
    <a href="/${item.username}"><c:out value="${item.username}" /></a>
    <br>
        <c:out value="${item.tweettext}"/>
    <br>

    </c:forEach>

</body>
</html>

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

<%--<img src="http://t3.gstatic.com/images?q=tbn:ANd9GcTw3d_AuZa3EEzi1MNYTNz1caF62yFFCfKtbxTPuc-pfSsSnN-8" alt=${username}, width="200" height="200" >--%>

<%--<p>--%>
    <%--<img alt="" src="http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg"--%>
         <%--style="height: 85px; width: 198px" id="imgClickAndChange" onclick="changeImage()"  />--%>
<%--</p>--%>

<%--<script language="javascript">--%>
    <%--function changeImage() {--%>

        <%--if (document.getElementById("imgClickAndChange").src == "http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg")--%>
        <%--{--%>
            <%--document.getElementById("imgClickAndChange").src = "http://www.almostsavvy.com/wp-content/uploads/2009/07/unfollow-300x86.png";--%>
        <%--}--%>
        <%--else--%>
        <%--{--%>
            <%--document.getElementById("imgClickAndChange").src = "http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg";--%>
        <%--}--%>
    <%--}--%>
<%--</script>--%>

<%--<br><h2> ${message}</h2><br>--%>


<%--<c:forEach items="${list}" var="item">--%>
    <%--<br>--%>
    <%--<a href="/${item.username}"><c:out value="${item.username}" /></a>--%>
    <%--<br>--%>
    <%--<c:out value="${item.tweettext}"/>--%>
    <%--<br>--%>

<%--</c:forEach>--%>

<%--</body>--%>
<%--</html>--%>
<%--<html>--%>
<%--<head>--%>
    <%--<title></title>--%>

    <%--<script type="text/javascript"--%>
            <%--src="http://code.jquery.com/jquery-1.10.1.min.js"></script>--%>
    <%--<script type="text/javascript">--%>
        <%--function refreshTweetsAjax() {--%>
            <%--var tweetUrl = "${username}";--%>
            <%--var ext = "/newTweets"--%>
            <%--$.ajax({--%>
                <%--URL:tweetUrl.concat(ext),--%>
                <%--success:function(data){--%>
                    <%--$('#tweets').html(data);--%>
                <%--}--%>
            <%--});--%>
        <%--}--%>
    <%--</script>--%>

    <%--<script type="text/javascript">--%>
        <%--intervalId = setInterval(refreshTweetsAjax, 3000);--%>
    <%--</script>--%>
<%--</head>--%>
<%--<body>--%>
<%--<h1>${message}</h1>--%>
<%--<div id='tweets'></div>--%>
<%--<p>--------------------------------------------------</p>--%>
<%--<table>--%>
    <%--<c:forEach items="${list}" var="item">--%>
        <%--<tr>--%>
            <%--<td><c:out value="${item.tweettext}" /></td>--%>
        <%--</tr>--%>
    <%--</c:forEach>--%>
<%--</table>--%>
<%--</body>--%>
<%--</html>--%>