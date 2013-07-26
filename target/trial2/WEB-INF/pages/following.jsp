<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
                changeImage(id),
                $('#decode_me').html(data.concat(this.URL));
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
</head>
<body>
<p>${username} following:</p>
<table>
    <c:forEach items="${list}" var="item">
        <tr>

            <td><a href="/${item}/timeline"><c:out value="${item}" /></a></td>
            <td>
                <img alt="" src="http://www.almostsavvy.com/wp-content/uploads/2009/07/unfollow-300x86.png"
                     style="height: 85px; width: 198px" id="${item}" onclick="changeFollowingStatus(id)" />
            </td>

        </tr>
    </c:forEach>
</table>
<div id="decode_me"></div>
 </body>
</html>