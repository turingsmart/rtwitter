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
    <script src="/static/js/jquery.js"></script>
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <%--<script src="http://code.jquery.com/jquery.js"></script>--%>
    <script src="/static/js/bootstrap.min.js"></script>

    <%--Maverick--%>

    <%--<script src="jquery-1.7.2.min.js"></script>--%>
    <!-- noty -->
    <script type="text/javascript" src="/static/js/noty/jquery.noty.js"></script>

    <!-- layouts -->
    <script type="text/javascript" src="/static/js/noty/layouts/bottom.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/bottomCenter.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/bottomLeft.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/bottomRight.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/center.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/centerLeft.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/centerRight.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/inline.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/top.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/topCenter.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/topLeft.js"></script>
    <script type="text/javascript" src="/static/js/noty/layouts/topRight.js"></script>

    <!-- themes -->
    <script type="text/javascript" src="/static/js/noty/themes/default.js"></script>

    <script src="/static/js/homepageFunctions.js"></script>
    <script src="/static/js/followingpageFunctions.js"></script>
    <script src="/static/js/followerspageFunctions.js"></script>
    <%--<script type="text/javascript">$(document).ready(function(){console.log( "ready!" );refreshTweetsAjax("${username}");});</script>--%>

    <script type="text/javascript">




        function loadfollowing()
        {
            console.log("loading following");
            refreshFollowingAjax("${username}");
        };

        function loadfollowers()
        {
            console.log("loading followers");
            refreshFollowersAjax("${username}");
        };
    </script>

    <%--<script type="text/javascript"> $('tweettext').autoResize();       </script>--%>
    <title>${username}</title>
    <style type="text/css">
        .bigtextarea {
            width:600px;
            padding: 3px;
        }
        .movable{
            position:fixed;
            left:100px;
            top:350px;
            text-shadow: 4px 4px 4px #000000;
        }
    </style>

</head>
<body background="http://www.backgroundlabs.com/files/perforated-metal-seamless-pattern-1400.png">
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
                    <li class="offset5">

                        <form method="post" action="/home/search">
                            <input type="text" style="width: 250" id="searchString" name="searchString" placeholder="search tweets, people"/>
                            <input type="submit" value="search" >
                        </form>
                    </li>
                    <li class="">
                        <a href="./home">Home</a>
                    </li>

                    <li class="">
                        <a href="./${username}/timeline">timeline</a>
                    </li>
                    <li class="">
                        <a href="/auth/logout">logout</a>
                    <li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br><br>
<div>
    <ul class="nav nav-pills nav-stacked  movable " style="width: 100">

        <li ><a href="javascript:loadfollowers()"> Followers</a>  </li>

        <li ><a href="javascript:loadfollowing()"> Following</a>  </li>
    </ul>

    <table class="table" style="background-color:#3e3e3e" width="100%">

        <tr>
            <td align="left"> <img src="http://t3.gstatic.com/images?q=tbn:ANd9GcTO0MERk_biUJ8wXviLuO9sOw6uJWxvZJiDaZb44oxKCwWcmw0u" TITLE="${username}" class="img-polaroid" width=100 height=100>
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

            <td><textarea class="bigtextarea" rows="5"  id="tweettext" name="tweettext" maxlength="140" placeholder="write something . . . "/></textarea> </td>

        </tr>
    </table>
    <div class=" offset5">
        <input type="submit" value="tweet" onsubmit=/>
    </div>
</form>
<div id="tweetBox" class="alert alert-success offset3 "></div>
<script type="text/javascript">
    $(document).ready(function() {
        console.log( "ready!" );
        refreshTweetsAjax("${username}");
    });

</script>
</body>
</html>

