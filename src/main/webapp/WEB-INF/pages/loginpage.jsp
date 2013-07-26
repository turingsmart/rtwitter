<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Login Page-minitwiter</title>

    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="/static/css/bootstrap.min.css" rel="stylesheet" media="screen">

</head>
<body>
<script src="http://code.jquery.com/jquery.js"></script>
<script src="/static/js/bootstrap.min.js"></script>
<h1>Welcome </h1>

<div id="login-error">${error}</div>
<br>
<div class="text-left">
    <form class="form-horizontal" action="../../j_spring_security_check" method="post" >

        <div class="control-group">
            <label class="control-label" for="j_username">Username</label>
            <div class="controls">
                <input id="j_username" name="j_username" type="text"  placeholder="Username"/>
            </div>
        </div>
        <br>

        <div class="control-group">
            <label class="control-label" for="j_password">Password</label>
            <div class="controls">
                <input id="j_password" name="j_password" type="password" placeholder="password"/>
            </div>
        </div>
        <div class="control-group">
            <div class="controls">

                <input class=" btn-primary btn-info"  type="submit" value="signin"/>
            </div>
        </div>
    </form>
</div>




</body>
</html>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>--%>

<%--<%@ page language="java" contentType="text/html; charset=UTF-8"--%>
    <%--pageEncoding="UTF-8"%>--%>
<%--<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">--%>
<%--<html>--%>
<%--<head>--%>
<%--<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">--%>
<%--<title>Login Page</title>--%>
<%--</head>--%>
<%--<body>--%>

<%--<h1>Login</h1>--%>

<%--<div id="login-error">${error}</div>--%>

<%--<form action="../../j_spring_security_check" method="post" >--%>

<%--<p>--%>
	<%--<label for="j_username">Username</label>--%>
	<%--<input id="j_username" name="j_username" type="text" />--%>
<%--</p>--%>

<%--<p>--%>
	<%--<label for="j_password">Password</label>--%>
	<%--<input id="j_password" name="j_password" type="password" />--%>
<%--</p>--%>

<%--<input  type="submit" value="Login"/>								--%>
	<%----%>
<%--</form>--%>

<%--</body>--%>
<%--</html>--%>