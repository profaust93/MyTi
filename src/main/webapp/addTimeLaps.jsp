<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.29.3
  Time: 23:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.2.2/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/lib/jquery-ui-1.11.4.custom/jquery-ui.js"></script>
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.9.2/themes/base/jquery-ui.css" />
    <script src="http://code.jquery.com/jquery-1.8.3.js"></script>
    <script src="http://code.jquery.com/ui/1.9.2/jquery-ui.js"></script>
    <script>
        $(function() {
            $( "#datepicker" ).datepicker();
        });
    </script>
    <title>AddTimeLaps</title>
</head>
<body>
<p><%=request.getAttribute("data")%></p>
<% Map<String,String> resultMap = (Map<String,String>) request.getAttribute("data");
%>
<form method = "post" action="addTimeLaps">
    <h1>Your ID:</h1>
    <input type="text" name="userId"> <%=resultMap.get("userId")%>
    <h1>Category:</h1>
    <select name="category">
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h1>Date:</h1>
    <input type="text" id="datepicker" />


    <h1>Short Description</h1>
    <textarea rows="2" cols="50" type="text" name="shortDescription">
    </textarea>
    <h1>Long Description</h1>
    <textarea rows="4" cols="50" type="text" name="longDescription">
    </textarea>
    <input type="submit" value="submit">
</form>
</body>
</html>
