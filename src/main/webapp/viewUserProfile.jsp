<%@ page import="lv.javaguru.java2.domain.UserProfile" %>
<%--
  Created by IntelliJ IDEA.
  User: Camille
  Date: 02.04.2016
  Time: 13:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Profile</title>
</head>
<body>
<h1>Hi, Dude!</h1>
<p>This is your profile.</p>
<p><% UserProfile userProfile = (UserProfile)request.getAttribute("data");%></p>
<p>Name: <%=userProfile.getFirstName()%></p>
<p>Family name: <%=userProfile.getLastName()%></p>
<p>E-mail: <%=userProfile.getEmail()%></p></br>

<form action="editUserProfile.jsp">
    <input type="submit" value="Edit profile">
</form>


</body>
</html>
