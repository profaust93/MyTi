<%--
  Created by IntelliJ IDEA.
  User: german
  Date: 4/3/16
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="ToDo List">
    <meta name="author" content="<MyTi Team">

    <title>MyTi</title>

    <!-- Bootstrap Core CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/metisMenu/dist/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${pageContext.request.contextPath}/resources/dist/css/timeline.css" rel="stylesheet">

    <!-- Jquerry UI Css -->
    <link href="${pageContext.request.contextPath}/resources/bower_components/jquery-ui/themes/base/all.css" rel="stylesheet">

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
        <div class="container-fluid">
                <div class="col-lg-6">
                    <h1>Add ToDo</h1>
                    <form method="post" action="/animal/add">
                        <div class="form-group">
                            <label for="toDoName">ToDo Name</label>
                            <input id="toDoName" required="required" type="text" class="form-control" placeholder="ToDo Name"/>
                        </div>

                        <div class="form-group">
                            <label for="deadline">DeadLine</label>
                            <input id="deadline" type="text" class="form-control" placeholder="DeadLine"/>
                        </div>

                            <div class = "row">
                                <h2>Task </h2>
                                <div class = "col-md-4">
                                    <label for="taskName">Task Name</label>
                                    <input id="taskName" type="text" class="form-control" placeholder="Task Name"/>
                                    <input id="multiGoal" type="checkbox" class="goalCheck" placeholder="Many goals"/>
                                    task have goals
                                </div>
                                <div style="display: none;" class = "goalsDiv" >
                                    <div class = "col-md-4">
                                        <label for="goals">Task count</label>
                                        <input id="goals" type="number" value="1" class="form-control" placeholder="Task count"/></div>
                                    <div class = "col-md-4">
                                        <label for="goalsComplete">Completed</label>
                                        <input id="goalsComplete" value="0" type="number" class="form-control" placeholder="Completed"/>

                                    </div>
                                </div>
                            </div>


                            <button id="addTask" type="button" class="btn btn-primary">Add Task</button>


                        <div class="form-group">
                            <label for="notes">Notes</label>
                            <textarea id="notes" rows="5" cols="30" class="form-control" placeholder="ToDo Name"></textarea>
                        </div>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form>
                </div>
        </div>
    </div>
</div>
<!-- jQuery -->
<script src="${pageContext.request.contextPath}/resources/bower_components/jquery/dist/jquery.min.js"></script>

<script src="${pageContext.request.contextPath}/resources/bower_components/jquery-ui/jquery-ui.min.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="${pageContext.request.contextPath}/resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>

<!-- Metis Menu Plugin JavaScript -->
<script src="${pageContext.request.contextPath}/resources/bower_components/metisMenu/dist/metisMenu.min.js"></script>

<!-- Custom Theme JavaScript -->

<script src="${pageContext.request.contextPath}/resources/dist/js/myTi.js"></script>

<script src="${pageContext.request.contextPath}/resources/dist/js/todo.js"></script>
</body>
</html>
