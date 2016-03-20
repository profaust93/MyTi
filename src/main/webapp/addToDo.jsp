<%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.18.3
  Time: 19:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo</title>
</head>
<body>
<form method = "post" action="todo">
    <h1>Your ID:</h1>
    <input type="text" name="id">
    <h1>Category:</h1>
    <select name="category">
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h1>Priority:</h1>
    <select name = "priority">
        <option value="1">1</option>
        <option value="2">2</option>
        <option value="3">3</option>
        <option value="4">4</option>
    </select>
    <h1>Short Description</h1>
    <textarea rows="4" cols="50" type="text" name="description">
    </textarea>
    <input type="submit" value="submit">
    </form>



</body>
</html>
