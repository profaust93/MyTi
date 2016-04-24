<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.UserProfileList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 2016.04.23.
  Time: 13:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User Profile List</title>
</head>
<body>
<%List<UserProfileList> userProfileList = (List<UserProfileList>) request.getAttribute("data");%>
<% for(int i = 0; i < userProfileList.size() ; i++) { %>

<button class="accordion"><p>Name:<%=userProfileList.get(i).getFirstName()%></p>
    <p>Surname:<%=userProfileList.get(i).getLastName()%></p>
    <p>ID:<%=userProfileList.get(i).getUserId()%></p>
</button>
<div class="panel">
    <div>
        <p><b>Name:</b><%=userProfileList.get(i).getFirstName()%></p>
    </div>
    <div>
        <p><b>Surname:</b><%=userProfileList.get(i).getLastName()%></p>
    </div>
    <div>
        <h4>E-mail:</h4><p><%=userProfileList.get(i).getEmail()%></p>
    </div>
    <div>
        <form method="get" action="viewUserProfile" name="userId">
            <button type="submit" value="<%=userProfileList.get(i).getUserId()%>" name = "userId">View User Profile</button>
        </form>
        <form method="get" action="addChallenge" name="recipientId">
            <button type="submit" value="<%=userProfileList.get(i).getUserId()%>" name = "recipientId">Challenge!</button>
        </form>
    </div>
</div>
<%}%>
</body>
</html>
