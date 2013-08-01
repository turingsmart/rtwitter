<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
    <title> ${username} - following</title>
    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <script type="text/javascript" src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
    <script type="text/javascript">
        var initialPath = "/";

        function changeFollowingStatus(id){
            var fullPath = initialPath.concat(id).concat("/changeFollowingStatus")
            $.ajax({
                url: fullPath,
                cache:false,
                success:function(data){
                    changeImage(id),
                            $('#decode_me').html(data.concat(this.URL));
                }
            });
        }
        function changeImage(id) {

            if (document.getElementById(id).value=="follow")
            {
                //document.getElementById(id).src = "http://www.almostsavvy.com/wp-content/uploads/2009/07/unfollow-300x86.png";


                // <img alt="" src="http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg"
                //  style="height: 85px; width: 198px" id=  />
                document.getElementById(id).value="unfollow";
            }
            else
            {
                //document.getElementById(id).src = "http://us.123rf.com/400wm/400/400/thesupe87/thesupe870910/thesupe87091000053/5710218-follow-arrow-icon-sketched-in-black-in-vector-format.jpg";
                document.getElementById(id).value="follow";
            }
        }
    </script>
</head>
<body background="http://www.backgroundlabs.com/files/perforated-metal-seamless-pattern-1400.png">

<div class="  navbar navbar-inverse navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <div class="nav-collapse collapse">
                <ul class="nav">
                    <li class="offset8">
                        <a href="/home">Home</a>
                    </li>

                    <li class="">
                        <a href="/${loggedinUser}/timeline">timeline</a>
                    </li>
                    <li class="">
                        <a href="/auth/logout">logout</a>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</div>
<br><br>
<div>

    <p>${username} following:</p>
    <div class="offset2" style="width: 65%">
        <c:forEach items="${unfollowList}" var="item">
            <div class="alert alert-success" style="border: none">
                <div style="display: inline ; border:none " >
                    <a href="/${item}/timeline"><c:out value="${item}" /> </a>
                </div>
                <div style="display: inline ; border:none" class="pull-right">
                    <input type="button" id="${item}" name="unfollow" value="unfollow" onclick="changeFollowingStatus(id)"/>
                </div>
                <br>
                <br>
                <div class="alert alert-success" style="border: none; ">

                </div>

            </div>

        </c:forEach>

        <c:forEach items="${followList}" var="item">


            <div class="alert alert-success" style="border: none">
                <div style="display: inline; border:none" >
                    <a href="/${item}/timeline"><c:out value="${item}" /></a>
                </div>
                <c:if test='${item!=loggedinUser}'>
                    <div style="display: inline ; border:none" class="pull-right">

                        <input type="button" id="${item}" name ="follow" value="follow" onclick="changeFollowingStatus(id)"/>

                    </div>
                </c:if>
                <br><br>
                <div class="alert alert-success" style="border: none;">
                </div>


            </div>
        </c:forEach>
    </div>
    <div id="decode_me"></div>
</body>
</html>