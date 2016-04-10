<%@ page import="java.util.Map" %>
<%@ page import="lv.javaguru.java2.domain.TimeLapsList" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ruslan
  Date: 16.29.3
  Time: 19:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        button.accordion {
            background-color: #eee;
            color: #444;
            cursor: pointer;
            padding: 0px;
            width: 100%;
            border: none;
            text-align: left;
            outline: none;
            font-size: 15px;
            transition: 0.4s;
        }

        button.accordion.active, button.accordion:hover {
            background-color: #ddd;
        }

        div.panel {
            padding: 0 18px;
            background-color: white;
            max-height: 0;
            width: 90%;
            word-break: break-all;
            overflow: hidden;
            transition: 0.6s ease-in-out;
            opacity: 0;
        }

        div.panel.show {
            opacity: 1;
            max-height: 500px;
        }
    </style>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Main Page">
    <meta name="author" content="<MyTi Team">

    <title>SB Admin 2 - Bootstrap Admin Theme</title>

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



<% List<TimeLapsList> list = (List<TimeLapsList>) request.getAttribute("data");%>

        <form method="post" action="viewTimeLaps" name="DeleteAllTimeLaps">
            <button type="submit" value="Delete" name = "DeleteAllTimeLaps">Delete All</button>
        </form>

        <form method="post" action="viewTimeLaps" name="AddTimeLaps">
            <button type="submit" value="add" name="AddTimeLaps">Add New</button>
        </form>

<h2>View Time Laps Page</h2>
<% for (int i = 0; i < list.size() ; i++) { %>
<button class="accordion"><p>Name:<%=list.get(i).getTimeLapsName()%></p><p>ID:<%=list.get(i).getTimeLapsId()%></p></button>
<div class="panel">
    <div>
        <p><b>Compete Time:</b><%=list.get(i).getCompleteTime()%></p>
    </div>
    <div>
        <h4>Short Description:</h4>
        <p><%=list.get(i).getShortDescription()%></p>
    </div>
    <div>
        <h4>Long Description:</h4>
        <p><%=list.get(i).getLongDescription()%></p>
    </div>
    <div>
        <form method="get" action="editTimeLaps" name="TimeLapsId">
            <button type="submit" value="<%=list.get(i).getTimeLapsId()%>" name = "TimeLapsId">Edit</button>
        </form>
        <form method="post" action="viewTimeLaps" name="DeleteTimeLapsById">
            <button type="submit" value="<%=list.get(i).getTimeLapsId()%>" name="DeleteTimeLapsById">Delete</button>
        </form>
    </div>

</div>
<% } %>

<script>
    var acc = document.getElementsByClassName("accordion");
    var i;

    for (i = 0; i < acc.length; i++) {
        acc[i].onclick = function(){
            this.classList.toggle("active");
            this.nextElementSibling.classList.toggle("show");
        }
    }
</script>
    </div>
</div>
</body>
</html>
