<%@ page import="java.util.Map" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: Ruslan
  Date: 2016.04.08.
  Time: 13:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link href="${pageContext.request.contextPath}/resources/lib/datetimepicker/css/bootstrap-combined.min.css" rel="stylesheet">
    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/resources/lib/datetimepicker/css/bootstrap-datetimepicker.min.css">
    <title>Edit Time Laps</title>
</head>
<body>
<% List<Map> dataList = (List<Map>) request.getAttribute("data");%>
<%Map<String,String> dataMap = dataList.get(0);%>
<%Map<String,String> resultCheckMap = dataList.get(1);%>
<form method = "post" action="editTimeLaps">

    <h3>TimeLaps name:</h3>
    <input type="text" name="name" value="<%=dataMap.get("name")%>"> <%= resultCheckMap.get("nameCheckResult")%>
    <h3>Category:</h3>
    <select name="category"> <%=resultCheckMap.get("categoryCheckResult")%>
        <option value="<%=dataMap.get("category")%>"><%=dataMap.get("category")%></option>
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h3>Date:</h3> <%=resultCheckMap.get("dateCheckResult")%>
    <div id="datetimepicker" class="input-append date">
        <input type="text" name = "date" value="<%= dataMap.get("date")%>">
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

    <h3>Short Description</h3> <%=resultCheckMap.get("sDescCheck")%>
    <textarea rows="2" cols="50" type="text" name="shortDescription" autofocus maxlength="100">
        <%= dataMap.get("shortDesc")%>
    </textarea>
    <h3>Long Description</h3> <%=resultCheckMap.get("lDescCheck")%>
    <textarea rows="4" cols="50" type="text" name="longDescription" autofocus maxlength="1000">
        <%= dataMap.get("longDesc")%>
    </textarea>
    <input type="submit" value="submit">
</form>


</body>
</html>
