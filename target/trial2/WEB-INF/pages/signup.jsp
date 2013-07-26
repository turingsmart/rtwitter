<%--
  Created by IntelliJ IDEA.
  User: maverick
  Date: 15/7/13
  Time: 5:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="signup"%>
<html>
<head>
    <title>Mini Twitter New User</title>
</head>
<body>
<h2>New User</h2>
<signup:form method="post" commandName="users">
    <table>
        <tr>
            <td>    <label for="usernameInput">Username </label>       </td>
            <td>    <signup:input path="username" id="usernameInput" /> </td>
            <td>    <signup:errors path="username" cssclass="error"></signup:errors> </td>

        </tr>

        <tr>
            <td>    <label for="nameInput">Name </label>           </td>
            <td>    <signup:input path="name" id="nameInput" />     </td>
            <td>    <signup:errors path="name" cssclass="error"></signup:errors> </td>

        </tr>
        <tr>
            <td> <label for="passwordInput">Password </label> </td>
            <td>  <signup:input type = "password" path="password" id="passwordInput" /></td>
            <td>  <signup:errors path="password" cssclass="error"></signup:errors> </td>
        </tr>

        <tr>
            <td> <label for="EmailInput">Email </label> </td>
            <td>  <signup:input  path="email" id="EmailInput" /></td>
            <td>  <signup:errors path="email" cssclass="error"></signup:errors> </td>
        </tr>
    </table>
    <input type="submit" value="Submit" onsubmit=/>

</signup:form>
<h1>${message}</h1>
</body>
</html>