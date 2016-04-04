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
    <link href="${pageContext.request.contextPath}/resources/lib/datetimepicker/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/resources/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <title>AddTimeLaps</title>
</head>
<body>
<p><%=request.getAttribute("data")%></p>

<% Map<String,String> dataMap = (Map<String,String>) request.getAttribute("data");
%>
<form method = "post" action="addTimeLaps">
    <h3>User ID: <%=dataMap.get("getUserId")%> </h3>
    <h3>TimeLaps name:</h3>
    <input type="text" name="name"> <%=dataMap.get("nameCheckResult")%>
    <h3>Category:</h3>
    <select name="category">
        <option value=""></option>
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select> <%= dataMap.get("categoryCheckResult")%>
    <h3>Date:</h3> <%= dataMap.get("dateCheckResult")%>

    <div id="datetimepicker" class="input-append date">
        <input type="text" name = "date"></input>
      <span class="add-on">
        <i data-time-icon="icon-time" data-date-icon="icon-calendar"></i>
      </span>
    </div>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/lib/datetimepicker/js/jquery.min.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/lib/datetimepicker/js/bootstrap.min.js">
    </script>
    <script type="text/javascript"
            src="${pageContext.request.contextPath}/resources/lib/datetimepicker/js/bootstrap-datetimepicker.min.js">
    </script>
    <script type="text/javascript">
        $('#datetimepicker').datetimepicker({
            format: 'dd/MM/yyyy hh:mm:ss',
            language: 'en'
        });
    </script>

    <h3>Short Description</h3>
    <textarea rows="2" cols="50" type="text" name="shortDescription" autofocus maxlength="3">
    </textarea>
    <h3>Long Description</h3>
    <textarea rows="4" cols="50" type="text" name="longDescription" autofocus maxlength="1000">
    </textarea>
    <input type="submit" value="submit">
</form>
</body>
</html>
