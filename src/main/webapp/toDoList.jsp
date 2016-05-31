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
<jsp:include page="/header.jsp"/>
<body>

<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">
        <div class="container-fluid">
            <div class="row">
                <div class="col-lg-12">
                    <h1>ToDoList</h1>
                    <ul class="pagination">
                        <c:forEach begin="1" end="${pageCount}" var="i">
                            <c:choose>
                                <c:when test="${page eq i}">
                                    <li class="active"><a href="${pageContext.request.contextPath}/todoList?page=${i}">${i}</a></li>
                                </c:when>
                                <c:otherwise>
                                    <li><a href="${pageContext.request.contextPath}/todoList?page=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                    </ul>
                    <c:if test="${!empty todoList}">

                        <table class="table table-striped table-list-search table-hover ">
                            <thead>
                            <tr>
                                <th>ToDo Name</th>
                                <th>DeadLine </th>
                                <th>Task count</th>
                                <th>Done</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${todoList}" var="todo">
                                <jsp:useBean id="todo"
                                             class="lv.javaguru.java2.todo.form.ToDoListModel" scope="page"/>
                                <tr>
                                    <td>${todo.name}</td>
                                    <td>${todo.formatedDeadLineTime}</td>
                                    <td>${todo.taskCount}</td>
                                    <td>${todo.complete}</td>
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
                    <button type="button" class="btn btn-primary" onclick="window.location.href='/addToDo'">Add ToDo</button>
                </div>
            </div>
        </div>
    </div>
</div>

</body>
</html>
