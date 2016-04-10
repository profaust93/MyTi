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
<% Map<String,String> dataMap = (Map<String,String>) request.getAttribute("data");%>

<form method = "post" action="addTimeLaps">
    <h3>TimeLaps name:</h3>
    <input type="text" name="name"> <%if(dataMap.get("nameCheckResult") != null) { %>
    <%=dataMap.get("nameCheckResult")%>
    <%};%>
    <h3>Category:</h3>
    <select name="category">
        <option value=""></option>
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select> <%if(dataMap.get("categoryCheckResult") != null) { %>
    <%= dataMap.get("categoryCheckResult")%>
    <%};%>
    <h3>Date:</h3><%if(dataMap.get("dateCheckResult") != null) { %>
    <%= dataMap.get("dateCheckResult")%>
    <%};%>
    <div id="datetimepicker" class="input-append date">
        <input type="text" name = "date">
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

    <h3>Short Description</h3><%if(dataMap.get("sDescCheck") != null) { %>
    <%= dataMap.get("sDescCheck")%>
    <%};%>
    <textarea rows="2" cols="50" type="text" name="shortDescription" autofocus maxlength="100"></textarea>
    <h3>Long Description</h3><%if(dataMap.get("lDescCheck") != null) { %>
    <%= dataMap.get("lDescCheck")%>
    <%};%>
    <textarea rows="4" cols="50" type="text" name="longDescription" autofocus maxlength="1000"></textarea>
    <input type="submit" value="submit">

</form>
</body>
</html>
