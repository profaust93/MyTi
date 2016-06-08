<%--
  Created by IntelliJ IDEA.
  User: german
  Date: 4/3/16
  Time: 10:02 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<html>
<jsp:include page="/header.jsp"/>
<body>
<link href="${pageContext.request.contextPath}/resources/css/todos.css" type="text/css" rel="stylesheet" />
<script src="${pageContext.request.contextPath}/resources/js/todos.js"></script>

<c:choose>
    <c:when test="${empty toDo.toDoId}">
        <c:set var="action" value="addToDo"/>
    </c:when>
    <c:otherwise>
        <c:set var="action" value="toDo/${toDo.toDoId}/edit"/>
    </c:otherwise>
</c:choose>


<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">
        <div class="container-fluid">
                <div class="col-lg-6">
                    <h1>Add ToDo</h1>
                    <form:form method="post" action="${pageContext.request.contextPath}/${action}" modelAttribute="toDo" id = "toDo">
                        <div class="form-group">
                            <label for="toDoName">ToDo Name</label>
                            <form:input  path="toDoName" id="toDoName" required="required" type="text" class="form-control" placeholder="ToDo Name"/>
                        </div>


                        <div class="form-group">
                            <label for="formatedDeadLineTime">DeadLine</label>
                            <form:input path="formatedDeadLineTime" id="formatedDeadLineTime" type="text" class="form-control" placeholder="DeadLine"/>

                            <script type="text/javascript">
                                $(function(){
                                    $('#formatedDeadLineTime').appendDtpicker();
                                });
                            </script>
                        </div>
                        <h4>Task</h4>
                    <div class="form-group">
                            <div id="todoapp">
                                    <input id="new-todo" class="form-control" type="text" placeholder="What needs to be done?">
                                    <section id='main'>
                                        <ul id='todo-list'>
                                            <c:forEach items="${toDo.toDoFormTaskList}" var="task">
                                                <jsp:useBean id="task"
                                                             class="lv.javaguru.java2.todo.form.ToDoFormTask" scope="page"/>
                                                <li>
                                                    <div class='view'>
                                                        <input class='toggle' type='checkbox' <c:if test="${task.done}">checked="checked"</c:if>>
                                                        <input class='springcheckbox' type='hidden' value='off' name=''/>
                                                        <label data='' <c:if test="${task.done}">style="text-decoration: line-through;"</c:if> ><c:out value="${task.name}"/></label>
                                                        <input type='hidden' class = 'task' name = '' value='${task.name}'/>
                                                        <a class='destroy'></a>
                                                    </div>
                                                </li>
                                            </c:forEach>
                                        </ul>
                                    </section>
                                    <footer> <a id="clear-completed">Clear completed</a>
                                        <div id='todo-count'></div>
                                    </footer>
                            </div>

                        </div>
                        <div class="form-group">
                            <label for="notes">Notes</label>
                            <form:textarea path="notes" id="notes" rows="5" cols="30" class="form-control" placeholder="Notes"/>
                        </div>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
                    <script type="text/javascript">
                        </script>
        </div>
    </div>
</div>
</body>

<script type="text/javascript">
</script>
</html>
