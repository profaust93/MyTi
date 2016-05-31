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

<div id="wrapper">
    <jsp:include page="/navbar.jsp"/>
    <div id="page-wrapper">
        <div class="container-fluid">
                <div class="col-lg-6">
                    <h1>Add ToDo</h1>
                    <form:form method="post" action="${pageContext.request.contextPath}/addToDo" modelAttribute="toDo" id = "toDo">
                        <div class="form-group">
                            <label for="toDoName">ToDo Name</label>
                            <form:input  path="toDoName" id="toDoName" required="required" type="text" class="form-control" placeholder="ToDo Name"/>
                        </div>

                        <div class="form-group">
                            <label for="deadLineTime">DeadLine</label>
                            <form:input path="deadLineTime" id="deadLineTime" type="datetime" class="form-control" placeholder="DeadLine"/>
                        </div>
                        <h4>Task</h4>
                    <div class="form-group">
                        <c:forEach items="${toDo.toDoTaskList}" var="task" varStatus="i" begin="0" >
                            <div class = "row">

                                <div class = "col-md-4">
                                    <input id="taskName" type="text" class="form-control" placeholder="Task Name"/>
                                </div>
                                <div style="display: none;" class = "goalsDiv" >
                                    <div class = "col-md-4">
                                        <label>Task count
                                        <form:input path="toDoTaskList[${i.index}].name" id="name${i.index}"/>
                                        </label>
                                    </div>
                            </div>
                        </c:forEach>
                        <c:out value="${toDo.toDoName}"></c:out>
                        <c:if test="${toDo.toDoTaskList.size() == 0}">
                            <div class = "row">
                                <div class = "col-md-4">
                                    <form:input path="toDoTaskList[0].name" class="form-control" name="toDoTaskList[].name" value="" placeholder="Task Name"/>
                                </div>
                            </div>
                        </c:if>
                        </div>
                        <button id="addTask" type="button" class="btn btn-primary btn-xs">Add Another Task</button>

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
</html>
