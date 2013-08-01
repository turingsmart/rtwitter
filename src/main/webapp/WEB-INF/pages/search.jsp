<%--
  Created by IntelliJ IDEA.
  User: karthik
  Date: 29/7/13
  Time: 11:07 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

    <script src="/static/js/jquery.js"></script>

    <link href="/static/css/bootstrap.css" rel="stylesheet" type="text/css" >
    <link href="/static/css/bootstrap-responsive.css" rel="stylesheet" type="text/css">
    <%--<script src="http://code.jquery.com/jquery.js"></script>--%>
    <script src="/static/js/bootstrap.min.js"></script>

    <%--Maverick--%>
    <%--<script src="jquery-1.7.2.min.js"></script>--%>
    <!-- noty -->
    <script type="text/javascript" src="/static/js/noty/jquery.noty.js"></script>
    <title></title>


    <script type="text/javascript">
        function searchuser()
                   {
                       console.log("I am inside SearcUser");
                       //console.log(this.url);
                       var tweetUrl = "/home/searchUser";
                       $.ajax({
                           url: tweetUrl,
                           type:'POST',
                           data:{ loggedinUser: "${loggedinUser}",
                               searchString : "${searchString}"
                           },
                           success:function(d){
                               var data = JSON.parse(d);
                               var length = data.length;
                               console.log("success");
                                  console.log(data);
                               $('#tweetBox').empty();
                               for(var i=0; i < data.length; i++){
                                   $('#tweetBox').append(
                                           $('<div>').addClass('well')
                                                   .append($('<div>').addClass("pull-left").append($('<a>').text(data[i].name).attr("href","/"+data[i].username+"/timeline")))

                                   );
                               }
                           }    ,
                              error: function(xhr){console.log(xhr)}
                       });
                   }
    </script>

    <script type="text/javascript"> $(document).ready(function(){console.log( "ready!" );searchuser();});
    </script>
</head>
<body >
           <h1>${loggedinUser} searching for ${searchString} </h1>
           <br>
<div id="tweetBox"> </div>
</body>
</html>