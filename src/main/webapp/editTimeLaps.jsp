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
<jsp:include page="/header.jsp"/>
<body>
<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">

<% List<Map> dataList = (List<Map>) request.getAttribute("data");%>
<%Map<String,String> dataMap = dataList.get(0);%>
<%Map<String,String> resultCheckMap = dataList.get(1);%>

<form method = "post" action="editTimeLaps">

    <h3>TimeLaps name:</h3>
    <input type="text" name="name" value="<%=dataMap.get("name")%>"> <%= resultCheckMap.get("nameCheckResult")%>
    <h3>Category:</h3>
    <select name="category">
        <option value="<%=dataMap.get("category")%>"><%=dataMap.get("category")%></option>
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h3>Date:</h3>
        <input id="datetimepicker" type="text" name="date" value="<%= dataMap.get("date")%>" >

        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.min.js">
        </script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.full.min.js">
        </script>
        <script type="text/javascript"
                src="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/build/jquery.datetimepicker.full.js">
        </script>

        <script type="text/javascript">
            $(function(){
                $('#datetimepicker').appendDtpicker();
            });
        </script>

    <h3>Short Description</h3>
    <textarea rows="2" cols="50" type="text" name="shortDescription" autofocus maxlength="100"><%= dataMap.get("shortDesc")%></textarea>
    <h3>Long Description</h3>
    <textarea rows="4" cols="50" type="text" name="longDescription" autofocus maxlength="1000"><%= dataMap.get("longDesc")%></textarea>
    <input type="hidden"
           name="${_csrf.parameterName}"
           value="${_csrf.token}"/>
    <input type="submit" value="submit">
</form>

    </div>
</div>
</body>
</html>
