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
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>ToDoList</h1>

                    <c:if test="${!empty data}">
                        <table class="table table-striped table-list-search">
                            <thead>
                            <tr>
                                <th>ToDo Name</th>
                                <th>DeadLine </th>
                                <th>Task count</th>
                                <th>Done</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${data}" var="todo">
                                <%--<jsp:useBean id="todo"
                                             class="lv.javaguru.java2.web.form.model.ToDoListModel" scope="page"/>--%>
                                <tr>
                                    <td>${todo.toDoName}</td>
                                    <td>${todo.fromatedDeadLineDate}</td>
                                    <%--<td><tags:localDate date="${todo.deadLine}"/></td>--%>
                                    <td>${todo.taskCount}</td>
                                    <td><fmt:formatNumber type="percent"
                                                          maxIntegerDigits="3" value="${todo.percentDone}" /></td>
                                    <td>
                                        <div class='pull-right'>
                                            <a class="btn btn-success" href="<c:url value='/java2/todo/${todo.id}/edit' />" >Edit</a>
                                            <a class="btn btn-danger" href="<c:url value='/java2/todo/${todo.id}/delete' />" >Delete</a>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </c:if>
                    <button type="button" class="btn btn-primary" onclick="window.location.href='/java2/addToDo'">Add ToDo</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
