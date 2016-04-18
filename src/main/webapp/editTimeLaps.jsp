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
    <title>Edit Time Laps</title>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Main Page">
    <meta name="author" content="<MyTi Team">

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/myTi.css" rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/morrisjs/morris.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/font-awesome/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <link rel="stylesheet" type="text/css" media="screen"
          href="${pageContext.request.contextPath}/resources/lib/datetimepicker-master/jquery.datetimepicker.css">
    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>
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
    <select name="category"> <%=resultCheckMap.get("categoryCheckResult")%>
        <option value="<%=dataMap.get("category")%>"><%=dataMap.get("category")%></option>
        <option value="sport">Sport</option>
        <option value="fun">Fun</option>
        <option value="work">Work</option>
        <option value="home">Home</option>
    </select>
    <h3>Date:</h3> <%=resultCheckMap.get("dateCheckResult")%>
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
            jQuery('#datetimepicker').datetimepicker();
        </script>

    <h3>Short Description</h3> <%=resultCheckMap.get("sDescCheck")%>
    <textarea rows="2" cols="50" type="text" name="shortDescription" autofocus maxlength="100"><%= dataMap.get("shortDesc")%></textarea>
    <h3>Long Description</h3> <%=resultCheckMap.get("lDescCheck")%>
    <textarea rows="4" cols="50" type="text" name="longDescription" autofocus maxlength="1000"><%= dataMap.get("longDesc")%></textarea>
    <input type="submit" value="submit">
</form>

    </div>
</div>
</body>
</html>
