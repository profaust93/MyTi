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
    <title>AddTimeLaps</title>
</head>
<body>
<p><%=request.getAttribute("data")%></p>
<% Map<String,String> resultMap = (Map<String,String>) request.getAttribute("data");
%>
<form method = "post" action="addTimeLaps">
    <h1>Your ID:</h1>
    <input type="text" name="userId"> <p><%%></p>
    <h1>Category:</h1>
    <select name="category">
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h1>Date</h1>
    <input type="text" name="year">
    <input type="text" name="month">
    <input type="text" name="day">
    <input type="text" name="hour">
    <input type="text" name="minute">

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
