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
                    <form:form method="post" action="${pageContext.request.contextPath}/addToDo" modelAttribute="todoTask">
                        <div class="form-group">
                            <label for="toDoName">ToDo Name</label>
                            <form:input  path="todoName" id="toDoName" required="required" type="text" class="form-control" placeholder="ToDo Name"/>
                        </div>

                        <div class="form-group">
                            <label for="deadline">DeadLine</label>
                            <form:input path="deadLine" id="deadline" type="date" class="form-control" placeholder="DeadLine"/>
                        </div>

                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Many Tasks
                            </label>
                        </div>

                        <div class="form-group">
                            <label for="notes">Notes</label>
                            <form:textarea path="notes" id="notes" rows="5" cols="30" class="form-control" placeholder="Notes"/>
                        </div>

                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
        </div>
    </div>
</div>
</body>
</html>
